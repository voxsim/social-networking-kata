package it.voxsim.command;

import java.util.Calendar;

import it.voxsim.LinkRepository;

public class FollowCommand implements Command {

	private LinkRepository repository;

	public FollowCommand(LinkRepository repository) {
		this.repository = repository;
	}

	@Override
	public String execute(String username, String argument, Calendar timeOfExecution) {
		repository.addTo(username, argument);
		return "";
	}

}
