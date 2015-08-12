package it.voxsim.command;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import it.voxsim.LinkRepository;
import it.voxsim.MessageRepository;
import it.voxsim.message.Message;

public class WallCommand implements Command {

	private MessageRepository messageRepository;
	private LinkRepository linkRepository;

	public WallCommand(MessageRepository repository, LinkRepository linkRepository) {
		this.messageRepository = repository;
		this.linkRepository = linkRepository;
	}

	@Override
	public String execute(String username, String argument, Calendar timeOfExecution) {
		Set<String> links = retrieveLinks(username);
		List<Message> messages = retrieveMessagesFromLinks(links);
		Collections.sort(messages);

		if (messages.isEmpty())
			return "no messages in " + username + " wall";

		String output = "";
		String separator = "";
		for (Message message : messages) {
			output += separator + message.description("%{user} - %{description} (%{time})", timeOfExecution);
			separator = "\n";
		}
		return output;
	}

	private Set<String> retrieveLinks(String username) {
		Set<String> links = linkRepository.retrieveByUsername(username);
		links.add(username);
		return links;
	}

	private List<Message> retrieveMessagesFromLinks(Set<String> links) {
		List<Message> messages = new ArrayList<Message>();
		Iterator<String> iterator = links.iterator();
		while (iterator.hasNext()) {
			String user = iterator.next();
			List<Message> messagesOfUser = messageRepository.retrieveMessagesByUsername(user);
			messages.addAll(messagesOfUser);
		}
		return messages;
	}
}