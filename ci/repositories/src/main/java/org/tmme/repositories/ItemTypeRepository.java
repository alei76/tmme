package org.tmme.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.repositories.model.ItemType;

public interface ItemTypeRepository extends MongoRepository<ItemType, String> {

	ItemType findByName(String name);

}
