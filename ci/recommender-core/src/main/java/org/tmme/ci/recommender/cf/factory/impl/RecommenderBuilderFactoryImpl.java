package org.tmme.ci.recommender.cf.factory.impl;

import org.apache.commons.lang.Validate;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.tmme.ci.recommender.cf.factory.RecommenderBuilderFactory;
import org.tmme.ci.recommender.cf.factory.RecommenderFactory;
import org.tmme.ci.recommender.cf.factory.RecommenderSimilarity;
import org.tmme.ci.recommender.cf.factory.RecommenderType;

public class RecommenderBuilderFactoryImpl implements RecommenderBuilderFactory {

	private final RecommenderFactory recommenderFactory;

	public RecommenderBuilderFactoryImpl(
			final RecommenderFactory recommenderFactory) {
		Validate.notNull(recommenderFactory);
		this.recommenderFactory = recommenderFactory;
	}

	@Override
	public RecommenderBuilder createRecommenderBuilder(
			final RecommenderType type, final RecommenderSimilarity similarity,
			final DataModel dataModel) {

		return new RecommenderBuilder() {
			@Override
			public Recommender buildRecommender(final DataModel dataModel)
					throws TasteException {
				return recommenderFactory.createRecommender(type, similarity,
						dataModel);
			}
		};
	}

}
