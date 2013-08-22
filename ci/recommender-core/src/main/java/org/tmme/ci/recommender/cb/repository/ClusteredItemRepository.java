package org.tmme.ci.recommender.cb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.ci.recommender.cb.model.ClusteredItem;

public interface ClusteredItemRepository extends
		MongoRepository<ClusteredItem, String> {

	List<ClusteredItem> findByClusterId(String clusterId);

	ClusteredItem findByItemId(String itemId);

}