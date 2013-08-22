package org.tmme.ci.recommender.cb.utils;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.mahout.common.HadoopUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {

	private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);

	private FileUtils() {
		// avoid instantiation
	}

	public static void deleteFolder(final Configuration config,
			final String folder) {
		try {
			final Path path = new Path(folder);
			final FileSystem fs = FileSystem.get(config);
			if (fs.exists(path)) {
				HadoopUtil.delete(config, path);
			}
		} catch (final IOException e) {
			LOG.error("Exception deleting folder {}. Message {}", folder,
					e.getMessage());
		}
	}

	public static void createFile(final Configuration config,
			final String folder, final String fileName, final String content) {
		FileSystem fileSystem = null;
		FSDataOutputStream out = null;
		try {
			fileSystem = FileSystem.get(config);
			final Path path = new Path(folder + "/" + fileName);
			out = fileSystem.create(path);
			out.writeUTF(content);

		} catch (final IOException e) {
			LOG.error("Exception writing file {} into folder {}. Message {}",
					fileName, folder, e.getMessage());
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (fileSystem != null) {
					fileSystem.close();
				}
			} catch (final IOException e) {
				// nothing to do
			}
		}

	}

}
