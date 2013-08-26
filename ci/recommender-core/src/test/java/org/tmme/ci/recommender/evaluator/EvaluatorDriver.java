package org.tmme.ci.recommender.evaluator;

import org.tmme.ci.recommender.cf.factory.RecommenderSimilarity;

public class EvaluatorDriver {

	public static void main(final String[] args) throws Exception {
		String filename = "src/test/resources/sample.csv";
		final RecommenderEvaluatorRMSTest rms = new RecommenderEvaluatorRMSTest();
		rms.setUp();
		System.out.println("### Sample - RMSE - User Based ###");
		double result = rms.testUser(filename, RecommenderSimilarity.EUCLIDEAN);
		System.out.println("Euclidean Similarity: " + result);
		result = rms.testUser(filename, RecommenderSimilarity.PEARSON);
		System.out.println("Pearson Similarity: " + result);
		result = rms.testUser(filename, RecommenderSimilarity.TANIMOTO);
		System.out.println("Jaccard Similarity: " + result);
		result = rms.testUser(filename, RecommenderSimilarity.LOG);
		System.out.println("Log-Likelihood Similarity: " + result);

		System.out.println("### Sample - RMSE - Item Based ###");
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
		System.out.println("### Sample - AAD - User Based ###");
		result = aad.testUser(filename, RecommenderSimilarity.EUCLIDEAN);
		System.out.println("Euclidean Similarity: " + result);
		result = aad.testUser(filename, RecommenderSimilarity.PEARSON);
		System.out.println("Pearson Similarity: " + result);
		result = aad.testUser(filename, RecommenderSimilarity.TANIMOTO);
		System.out.println("Jaccard Similarity: " + result);
		result = aad.testUser(filename, RecommenderSimilarity.LOG);
		System.out.println("Log-Likelihood Similarity: " + result);

		System.out.println("### Sample - AAD - Item Based ###");
		result = aad.testItem(filename, RecommenderSimilarity.EUCLIDEAN);
		System.out.println("Euclidean Similarity: " + result);
		result = aad.testItem(filename, RecommenderSimilarity.PEARSON);
		System.out.println("Pearson Similarity: " + result);
		result = aad.testItem(filename, RecommenderSimilarity.TANIMOTO);
		System.out.println("Jaccard Similarity: " + result);
		result = aad.testItem(filename, RecommenderSimilarity.LOG);
		System.out.println("Log-Likelihood Similarity: " + result);

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
