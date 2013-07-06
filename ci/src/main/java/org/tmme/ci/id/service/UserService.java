package org.tmme.ci.id.service;

import org.tmme.ci.model.User;

public interface UserService {

	void createUser(User user);

	void autoLogin(String username);

}
