package org.tmme.ci.recommender.cb.task.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.Validate;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.mahout.clustering.Cluster;
import org.apache.mahout.clustering.kmeans.KMeansDriver;
import org.apache.mahout.clustering.kmeans.RandomSeedGenerator;
import org.apache.mahout.common.distance.CosineDistanceMeasure;
import org.apache.mahout.common.distance.DistanceMeasure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmme.ci.clients.CatalogClient;
import org.tmme.ci.models.Item;
import org.tmme.ci.recommender.cb.model.ClusterConfig;
import org.tmme.ci.recommender.cb.repository.ClusterConfigRepository;
import org.tmme.ci.recommender.cb.task.ClusterTask;
import org.tmme.ci.recommender.cb.transformer.SequenceFileTransformer;
import org.tmme.ci.recommender.cb.transformer.SparseVectorTransformer;
import org.tmme.ci.recommender.cb.utils.ClusterHelper;
import org.tmme.ci.recommender.cb.utils.FileUtils;

public class ClusterTaskImpl implements ClusterTask {

	private static final Logger LOG = LoggerFactory
			.getLogger(ClusterTaskImpl.class);

	private final ClusterConfigRepository clusterConfigRepository;
	private final CatalogClient catalogClient;
	private final Configuration config;

	public ClusterTaskImpl(
			final ClusterConfigRepository clusterConfigRepository,
			final CatalogClient catalogClient, final Configuration config) {
		Validate.notNull(clusterConfigRepository);
		Validate.notNull(catalogClient);
		Validate.notNull(config);
		this.catalogClient = catalogClient;
		this.clusterConfigRepository = clusterConfigRepository;
		this.config = config;
	}

	@Override
	public void cluster() {

		final List<ClusterConfig> clusterConfigs = clusterConfigRepository
				.findAll();
		if (CollectionUtils.isEmpty(clusterConfigs)) {
			LOG.info("No cluster configurations defined");
			return;
		}

		final List<String> configTypes = new ArrayList<String>();
		for (final ClusterConfig clusterConfig : clusterConfigs) {
			configTypes.add(clusterConfig.getType());
		}

		final Map<String, List<Item>> itemsMap = catalogClient
				.getItemsForTypes(configTypes);
		if (MapUtils.isEmpty(itemsMap)) {
			LOG.info("No items found for the specified criterias");
			return;
		}

		for (final ClusterConfig clusterConfig : clusterConfigs) {
			final String type = clusterConfig.getType();
			final List<Item> items = itemsMap.get(type);
			if (CollectionUtils.isEmpty(items)) {
				LOG.info("No items found for config type {}", type);
				continue;
			}
			// TODO check if its better to cleanup everything
			FileUtils.deleteFolder(config, type);

			for (final Item item : items) {
				for (final String attributeName : clusterConfig.getAttributes()) {
					final String content = (String) item.get(attributeName);
					if (content != null) {
						FileUtils.createFile(config, type + "/" + attributeName
								+ "/input", item.getId(), content);
					}
				}
			}

			for (final String attributeName : clusterConfig.getAttributes()) {

				final String seqFilesOuputDir = type + "/" + attributeName
						+ "/seqfiles";
				try {
					SequenceFileTransformer.toSequenceFiles(config, type + "/"
							+ attributeName + "/input", seqFilesOuputDir);
				} catch (final Exception e) {
					LOG.error(
							"Exception while transforming to sequence the files in {}. Message {}",
							type, e.getMessage());
					return;
				}

				final String sparseVectorInputDir = seqFilesOuputDir;
				final String sparseVectorOuputDir = type + "/" + attributeName
						+ "/vectors";
				try {
					SparseVectorTransformer.toSparseVectors(config,
							sparseVectorInputDir, sparseVectorOuputDir);
				} catch (final Exception e) {
					LOG.error(
							"Exception while transforming to sparse vectors the files in {}. Message {}",
							type, e.getMessage());
					return;
				}

				final String vectorsFolder = sparseVectorOuputDir
						+ "/tfidf-vectors";
				final Path samples = new Path(vectorsFolder + "/part-r-00000");
				final Path output = new Path(type + "/" + attributeName + "/"
						+ "output");
				FileUtils.deletePath(config, output);

				final DistanceMeasure measure = new CosineDistanceMeasure();
				final Path clustersIn = new Path(output, "random-seeds");
				try {
					RandomSeedGenerator.buildRandom(config, samples,
							clustersIn, 3, measure);
				} catch (final IOException e) {
					LOG.error(
							"Exception while generating random seeds. Message {}",
							e.getMessage());
					return;
				}

				try {
					KMeansDriver.run(config, samples, clustersIn, output,
							measure, 0.01, 10, true, 0.0, true);
				} catch (final Exception ex) {
					LOG.error(
							"Exception while running kmeans driver. Message {}",
							ex.getMessage());
					return;
				}

				final List<List<Cluster>> clusters = ClusterHelper
						.readClusters(config, output);

				if (CollectionUtils.isNotEmpty(clusters)) {
					for (final Cluster cluster : clusters
							.get(clusters.size() - 1)) {
						System.out.println("Cluster id: " + cluster.getId()
								+ " center: "
								+ cluster.getCenter().asFormatString());
					}
				}
			}

		}

	}

}
