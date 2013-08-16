package org.tmme.ci.recommender.evaluator;

import org.apache.commons.lang.Validate;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.common.RandomUtils;

public abstract class RecommenderEvaluatorTest extends
		BaseEvaluatorTest<Double> {

	protected static final String SAMPLE_FILE = "src/test/resources/sample.csv";
	protected static final String GROUP_LENS_FILE = "src/test/resources/grouplens.csv";
	protected static final String APPUP_FILE = "src/test/resources/appup.csv";

	protected RecommenderEvaluator recommenderEvaluator;

	protected void setUp(final RecommenderEvaluator evaluator) {
		Validate.notNull(evaluator);
		RandomUtils.useTestSeed();
		recommenderEvaluator = evaluator;
	}

	@Override
	protected Double evaluate(final RecommenderBuilder builder,
			final DataModel model) throws Exception {
		// Use 70% of the data to train; test using the other 30%.
		return recommenderEvaluator.evaluate(builder, null, model, 0.7, 1.0);
	}

}
