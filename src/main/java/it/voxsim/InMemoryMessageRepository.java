package it.voxsim;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import it.voxsim.message.Message;

public class InMemoryMessageRepository implements MessageRepository {

	private HashMap<String, List<Message>> messagesByUsername = new HashMap<String, List<Message>>();

	public List<Message> retrieveMessagesByUsername(String username) {
		List<Message> messages = messagesByUsername.get(username);
		if (messages == null)
			return new ArrayList<Message>();
		return messages;
	}

	public void saveIfNotExist(String username) {
		if (messagesByUsername.get(username) == null)
			messagesByUsername.put(username, new ArrayList<Message>());
	}

	public void addMessageTo(String username, String message, Calendar timeOfExecution) {
		List<Message> messages = messagesByUsername.get(username);
		messages.add(Message.create(username, message, timeOfExecution));
	}
}