package it.voxsim;

import java.util.HashMap;

public class InMemoryUserRepository implements UserRepository {
	
	HashMap<String, User> users = new HashMap<String, User>();

	public User findByUsername(String username) {
		return users.get(username);
	}

	public void save(String username) {
		users.put(username, new User());
	}

}
