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
		Assert.assertEquals(1.29, roundTwoDecimals(result));
	}

	@Test
	public void testSampleDataUserAndLog() throws Exception {
		final double result = testUser(SAMPLE_FILE, RecommenderSimilarity.LOG);
		Assert.assertEquals(1.14, roundTwoDecimals(result));
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
	public void testClientDataUserAndPearson() throws Exception {
		final double result = testUser(CLIENT_FILE,
				RecommenderSimilarity.PEARSON);
		Assert.assertTrue(Double.isNaN(result));
	}

	@Test
	public void testClientDataUserAndEuclidean() throws Exception {
		final double result = testUser(CLIENT_FILE,
				RecommenderSimilarity.EUCLIDEAN);
		Assert.assertEquals(0.29, roundTwoDecimals(result));
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
		Assert.assertEquals(0.86, roundTwoDecimals(result));
	}

	@Test
	public void testClientDataItemAndTanimoto() throws Exception {
		final double result = testItem(CLIENT_FILE,
				RecommenderSimilarity.TANIMOTO);
		Assert.assertEquals(0.88, roundTwoDecimals(result));
	}

	@Test
	public void testClientDataItemAndLog() throws Exception {
		final double result = testItem(CLIENT_FILE, RecommenderSimilarity.LOG);
		Assert.assertEquals(0.92, roundTwoDecimals(result));
	}

	@Test
	public void testGroupLensDataUserAndPearson() throws Exception {
		final double result = testUser(GROUP_LENS_FILE,
				RecommenderSimilarity.PEARSON);
		Assert.assertEquals(1.23, roundTwoDecimals(result));
	}

	@Test
	public void testGroupLensDataUserAndEuclidean() throws Exception {
		final double result = testUser(GROUP_LENS_FILE,
				RecommenderSimilarity.EUCLIDEAN);
		Assert.assertEquals(1.24, roundTwoDecimals(result));
	}

	@Test
	public void testGroupLensDataUserAndTanimoto() throws Exception {
		final double result = testUser(GROUP_LENS_FILE,
				RecommenderSimilarity.TANIMOTO);
		Assert.assertEquals(1.13, roundTwoDecimals(result));
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
		Assert.assertEquals(1.08, roundTwoDecimals(result));
	}

	@Test
	public void testGroupLensDataItemAndEuclidean() throws Exception {
		final double result = testItem(GROUP_LENS_FILE,
				RecommenderSimilarity.EUCLIDEAN);
		Assert.assertEquals(1.02, roundTwoDecimals(result));
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
