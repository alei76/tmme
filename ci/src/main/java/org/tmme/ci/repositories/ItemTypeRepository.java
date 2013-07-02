package org.tmme.ci.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.ci.model.ItemType;

public interface ItemTypeRepository extends MongoRepository<ItemType, String> {

	ItemType findByName(String name);

}
