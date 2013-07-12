package org.tmme.ci.recommender.converter;

import org.apache.mahout.cf.taste.impl.model.mongodb.MongoDBDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class IdConverter implements Converter<String, Long> {

	@Autowired
	private MongoDBDataModel mongoDbDatamodel;

	@Override
	public Long convert(final String id) {
		final String longValue = mongoDbDatamodel.fromIdToLong(id, true);
		return Long.valueOf(longValue);
	}

}
