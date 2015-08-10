package it.voxsim;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SocialNetworkingClient {

	private MessageRepository messageRepository;
	private LinkRepository linkRepository;

	public SocialNetworkingClient(MessageRepository messageRepository, LinkRepository linkRepository) {
		this.messageRepository = messageRepository;
		this.linkRepository = linkRepository;
	}

	public String process(String commandToInterpret, Calendar timeOfExecution) {
		Pattern pattern = Pattern.compile("(\\w+)(\\s(->|follows|wall)(\\s(.*))*)*");
		Matcher matcher = pattern.matcher(commandToInterpret);
		matcher.matches();

		String username = matcher.group(1);
		String action = matcher.group(3);
		String arguments = matcher.group(5);

		Command command = Command.build(messageRepository, linkRepository, action);
		return command.execute(username, arguments, timeOfExecution);
	}
}
