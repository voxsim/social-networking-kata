package it.voxsim.repository;

import java.util.Calendar;
import java.util.List;

import it.voxsim.message.Message;

public interface MessageRepository {

	List<Message> retrieveMessagesByUsernameOrderedByTime(String username);

	void saveIfNotExist(String username);

	void addMessageTo(String username, String message, Calendar timeOfExecution);
}
