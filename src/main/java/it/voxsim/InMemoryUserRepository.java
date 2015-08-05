package it.voxsim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryUserRepository implements UserRepository {

	private HashMap<String, List<String>> users = new HashMap<String, List<String>>();

	public List<String> retrieveMessagesByUsername(String username) {
		return users.get(username);
	}

	public void saveIfNotExist(String username) {
		if (users.get(username) == null)
			users.put(username, new ArrayList<String>());
	}

	public void addMessageTo(String username, String message) {
		List<String> messages = users.get(username);
		messages.add(message);
	}

}
