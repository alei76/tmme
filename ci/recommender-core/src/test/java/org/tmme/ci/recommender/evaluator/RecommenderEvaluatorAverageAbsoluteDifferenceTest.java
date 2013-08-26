package org.tmme.ci.recommender.evaluator;

import junit.framework.Assert;

import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.junit.Before;
import org.junit.Test;
import org.tmme.ci.recommender.cf.factory.RecommenderSimilarity;

public class RecommenderEvaluatorAverageAbsoluteDifferenceTest extends
		RecommenderEvaluatorTest {

	@Before
	public void setUp() {
		super.setUp(new AverageAbsoluteDifferenceRecommenderEvaluator());
	}

	// Sample

	@Test
	public void testSampleDataUserAndPearson() throws Exception {
		final double result = testUser(SAMPLE_FILE,
				RecommenderSimilarity.PEARSON);
		Assert.assertTrue(Double.isNaN(result));
	}

	@Test
	public void testSampleDataUserAndEuclidean() throws Exception {
		final double result = testUser(SAMPLE_FILE,
				RecommenderSimilarity.EUCLIDEAN);
		Assert.assertEquals(1.0, roundTwoDecimals(result));
	}

	@Test
	public void testSampleDataUserAndTanimoto() throws Exception {
		final double result = testUser(SAMPLE_FILE,
				RecommenderSimilarity.TANIMOTO);
		Assert.assertEquals(1.125, result);
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
		Assert.assertTrue(Double.isNaN(result));
	}

	@Test
	public void testSampleDataItemAndEuclidean() throws Exception {
		final double result = testItem(SAMPLE_FILE,
				RecommenderSimilarity.EUCLIDEAN);
		Assert.assertEquals(0.45, roundTwoDecimals(result));
	}

	@Test
	public void testSampleDataItemAndTanimoto() throws Exception {
		final double result = testItem(SAMPLE_FILE,
				RecommenderSimilarity.TANIMOTO);
		Assert.assertEquals(0.67, roundTwoDecimals(result));
	}

	@Test
	public void testSampleDataItemAndLog() throws Exception {
		final double result = testItem(SAMPLE_FILE, RecommenderSimilarity.LOG);
		Assert.assertEquals(0.44, roundTwoDecimals(result));
	}

	// Client

	@Test
	public void testClientDataUserAndEuclidean() throws Exception {
		final double result = testUser(CLIENT_FILE,
				RecommenderSimilarity.EUCLIDEAN);
		Assert.assertEquals(0.17, roundTwoDecimals(result));
	}

	@Test
	public void testClientDataUserAndTanimoto() throws Exception {
		final double result = testUser(CLIENT_FILE,
				RecommenderSimilarity.TANIMOTO);
		Assert.assertEquals(0.0, result);
	}

	@Test
	public void testClientDataUserAndLog() throws Exception {
		final double result = testUser(CLIENT_FILE, RecommenderSimilarity.LOG);
		Assert.assertEquals(0.0, result);
	}

	@Test
	public void testClientDataItemAndPearson() throws Exception {
		final double result = testItem(CLIENT_FILE,
				RecommenderSimilarity.PEARSON);
		Assert.assertEquals(0.0, roundTwoDecimals(result));
	}

	@Test
	public void testClientDataItemAndEuclidean() throws Exception {
		final double result = testItem(CLIENT_FILE,
				RecommenderSimilarity.EUCLIDEAN);
		Assert.assertEquals(0.42, roundTwoDecimals(result));
	}

	@Test
	public void testClientDataItemAndTanimoto() throws Exception {
		final double result = testItem(CLIENT_FILE,
				RecommenderSimilarity.TANIMOTO);
		Assert.assertEquals(0.42, roundTwoDecimals(result));
	}

	@Test
	public void testClientDataItemAndLog() throws Exception {
		final double result = testItem(CLIENT_FILE, RecommenderSimilarity.LOG);
		Assert.assertEquals(0.47, roundTwoDecimals(result));
	}

	// GroupLens

	@Test
	public void testGroupLensDataUserAndPearson() throws Exception {
		final double result = testUser(GROUP_LENS_FILE,
				RecommenderSimilarity.PEARSON);
		Assert.assertEquals(0.95, roundTwoDecimals(result));
	}

	@Test
	public void testGroupLensDataUserAndEuclidean() throws Exception {
		final double result = testUser(GROUP_LENS_FILE,
				RecommenderSimilarity.EUCLIDEAN);
		Assert.assertEquals(0.92, roundTwoDecimals(result));
	}

	@Test
	public void testGroupLensDataUserAndTanimoto() throws Exception {
		final double result = testUser(GROUP_LENS_FILE,
				RecommenderSimilarity.TANIMOTO);
		Assert.assertEquals(0.87, roundTwoDecimals(result));
	}

	@Test
	public void testGroupLensDataUserAndLog() throws Exception {
		final double result = testUser(GROUP_LENS_FILE,
				RecommenderSimilarity.LOG);
		Assert.assertEquals(0.89, roundTwoDecimals(result));
	}

	@Test
	public void testGroupLensDataItemAndPearson() throws Exception {
		final double result = testItem(GROUP_LENS_FILE,
				RecommenderSimilarity.PEARSON);
		Assert.assertEquals(0.84, roundTwoDecimals(result));
	}

	@Test
	public void testGroupLensDataItemAndEuclidean() throws Exception {
		final double result = testItem(GROUP_LENS_FILE,
				RecommenderSimilarity.EUCLIDEAN);
		Assert.assertEquals(0.82, roundTwoDecimals(result));
	}

	@Test
	public void testGroupLensDataItemAndTanimoto() throws Exception {
		final double result = testItem(GROUP_LENS_FILE,
				RecommenderSimilarity.TANIMOTO);
		Assert.assertEquals(0.79, roundTwoDecimals(result));
	}

	@Test
	public void testGroupLensDataItemAndLog() throws Exception {
		final double result = testItem(GROUP_LENS_FILE,
				RecommenderSimilarity.LOG);
		Assert.assertEquals(0.81, roundTwoDecimals(result));
	}

}
