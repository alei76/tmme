package org.tmme.ci.recommender.cb.transformer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.text.SequenceFilesFromDirectory;

public class SequenceFileTransformer {

	private SequenceFileTransformer() {
		// avoid construction
	}

	public static void toSequenceFiles(final Configuration config,
			final String inputDir, final String outputDir) throws Exception {
		final SequenceFilesFromDirectory sffd = new SequenceFilesFromDirectory();
		sffd.setConf(config);
		ToolRunner.run(sffd, new String[] { "-i", inputDir, "-o", outputDir });
	}

}
