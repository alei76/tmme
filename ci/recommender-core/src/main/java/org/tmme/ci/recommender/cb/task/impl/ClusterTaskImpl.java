package org.tmme.ci.recommender.cb.task.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.Validate;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.mahout.clustering.Cluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmme.ci.clients.CatalogClient;
import org.tmme.ci.models.Item;
import org.tmme.ci.recommender.cb.algorithm.Algorithm;
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
	private final Algorithm algorithm;

	public ClusterTaskImpl(
			final ClusterConfigRepository clusterConfigRepository,
			final CatalogClient catalogClient, final Configuration config,
			final Algorithm algorithm) {
		Validate.notNull(clusterConfigRepository);
		Validate.notNull(catalogClient);
		Validate.notNull(config);
		Validate.notNull(algorithm);
		this.catalogClient = catalogClient;
		this.clusterConfigRepository = clusterConfigRepository;
		this.config = config;
		this.algorithm = algorithm;
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
				final String inputDir = vectorsFolder + "/part-r-00000";
				final String outputDir = type + "/" + attributeName + "/"
						+ algorithm.toString();

				FileUtils.deleteFolder(config, outputDir);

				try {

					algorithm.compute(inputDir, outputDir);

				} catch (final Exception ex) {
					LOG.error("Exception while computing cluster. Message {}",
							ex.getMessage());
					return;
				}

				final List<List<Cluster>> clusters = ClusterHelper
						.readClusters(config, new Path(outputDir));

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
