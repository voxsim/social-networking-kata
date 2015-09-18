package it.voxsim.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import it.voxsim.message.Message;

public class InMemoryMessageRepository implements MessageRepository {

	private HashMap<String, TreeSet<Message>> messagesByUsername = new HashMap<String, TreeSet<Message>>();

	public List<Message> retrieveMessagesByUsernameOrderedByTime(String username) {
		ArrayList<Message> messages = new ArrayList<Message>();
		TreeSet<Message> messagesSet = messagesByUsername.get(username);
		if (messagesSet != null)
			messages.addAll(messagesSet);
		return messages;
	}

	public void saveIfNotExist(String username) {
		if (messagesByUsername.get(username) == null)
			messagesByUsername.put(username, new TreeSet<Message>());
	}

	public void addMessageTo(String username, String message, Calendar timeOfExecution) {
		TreeSet<Message> messages = messagesByUsername.get(username);
		messages.add(Message.create(username, message, timeOfExecution));
	}
}