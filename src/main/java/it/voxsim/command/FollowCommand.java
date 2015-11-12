package it.voxsim.command;

import java.util.Calendar;

import it.voxsim.repository.LinkRepository;

import it.voxsim.exception.UserNotExistsException;

public class FollowCommand implements Command {

	private LinkRepository repository;

	public FollowCommand(LinkRepository repository) {
		this.repository = repository;
	}

	public String execute(String username, String followedUsername, Calendar timeOfExecution) {
        try {
            if(areDifferentUsers(username, followedUsername))
                repository.addLinkBetween(username, followedUsername);
		    return "";
        } catch(UserNotExistsException e) {
            return "Something went wrong, please be sure that Alice or Bob exists.";
        }
	}

	private boolean areDifferentUsers(String username, String followedUsername) {
		return !username.equals(followedUsername);
	}
}
