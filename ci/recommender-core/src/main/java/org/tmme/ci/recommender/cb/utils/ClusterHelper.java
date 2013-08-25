package org.tmme.ci.recommender.cb.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.mahout.clustering.Cluster;
import org.apache.mahout.clustering.classify.WeightedVectorWritable;
import org.apache.mahout.clustering.iterator.ClusterWritable;
import org.apache.mahout.common.iterator.sequencefile.PathFilters;
import org.apache.mahout.common.iterator.sequencefile.PathType;
import org.apache.mahout.common.iterator.sequencefile.SequenceFileDirValueIterable;
import org.apache.mahout.math.NamedVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmme.ci.recommender.cb.model.ClusteredItem;

import com.google.common.collect.Lists;

public class ClusterHelper {

	private static final Logger LOG = LoggerFactory
			.getLogger(ClusterHelper.class);

	public static void writePointsToFile(final List<Vector> points,
			final Configuration conf, final Path path) {
		SequenceFile.Writer writer = null;
		try {
			final FileSystem fs = FileSystem.get(path.toUri(), conf);
			writer = new SequenceFile.Writer(fs, conf, path,
					LongWritable.class, VectorWritable.class);
			long recNum = 0;
			final VectorWritable vec = new VectorWritable();
			for (final Vector point : points) {
				vec.set(point);
				writer.append(new LongWritable(recNum++), vec);
			}
		} catch (final IOException ex) {
			LOG.error("Exception writing points to file. Message {}",
					ex.getMessage());
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (final IOException e) {
					// nothing to do here
				}
			}
		}
	}

	public static List<List<Cluster>> readClusters(final Configuration conf,
			final Path output) {
		final List<List<Cluster>> clustersList = Lists.newArrayList();
		try {
			final FileSystem fs = FileSystem.get(output.toUri(), conf);
			for (final FileStatus s : fs.listStatus(output,
					new ClustersFilter())) {
				final List<Cluster> clusters = Lists.newArrayList();
				for (final ClusterWritable value : new SequenceFileDirValueIterable<ClusterWritable>(
						s.getPath(), PathType.LIST,
						PathFilters.logsCRCFilter(), conf)) {
					final Cluster cluster = value.getValue();
					clusters.add(cluster);
				}
				clustersList.add(clusters);
			}
		} catch (final IOException ex) {
			LOG.error("Exception reading clusters. Message {}", ex.getMessage());
		}
		return clustersList;
	}

	public static List<ClusteredItem> readClusteredItems(
			final Configuration config, final String outputDir,
			final String attributeName) {
		final List<ClusteredItem> clusteredItems = new ArrayList<ClusteredItem>();
		SequenceFile.Reader reader = null;
		try {
			final FileSystem fs = FileSystem.get(config);
			reader = new SequenceFile.Reader(fs, new Path(outputDir + "/"
					+ Cluster.CLUSTERED_POINTS_DIR + "/part-m-00000"), config);
			final IntWritable key = new IntWritable();
			final WeightedVectorWritable value = new WeightedVectorWritable();
			while (reader.next(key, value)) {
				final ClusteredItem clusteredItem = new ClusteredItem();
				final NamedVector namedVector = (NamedVector) value.getVector();
				final String name = namedVector.getName();
				final String itemId = name.substring(1);
				clusteredItem.setClusterId(key.toString());
				clusteredItem.setItemId(itemId);
				clusteredItem.setAttribute(attributeName);
				clusteredItems.add(clusteredItem);
			}
		} catch (final Exception ex) {
			LOG.error("Exception reading output" + ex.getMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (final IOException e) {
					// nothing to do here
				}
			}
		}
		return clusteredItems;
	}
}
