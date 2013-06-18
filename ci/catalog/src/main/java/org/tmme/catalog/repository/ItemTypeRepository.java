package org.tmme.catalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.catalog.model.ItemType;

public interface ItemTypeRepository extends MongoRepository<ItemType, String> {

    ItemType findByName(String name);

}
