package org.tmme.ci.recommender.cb.transformer;

import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.mahout.common.Pair;
import org.apache.mahout.vectorizer.DictionaryVectorizer;
import org.apache.mahout.vectorizer.DocumentProcessor;
import org.apache.mahout.vectorizer.tfidf.TFIDFConverter;
import org.tmme.ci.recommender.cb.analyzer.CustomAnalyzer;
import org.tmme.ci.recommender.cb.utils.FileUtils;

public class SparseVectorTransformer {

	private static final int minSupport = 5;
	private static final int minDf = 5;
	private static final int maxDFPercent = 95;
	private static final int maxNGramSize = 1;
	private static final float minLLRValue = 50;
	private static final int reduceTasks = 1;
	private static final int chunkSize = 200;
	private static final int norm = 2;
	private static final boolean sequentialAccessOutput = true;

	private SparseVectorTransformer() {
		// avoid instantiation
	}

	public static void toSparseVectors(final Configuration config,
			final String inputDir, final String outputDir) throws Exception {

		FileUtils.deleteFolder(config, outputDir);

		final Path tokenizedPath = new Path(outputDir,
				DocumentProcessor.TOKENIZED_DOCUMENT_OUTPUT_FOLDER);

		DocumentProcessor.tokenizeDocuments(new Path(inputDir),
				CustomAnalyzer.class, tokenizedPath, config);

		DictionaryVectorizer.createTermFrequencyVectors(tokenizedPath,
				new Path(outputDir),
				DictionaryVectorizer.DOCUMENT_VECTOR_OUTPUT_FOLDER, config,
				minSupport, maxNGramSize, minLLRValue, 2, true, reduceTasks,
				chunkSize, sequentialAccessOutput, false);

		final Pair<Long[], List<Path>> dfData = TFIDFConverter.calculateDF(
				new Path(outputDir,
						DictionaryVectorizer.DOCUMENT_VECTOR_OUTPUT_FOLDER),
				new Path(outputDir), config, chunkSize);

		TFIDFConverter.processTfIdf(new Path(outputDir,
				DictionaryVectorizer.DOCUMENT_VECTOR_OUTPUT_FOLDER), new Path(
				outputDir), config, dfData, minDf, maxDFPercent, norm, true,
				sequentialAccessOutput, false, reduceTasks);

	}

}
