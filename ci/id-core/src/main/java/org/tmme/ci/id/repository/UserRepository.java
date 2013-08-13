package org.tmme.ci.id.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.tmme.ci.models.User;

public interface UserRepository extends MongoRepository<User, String> {

	User findByEmail(String email);

}
