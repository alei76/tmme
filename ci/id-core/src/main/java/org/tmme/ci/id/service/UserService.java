package org.tmme.ci.id.service;

import org.tmme.ci.id.models.User;

public interface UserService {

	void createUser(User user);

	void autoLogin(String email);

}
