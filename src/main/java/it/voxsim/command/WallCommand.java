package it.voxsim.command;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import it.voxsim.DeltaTimeTranslator;
import it.voxsim.LinkRepository;
import it.voxsim.Message;
import it.voxsim.MessageRepository;

public class WallCommand implements Command {

	private MessageRepository messageRepository;
	private LinkRepository linkRepository;
	private DeltaTimeTranslator deltaTimeTranslator;

	public WallCommand(MessageRepository repository, LinkRepository linkRepository,
			DeltaTimeTranslator deltaTimeTranslator) {
		this.messageRepository = repository;
		this.linkRepository = linkRepository;
		this.deltaTimeTranslator = deltaTimeTranslator;
	}

	@Override
	public String execute(String username, String argument, Calendar timeOfExecution) {
		Set<String> links = retrieveLinks(username);
		List<Message> messages = retrieveMessagesFromLinks(links);
		Collections.sort(messages, new MessageComparator());

		if (messages.isEmpty())
			return "no messages in " + username + " wall";

		String output = "";
		String separator = "";
		for (Message message : messages) {
			String deltaTime = deltaTimeTranslator.translate(timeOfExecution, message.getTime());
			output += separator + message.getUser() + " - " + message.getDescription() + " (" + deltaTime + ")";
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

class MessageComparator implements Comparator<Message> {

	@Override
	public int compare(Message message1, Message message2) {
		return (int) (message2.getTime().getTimeInMillis() - message1.getTime().getTimeInMillis());
	}
}