package org.tmme.ci.recommender.cb.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

public class ConfigurationFactory {

	public enum FileSystem {
		LOCAL, HDFS
	}

	private ConfigurationFactory() {
		// avoid instantiation
	}

	public static Configuration createNewConfiguration(
			final FileSystem fileSystem, final String resource) {
		final Configuration config = new Configuration();
		if (fileSystem == FileSystem.HDFS) {
			config.addResource(new Path(resource));
		}
		return config;
	}

}
