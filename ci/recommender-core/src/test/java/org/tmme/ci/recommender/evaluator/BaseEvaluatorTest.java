package org.tmme.ci.recommender.evaluator;

import java.io.File;
import java.text.DecimalFormat;

import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.tmme.ci.recommender.cf.factory.RecommenderBuilderFactory;
import org.tmme.ci.recommender.cf.factory.RecommenderSimilarity;
import org.tmme.ci.recommender.cf.factory.RecommenderType;
import org.tmme.ci.recommender.cf.factory.impl.RecommenderBuilderFactoryImpl;
import org.tmme.ci.recommender.cf.factory.impl.RecommenderFactoryImpl;

public abstract class BaseEvaluatorTest<R> {

	protected static final String SAMPLE_FILE = "src/test/resources/sample4cf.csv";
	protected static final String GROUP_LENS_FILE = "src/test/resources/grouplens.csv";
	protected static final String CLIENT_FILE = "src/test/resources/client.csv";

	protected RecommenderBuilderFactory recommenderBuilderFactory;

	public BaseEvaluatorTest() {
		this.recommenderBuilderFactory = new RecommenderBuilderFactoryImpl(
				new RecommenderFactoryImpl());
	}

	protected R test(final String file, final RecommenderType type,
			final RecommenderSimilarity similarity) throws Exception {
		final File modelFile = new File(file);
		final DataModel model = new FileDataModel(modelFile);
		final RecommenderBuilder builder = recommenderBuilderFactory
				.createRecommenderBuilder(type, similarity, model);
		return evaluate(builder, model);
	}

	protected R testUser(final String file,
			final RecommenderSimilarity similarity) throws Exception {
		return test(file, RecommenderType.USER, similarity);
	}

	protected R testItem(final String file,
			final RecommenderSimilarity similarity) throws Exception {
		return test(file, RecommenderType.ITEM, similarity);
	}

	double roundTwoDecimals(final double d) {
		final DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
	}

	protected abstract R evaluate(RecommenderBuilder builder, DataModel model)
			throws Exception;

}
