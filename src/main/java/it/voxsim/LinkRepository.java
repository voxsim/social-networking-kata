package it.voxsim;

import java.util.Set;

public interface LinkRepository {

	Set<String> retrieveByUsername(String username);

	void addTo(String username, String argument);

	void saveIfNotExist(String username);

}
