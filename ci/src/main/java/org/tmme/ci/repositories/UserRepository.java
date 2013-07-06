package org.tmme.ci.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.ci.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	User findByUsername(String username);

}