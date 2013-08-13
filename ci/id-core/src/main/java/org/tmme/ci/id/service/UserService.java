package org.tmme.ci.id.service;

import org.tmme.ci.models.User;

public interface UserService {

	void createUser(User user);

	void autoLogin(String email);

	User getByEmail(String email);

}
