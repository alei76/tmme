package org.tmme.ci.recommender.cf.factory;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.Recommender;

public interface RecommenderFactory {

	Recommender createRecommender(
			final RecommenderType type,
			final RecommenderSimilarity similarity,
			final DataModel dataModel) throws TasteException;

}
