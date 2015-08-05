package it.voxsim;

import java.util.List;

public interface UserRepository {

	List<String> retrieveMessagesByUsername(String username);

	void saveIfNotExist(String username);

	void addMessageTo(String username, String message);
}
