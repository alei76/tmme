package org.tmme.ci.clients;

import org.tmme.ci.models.User;

public interface UserClient {

	User findByEmail(String email);

}
