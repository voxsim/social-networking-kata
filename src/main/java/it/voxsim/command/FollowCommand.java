package it.voxsim.command;

import java.util.Calendar;

import it.voxsim.repository.LinkRepository;

public class FollowCommand implements Command {

	private LinkRepository repository;

	public FollowCommand(LinkRepository repository) {
		this.repository = repository;
	}

	@Override
	public String execute(String username, String followedUsername, Calendar timeOfExecution) {
		if(areDifferentUsers(username, followedUsername))
			repository.addLinkBetween(username, followedUsername);
		return "";
	}

	private boolean areDifferentUsers(String username, String followedUsername) {
		return !username.equals(followedUsername);
	}
}