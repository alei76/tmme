package org.tmme.ci.recommender.evaluator;

import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.tmme.ci.recommender.cf.factory.RecommenderSimilarity;

public class EvaluatorDriver {

	public static void main(final String[] args) throws Exception {
		String filename = "src/test/resources/sample4cf.csv";
		final RecommenderEvaluatorRMSTest rms = new RecommenderEvaluatorRMSTest();
		rms.setUp();
		System.out.println("### Sample4CF - RMSE - User Based ###");
		double result = rms.testUser(filename, RecommenderSimilarity.EUCLIDEAN);
		System.out.println("Euclidean Similarity: " + result);
		result = rms.testUser(filename, RecommenderSimilarity.PEARSON);
		System.out.println("Pearson Similarity: " + result);
		result = rms.testUser(filename, RecommenderSimilarity.TANIMOTO);
		System.out.println("Jaccard Similarity: " + result);
		result = rms.testUser(filename, RecommenderSimilarity.LOG);
		System.out.println("Log-Likelihood Similarity: " + result);

		System.out.println("### Sample4CF - RMSE - Item Based ###");
		result = rms.testItem(filename, RecommenderSimilarity.EUCLIDEAN);
		System.out.println("Euclidean Similarity: " + result);
		result = rms.testItem(filename, RecommenderSimilarity.PEARSON);
		System.out.println("Pearson Similarity: " + result);
		result = rms.testItem(filename, RecommenderSimilarity.TANIMOTO);
		System.out.println("Jaccard Similarity: " + result);
		result = rms.testItem(filename, RecommenderSimilarity.LOG);
		System.out.println("Log-Likelihood Similarity: " + result);

		final RecommenderEvaluatorAverageAbsoluteDifferenceTest aad = new RecommenderEvaluatorAverageAbsoluteDifferenceTest();
		aad.setUp();
		System.out.println("### Sample4CF - AAD - User Based ###");
		result = aad.testUser(filename, RecommenderSimilarity.EUCLIDEAN);
		System.out.println("Euclidean Similarity: " + result);
		result = aad.testUser(filename, RecommenderSimilarity.PEARSON);
		System.out.println("Pearson Similarity: " + result);
		result = aad.testUser(filename, RecommenderSimilarity.TANIMOTO);
		System.out.println("Jaccard Similarity: " + result);
		result = aad.testUser(filename, RecommenderSimilarity.LOG);
		System.out.println("Log-Likelihood Similarity: " + result);

		System.out.println("### Sample4CF - AAD - Item Based ###");
		result = aad.testItem(filename, RecommenderSimilarity.EUCLIDEAN);
		System.out.println("Euclidean Similarity: " + result);
		result = aad.testItem(filename, RecommenderSimilarity.PEARSON);
		System.out.println("Pearson Similarity: " + result);
		result = aad.testItem(filename, RecommenderSimilarity.TANIMOTO);
		System.out.println("Jaccard Similarity: " + result);
		result = aad.testItem(filename, RecommenderSimilarity.LOG);
		System.out.println("Log-Likelihood Similarity: " + result);

		final RecommenderIRStatsEvaluatorTest irStats = new RecommenderIRStatsEvaluatorTest();
		irStats.setUp();
		System.out.println("### Sample4CF - PrecisionRecall - User Based ###");
		IRStatistics stats = irStats.testUser(filename,
				RecommenderSimilarity.EUCLIDEAN);
		System.out.println("Euclidean Similarity:");
		System.out.println(" Precision: " + stats.getPrecision());
		System.out.println(" Recall: " + stats.getRecall());
		stats = irStats.testUser(filename, RecommenderSimilarity.PEARSON);
		System.out.println("Pearson Similarity:");
		System.out.println(" Precision: " + stats.getPrecision());
		System.out.println(" Recall: " + stats.getRecall());
		stats = irStats.testUser(filename, RecommenderSimilarity.TANIMOTO);
		System.out.println("Jaccard Similarity:");
		System.out.println(" Precision: " + stats.getPrecision());
		System.out.println(" Recall: " + stats.getRecall());
		stats = irStats.testUser(filename, RecommenderSimilarity.LOG);
		System.out.println("Log-likelihood Similarity:");
		System.out.println(" Precision: " + stats.getPrecision());
		System.out.println(" Recall: " + stats.getRecall());

		System.out.println("### Sample4CF - PrecisionRecall - Item Based ###");
		stats = irStats.testItem(filename, RecommenderSimilarity.EUCLIDEAN);
		System.out.println("Euclidean Similarity:");
		System.out.println(" Precision: " + stats.getPrecision());
		System.out.println(" Recall: " + stats.getRecall());
		stats = irStats.testItem(filename, RecommenderSimilarity.PEARSON);
		System.out.println("Pearson Similarity:");
		System.out.println(" Precision: " + stats.getPrecision());
		System.out.println(" Recall: " + stats.getRecall());
		stats = irStats.testItem(filename, RecommenderSimilarity.TANIMOTO);
		System.out.println("Jaccard Similarity:");
		System.out.println(" Precision: " + stats.getPrecision());
		System.out.println(" Recall: " + stats.getRecall());
		stats = irStats.testItem(filename, RecommenderSimilarity.LOG);
		System.out.println("Log-likelihood Similarity:");
		System.out.println(" Precision: " + stats.getPrecision());
		System.out.println(" Recall: " + stats.getRecall());

		filename = "src/test/resources/grouplens.csv";

		System.out.println("### MovieLens - RMSE - User Based ###");
		result = rms.testUser(filename, RecommenderSimilarity.EUCLIDEAN);
		System.out.println("Euclidean Similarity: " + result);
		result = rms.testUser(filename, RecommenderSimilarity.PEARSON);
		System.out.println("Pearson Similarity: " + result);
		result = rms.testUser(filename, RecommenderSimilarity.TANIMOTO);
		System.out.println("Jaccard Similarity: " + result);
		result = rms.testUser(filename, RecommenderSimilarity.LOG);
		System.out.println("Log-Likelihood Similarity: " + result);

		System.out.println("### MovieLens - RMSE - Item Based ###");
		result = rms.testItem(filename, RecommenderSimilarity.EUCLIDEAN);
		System.out.println("Euclidean Similarity: " + result);
		result = rms.testItem(filename, RecommenderSimilarity.PEARSON);
		System.out.println("Pearson Similarity: " + result);
		result = rms.testItem(filename, RecommenderSimilarity.TANIMOTO);
		System.out.println("Jaccard Similarity: " + result);
		result = rms.testItem(filename, RecommenderSimilarity.LOG);
		System.out.println("Log-Likelihood Similarity: " + result);

	}

}
