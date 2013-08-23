package org.tmme.ci.recommender.cb.transformer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.vectorizer.SparseVectorsFromSequenceFiles;

public class SparseVectorTransformer {

	private SparseVectorTransformer() {
		// avoid instantiation
	}

	public static void toSparseVectors(final Configuration config,
			final String inputDir, final String outputDir) throws Exception {
		// ./mahout seq2sparse -i movies/description/seqfiles -o
		// movies/description/vectors -a
		// org.apache.lucene.analysis.standard.StandardAnalyzer -chunk 100 -md 1
		// -xs 3.0 -wt TFIDF -n 2 -ow -seq -nv
		final SparseVectorsFromSequenceFiles svfsf = new SparseVectorsFromSequenceFiles();
		svfsf.setConf(config);
		ToolRunner.run(svfsf, new String[] { "-i", inputDir, "-o", outputDir,
				"-a", "org.apache.lucene.analysis.standard.StandardAnalyzer",
				"-chunk", "100", "-md", "1", "-xs", "3.0", "-wt", "TFIDF",
				"-n", "2", "-ow", "-seq", "-nv" });

	}
}
