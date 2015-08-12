package it.voxsim;

import java.util.Calendar;

public class PostCommand implements Command {

	private MessageRepository messageRepository;
	private LinkRepository linkRepository;

	public PostCommand(MessageRepository repository, LinkRepository linkRepository) {
		this.messageRepository = repository;
		this.linkRepository = linkRepository;
	}

	@Override
	public String execute(String username, String argument, Calendar timeOfExecution) {
		messageRepository.saveIfNotExist(username);
		linkRepository.saveIfNotExist(username);
		messageRepository.addMessageTo(username, argument, timeOfExecution);
		return "";
	}
}
