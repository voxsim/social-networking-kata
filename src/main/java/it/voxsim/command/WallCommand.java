package it.voxsim.command;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import it.voxsim.message.Message;
import it.voxsim.message.MultiMessagePrinter;
import it.voxsim.repository.LinkRepository;
import it.voxsim.repository.MessageRepository;

public class WallCommand implements Command {

	private MessageRepository messageRepository;
	private LinkRepository linkRepository;
	private MultiMessagePrinter printer;

	public WallCommand(MessageRepository repository, LinkRepository linkRepository, MultiMessagePrinter printer) {
		this.messageRepository = repository;
		this.linkRepository = linkRepository;
		this.printer = printer;
	}

	@Override
	public String execute(String username, String argument, Calendar timeOfExecution) {
		Set<String> links = retrieveLinks(username);
		List<Message> messages = retrieveMessagesFromLinks(links);
		Collections.sort(messages);
		return printer.print(username, messages, timeOfExecution);
	}

	private Set<String> retrieveLinks(String username) {
		Set<String> links = linkRepository.retrieveLinksByUsername(username);
		links.add(username);
		return links;
	}

	private List<Message> retrieveMessagesFromLinks(Set<String> links) {
		List<Message> messages = new ArrayList<Message>();
		Iterator<String> iterator = links.iterator();
		while (iterator.hasNext()) {
			String user = iterator.next();
			List<Message> messagesOfUser = messageRepository.retrieveMessagesByUsernameOrderedByTime(user);
			messages.addAll(messagesOfUser);
		}
		return messages;
	}

	public static WallCommand create(MessageRepository messageRepository, LinkRepository linkRepository) {
		MultiMessagePrinter printer = new MultiMessagePrinter("no messages in %{username} wall",
				"%{user} - %{description} (%{time})");
		return new WallCommand(messageRepository, linkRepository, printer);
	}
}