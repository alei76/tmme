package org.tmme.ci.recommender.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.core.convert.converter.Converter;

public class RecommenderConverter implements
		Converter<List<RecommendedItem>, List<String>> {

	@Override
	public List<String> convert(final List<RecommendedItem> recommendedItems) {
		final List<String> items = new ArrayList<String>();
		if (recommendedItems != null) {
			for (final RecommendedItem recommendedItem : recommendedItems) {
				items.add(String.valueOf(recommendedItem.getItemID()));
			}
		}
		return items;
	}

}
