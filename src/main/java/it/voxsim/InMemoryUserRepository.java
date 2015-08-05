package it.voxsim;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class InMemoryUserRepository implements UserRepository {

	private HashMap<String, List<Message>> users = new HashMap<String, List<Message>>();

	public List<Message> retrieveMessagesByUsername(String username) {
		return users.get(username);
	}

	public void saveIfNotExist(String username) {
		if (users.get(username) == null)
			users.put(username, new ArrayList<Message>());
	}

	public void addMessageTo(String username, String message, Calendar timeOfExecution) {
		List<Message> messages = users.get(username);
		messages.add(new Message(message, timeOfExecution));
	}
}