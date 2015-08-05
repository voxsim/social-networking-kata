package it.voxsim;

import java.util.Calendar;
import java.util.List;

public interface UserRepository {

	List<Message> retrieveMessagesByUsername(String username);

	void saveIfNotExist(String username);

	void addMessageTo(String username, String message, Calendar timeOfExecution);
}
