package it.voxsim.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InMemoryLinkRepository implements LinkRepository {

	private Map<String, Set<String>> linksByUsername = new HashMap<String, Set<String>>();

	public Set<String> retrieveByUsername(String username) {
		Set<String> links = linksByUsername.get(username);
		if(links == null)
			return new HashSet<String>();
		return links;
	}

	public void saveIfNotExist(String username) {
		if (linksByUsername.get(username) == null)
			linksByUsername.put(username, new HashSet<String>());
	}

	public void addTo(String username, String argument) {
		Set<String> links = linksByUsername.get(username);
		links.add(argument);
	}
}