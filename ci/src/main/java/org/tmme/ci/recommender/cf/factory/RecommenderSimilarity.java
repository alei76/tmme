package org.tmme.ci.recommender.cf.factory;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;

public enum RecommenderSimilarity {

	PEARSON {
		@Override
		public Object createNewSimilarity(final DataModel dataModel)
				throws TasteException {
			return new PearsonCorrelationSimilarity(dataModel);
		}
	},
	TANIMOTO {
		@Override
		public Object createNewSimilarity(final DataModel dataModel) {
			return new TanimotoCoefficientSimilarity(dataModel);
		}
	},
	LOG {
		@Override
		public Object createNewSimilarity(final DataModel dataModel) {
			return new LogLikelihoodSimilarity(dataModel);
		}
	},
	EUCLIDEAN {
		@Override
		public Object createNewSimilarity(final DataModel dataModel)
				throws TasteException {
			return new EuclideanDistanceSimilarity(dataModel);
		}
	},
	NONE {
		@Override
		public Object createNewSimilarity(final DataModel dataModel)
				throws TasteException {
			return new Object();
		}
	};

	public abstract Object createNewSimilarity(final DataModel dataModel)
			throws TasteException;

}
