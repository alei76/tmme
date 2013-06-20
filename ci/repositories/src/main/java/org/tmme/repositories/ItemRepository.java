package org.tmme.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.repositories.model.Item;

public interface ItemRepository extends MongoRepository<Item, String> {

	List<Item> findByTypeName(String name);

}
