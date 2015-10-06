package it.voxsim.repository;

import java.util.List;

public interface LinkRepository {

	List<String> retrieveLinksByUsername(String username);

	void addLinkBetween(String username, String followedUsername);

	void saveIfNotExist(String username);

}
