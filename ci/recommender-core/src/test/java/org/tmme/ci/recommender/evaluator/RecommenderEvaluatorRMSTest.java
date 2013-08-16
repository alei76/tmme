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

	// Sample

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

	// Client

	@Test
	public void testClientDataUserAndPearson() throws Exception {
		final double result = testUser(CLIENT_FILE,
				RecommenderSimilarity.PEARSON);
		Assert.assertTrue(Double.isNaN(result));
	}

	@Test
	public void testClientDataUserAndEuclidean() throws Exception {
		final double result = testUser(CLIENT_FILE,
				RecommenderSimilarity.EUCLIDEAN);
		Assert.assertEquals(0.0, result);
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
		Assert.assertEquals(0.76, roundTwoDecimals(result));
	}

	@Test
	public void testClientDataItemAndTanimoto() throws Exception {
		final double result = testItem(CLIENT_FILE,
				RecommenderSimilarity.TANIMOTO);
		Assert.assertEquals(0.64, roundTwoDecimals(result));
	}

	@Test
	public void testClientDataItemAndLog() throws Exception {
		final double result = testItem(CLIENT_FILE, RecommenderSimilarity.LOG);
		Assert.assertEquals(0.81, roundTwoDecimals(result));
	}

	@Test
	public void testGroupLensDataUserAndPearson() throws Exception {
		final double result = testUser(GROUP_LENS_FILE,
				RecommenderSimilarity.PEARSON);
		Assert.assertEquals(1.28, roundTwoDecimals(result));
	}

	@Test
	public void testGroupLensDataUserAndEuclidean() throws Exception {
		final double result = testUser(GROUP_LENS_FILE,
				RecommenderSimilarity.EUCLIDEAN);
		Assert.assertEquals(1.16, roundTwoDecimals(result));
	}

	@Test
	public void testGroupLensDataUserAndTanimoto() throws Exception {
		final double result = testUser(GROUP_LENS_FILE,
				RecommenderSimilarity.TANIMOTO);
		Assert.assertEquals(1.14, roundTwoDecimals(result));
	}

	@Test
	public void testGroupLensDataUserAndLog() throws Exception {
		final double result = testUser(GROUP_LENS_FILE,
				RecommenderSimilarity.LOG);
		Assert.assertEquals(1.14, roundTwoDecimals(result));
	}

	@Test
	public void testGroupLensDataItemAndPearson() throws Exception {
		final double result = testItem(GROUP_LENS_FILE,
				RecommenderSimilarity.PEARSON);
		Assert.assertEquals(1.09, roundTwoDecimals(result));
	}

	@Test
	public void testGroupLensDataItemAndEuclidean() throws Exception {
		final double result = testItem(GROUP_LENS_FILE,
				RecommenderSimilarity.EUCLIDEAN);
		Assert.assertEquals(1.03, roundTwoDecimals(result));
	}

	@Test
	public void testGroupLensDataItemAndTanimoto() throws Exception {
		final double result = testItem(GROUP_LENS_FILE,
				RecommenderSimilarity.TANIMOTO);
		Assert.assertEquals(1.0, roundTwoDecimals(result));
	}

	@Test
	public void testGroupLensDataItemAndLog() throws Exception {
		final double result = testItem(GROUP_LENS_FILE,
				RecommenderSimilarity.LOG);
		Assert.assertEquals(1.02, roundTwoDecimals(result));
	}

}
