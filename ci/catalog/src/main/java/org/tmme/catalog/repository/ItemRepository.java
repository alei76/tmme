package org.tmme.catalog.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.catalog.model.Item;

public interface ItemRepository extends MongoRepository<Item, String> {

	List<Item> findByTypeName(String name);

}
