package org.tmme.ci.catalog.repository.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.tmme.ci.catalog.repository.CatalogRepository;
import org.tmme.ci.model.Item;

@Repository
public class CatalogRepositoryImpl implements CatalogRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Resource
	private List<String> collectionBlackList;

	@Override
	public Set<String> getCollectionNames() {
		final Set<String> collections = mongoTemplate.getCollectionNames();
		CollectionUtils.filter(collections, new Predicate() {
			@Override
			public boolean evaluate(final Object object) {
				return notBlackListed((String) object);
			}
		});
		return collections;
	}

	@Override
	public void createCollection(final String name) {
		if (!mongoTemplate.collectionExists(name)) {
			mongoTemplate.createCollection(name);
		}
	}

	@Override
	public List<Item> findItemsByCollectionName(final String name) {
		return notBlackListed(name) ? mongoTemplate.findAll(Item.class, name)
				: Collections.<Item> emptyList();
	}

	@Override
	public void save(final Item item, final String name) {
		if (notBlackListed(name)) {
			mongoTemplate.insert(item, name);
		}
	}

	@Override
	public void save(final List<Item> items, final String name) {
		if (notBlackListed(name)) {
			mongoTemplate.insert(items, name);
		}
	}

	private boolean notBlackListed(final String collectionName) {
		return !collectionBlackList.contains(collectionName);
	}

	@Override
	public Item findById(final String itemId, final String collectionName) {
		Item item = null;
		if (notBlackListed(collectionName)) {
			item = mongoTemplate.findById(itemId, Item.class, collectionName);
		}
		return item;
	}

	@Override
	public List<Item> findItemsByIds(final List<String> ids) {
		List<Item> items = Collections.<Item> emptyList();
		if (CollectionUtils.isNotEmpty(ids)) {
			final Query query = new Query(Criteria.where("id").in(ids));
			items = mongoTemplate.find(query, Item.class);
		}
		return items;
	}

}
