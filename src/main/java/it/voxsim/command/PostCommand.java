package it.voxsim.command;

import java.util.Calendar;

import it.voxsim.LinkRepository;
import it.voxsim.MessageRepository;

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
