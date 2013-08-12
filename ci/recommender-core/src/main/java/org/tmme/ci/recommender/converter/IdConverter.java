package org.tmme.ci.recommender.converter;

import org.apache.commons.lang.Validate;
import org.apache.mahout.cf.taste.impl.model.mongodb.MongoDBDataModel;
import org.springframework.core.convert.converter.Converter;

public class IdConverter implements Converter<String, Long> {

	private final MongoDBDataModel mongoDbDatamodel;

	public IdConverter(final MongoDBDataModel mongoDbDataModel) {
		Validate.notNull(mongoDbDataModel);
		this.mongoDbDatamodel = mongoDbDataModel;
	}

	@Override
	public Long convert(final String id) {
		final String longValue = mongoDbDatamodel.fromIdToLong(id, true);
		return Long.valueOf(longValue);
	}

}
