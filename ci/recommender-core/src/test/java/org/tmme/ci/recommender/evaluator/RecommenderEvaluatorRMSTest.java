package org.tmme.ci.recommender.evaluator;

import junit.framework.Assert;

import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.junit.Before;
import org.junit.Test;
import org.tmme.ci.recommender.cf.factory.RecommenderSimilarity;

public class RecommenderEvaluatorRMSTest extends RecommenderEvaluatorTest {

	@Before
	public void setUp() {
		super.setUp(new RMSRecommenderEvaluator());
	}

	@Test
	public void testSampleDataUserAndPearson() throws Exception {
		final double result = testUser(SAMPLE_FILE,
				RecommenderSimilarity.PEARSON);
		Assert.assertEquals(1.0, result);
	}

	@Test
	public void testSampleDataUserAndEuclidean() throws Exception {
		final double result = testUser(SAMPLE_FILE,
				RecommenderSimilarity.EUCLIDEAN);
		Assert.assertEquals(1.55, roundTwoDecimals(result));
	}

	@Test
	public void testSampleDataUserAndTanimoto() throws Exception {
		final double result = testUser(SAMPLE_FILE,
				RecommenderSimilarity.TANIMOTO);
		Assert.assertEquals(1.0, result);
	}

	@Test
	public void testSampleDataUserAndLog() throws Exception {
		final double result = testUser(SAMPLE_FILE, RecommenderSimilarity.LOG);
		Assert.assertEquals(1.0, result);
	}

	@Test
	public void testSampleDataItemAndPearson() throws Exception {
		final double result = testItem(SAMPLE_FILE,
				RecommenderSimilarity.PEARSON);
		Assert.assertEquals(1.27, roundTwoDecimals(result));
	}

	@Test
	public void testSampleDataItemAndEuclidean() throws Exception {
		final double result = testItem(SAMPLE_FILE,
				RecommenderSimilarity.EUCLIDEAN);
		Assert.assertEquals(1.12, roundTwoDecimals(result));
	}

	@Test
	public void testSampleDataItemAndTanimoto() throws Exception {
		final double result = testItem(SAMPLE_FILE,
				RecommenderSimilarity.TANIMOTO);
		Assert.assertEquals(1.33, roundTwoDecimals(result));
	}

	@Test
	public void testSampleDataItemAndLog() throws Exception {
		final double result = testItem(SAMPLE_FILE, RecommenderSimilarity.LOG);
		Assert.assertEquals(1.03, roundTwoDecimals(result));
	}

}
