package it.voxsim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryLinkRepository implements LinkRepository {
	
	private Map<String, List<String>> linksByUsername = new HashMap<String, List<String>>();

	public List<String> retrieveByUsername(String username) {
		return linksByUsername.get(username);
	}
	
	public void saveIfNotExist(String username) {
		if (linksByUsername.get(username) == null)
			linksByUsername.put(username, new ArrayList<String>());
	}

	public void addTo(String username, String argument) {
		List<String> links = linksByUsername.get(username);
		links.add(argument);
	}
}