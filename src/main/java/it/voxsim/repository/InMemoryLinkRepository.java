package it.voxsim.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.voxsim.exception.UserNotExistsException;

public class InMemoryLinkRepository implements LinkRepository {

	private Map<String, Set<String>> linksByUsername = new HashMap<String, Set<String>>();

	public List<String> retrieveLinksByUsername(String username) {
		Set<String> links = linksByUsername.get(username);
		ArrayList<String> result = new ArrayList<String>();

		if (links != null)
			result.addAll(links);

		return result;
	}

	public void saveIfNotExist(String username) {
		if (linksByUsername.get(username) == null)
			linksByUsername.put(username, new HashSet<String>());
	}

	public void addLinkBetween(String username, String argument) {
		Set<String> links = linksByUsername.get(username);

        if(links == null)
            throw new UserNotExistsException();

		links.add(argument);
	}
}
