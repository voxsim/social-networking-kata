package it.voxsim;

import java.util.Calendar;

public class FollowCommand extends Command {

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
