package org.tmme.ci.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.ci.model.Item;

public interface ItemRepository extends MongoRepository<Item, String> {

	List<Item> findByTypeName(String name);

}
