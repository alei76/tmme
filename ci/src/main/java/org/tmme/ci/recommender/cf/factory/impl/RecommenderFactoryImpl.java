package org.tmme.ci.recommender.cf.factory.impl;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.tmme.ci.recommender.cf.factory.RecommenderFactory;
import org.tmme.ci.recommender.cf.factory.RecommenderSimilarity;
import org.tmme.ci.recommender.cf.factory.RecommenderType;

public class RecommenderFactoryImpl implements
		RecommenderFactory {

	@Override
	public Recommender createRecommender(
			final RecommenderType type,
			final RecommenderSimilarity similarity,
			final DataModel dataModel) throws TasteException {
		return type.createNewRecommender(dataModel, similarity);
	}
}
