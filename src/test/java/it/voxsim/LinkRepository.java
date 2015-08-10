package it.voxsim;

import java.util.List;

public interface LinkRepository {

	List<String> retrieveByUsername(String username);

	void addTo(String username, String argument);

	void saveIfNotExist(String username);

}
