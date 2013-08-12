package org.tmme.ci.recommender.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.apache.mahout.cf.taste.impl.model.mongodb.MongoDBDataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.core.convert.converter.Converter;

public class RecommendedItemsConverter implements
		Converter<List<RecommendedItem>, List<String>> {

	private final MongoDBDataModel mongoDbDatamodel;

	public RecommendedItemsConverter(final MongoDBDataModel mongoDbDataModel) {
		Validate.notNull(mongoDbDataModel);
		this.mongoDbDatamodel = mongoDbDataModel;
	}

	@Override
	public List<String> convert(final List<RecommendedItem> recommendedItems) {
		final List<String> items = new ArrayList<String>();
		if (recommendedItems != null) {
			for (final RecommendedItem recommendedItem : recommendedItems) {
				items.add(mongoDbDatamodel.fromLongToId(recommendedItem
						.getItemID()));
			}
		}
		return items;
	}

}
