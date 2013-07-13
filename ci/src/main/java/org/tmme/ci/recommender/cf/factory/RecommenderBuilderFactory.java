package org.tmme.ci.recommender.cf.factory;

import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.model.DataModel;

public interface RecommenderBuilderFactory {

	RecommenderBuilder createRecommenderBuilder(
			final RecommenderType type,
			final RecommenderSimilarity similarity,
			final DataModel dataModel);

}
