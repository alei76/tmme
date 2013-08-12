package org.tmme.ci.recommender.cf.factory;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.slopeone.SlopeOneRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public enum RecommenderType {

	USER {
		@Override
		public Recommender createNewRecommender(final DataModel dataModel,
				final RecommenderSimilarity similarity)
				throws TasteException {
			final UserSimilarity userSimilarity = (UserSimilarity) similarity
					.createNewSimilarity(dataModel);
			final UserNeighborhood neighborhood = new NearestNUserNeighborhood(
					2, userSimilarity, dataModel);
			return new GenericUserBasedRecommender(dataModel, neighborhood,
					userSimilarity);
		}
	},
	ITEM {
		@Override
		public Recommender createNewRecommender(final DataModel dataModel,
				final RecommenderSimilarity similarity)
				throws TasteException {
			final ItemSimilarity itemSimilarity = (ItemSimilarity) similarity
					.createNewSimilarity(dataModel);
			return new GenericItemBasedRecommender(dataModel, itemSimilarity);
		}
	},
	SLOPEONE {
		@Override
		public Recommender createNewRecommender(final DataModel dataModel,
				final RecommenderSimilarity similarity)
				throws TasteException {
			return new SlopeOneRecommender(dataModel);
		}
	};

	public abstract Recommender createNewRecommender(DataModel dataModel,
			RecommenderSimilarity similarity)
			throws TasteException;

}
