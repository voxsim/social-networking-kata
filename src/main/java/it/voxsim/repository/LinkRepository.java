package it.voxsim.repository;

import java.util.Set;

public interface LinkRepository {

	Set<String> retrieveLinksByUsername(String username);

	void addLinkBetween(String username, String followedUsername);

	void saveIfNotExist(String username);

}
