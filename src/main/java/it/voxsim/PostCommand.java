package it.voxsim;

import java.util.Calendar;

public class PostCommand extends Command {

	private MessageRepository repository;

	public PostCommand(MessageRepository repository) {
		this.repository = repository;
	}

	@Override
	public String execute(String username, String argument, Calendar timeOfExecution) {
		repository.saveIfNotExist(username);
		repository.addMessageTo(username, argument, timeOfExecution);
		return "";
	}
}
