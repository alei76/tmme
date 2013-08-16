package org.tmme.ci.recommender.evaluator;

import junit.framework.Assert;

import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.common.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.tmme.ci.recommender.cf.factory.RecommenderSimilarity;

public class RecommenderIRStatsEvaluatorTest extends
		BaseEvaluatorTest<IRStatistics> {

	private RecommenderIRStatsEvaluator evaluator;

	@Before
	public void setUp() {
		RandomUtils.useTestSeed();
		evaluator = new GenericRecommenderIRStatsEvaluator();
	}

	@Override
	protected IRStatistics evaluate(final RecommenderBuilder builder,
			final DataModel model) throws Exception {
		return evaluator.evaluate(builder, null, model, null, 2,
				GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD, 1.0);
	}

	// Sample

	@Test
	public void testSampleDataUserAndPearson() throws Exception {
		final IRStatistics result = testUser(SAMPLE_FILE,
				RecommenderSimilarity.PEARSON);
		Assert.assertEquals(0.75, result.getPrecision());
		Assert.assertEquals(1.0, result.getRecall());
	}

	@Test
	public void testSampleDataUserAndEuclidean() throws Exception {
		final IRStatistics result = testUser(SAMPLE_FILE,
				RecommenderSimilarity.EUCLIDEAN);
		Assert.assertEquals(0.5, result.getPrecision());
		Assert.assertEquals(0.5, result.getRecall());
	}

	@Test
	public void testSampleDataUserAndTanimoto() throws Exception {
		final IRStatistics result = testUser(SAMPLE_FILE,
				RecommenderSimilarity.TANIMOTO);
		Assert.assertEquals(0.75, result.getPrecision());
		Assert.assertEquals(1.0, result.getRecall());
	}

	@Test
	public void testSampleDataUserAndLog() throws Exception {
		final IRStatistics result = testUser(SAMPLE_FILE,
				RecommenderSimilarity.LOG);
		Assert.assertEquals(0.75, result.getPrecision());
		Assert.assertEquals(1.0, result.getRecall());
	}

	@Test
	public void testSampleDataItemAndPearson() throws Exception {
		final IRStatistics result = testItem(SAMPLE_FILE,
				RecommenderSimilarity.PEARSON);
		Assert.assertEquals(0.75, result.getPrecision());
		Assert.assertEquals(1.0, result.getRecall());
	}

	@Test
	public void testSampleDataItemAndEuclidean() throws Exception {
		final IRStatistics result = testItem(SAMPLE_FILE,
				RecommenderSimilarity.EUCLIDEAN);
		Assert.assertEquals(0.5, result.getPrecision());
		Assert.assertEquals(1.0, result.getRecall());
	}

	@Test
	public void testSampleDataItemAndTanimoto() throws Exception {
		final IRStatistics result = testItem(SAMPLE_FILE,
				RecommenderSimilarity.TANIMOTO);
		Assert.assertEquals(0.5, result.getPrecision());
		Assert.assertEquals(1.0, result.getRecall());
	}

	@Test
	public void testSampleDataItemAndLog() throws Exception {
		final IRStatistics result = testItem(SAMPLE_FILE,
				RecommenderSimilarity.LOG);
		Assert.assertEquals(0.25, result.getPrecision());
		Assert.assertEquals(0.5, result.getRecall());
	}

}
