package it.voxsim;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SocialNetworkingClient {

	private MessageRepository repository;

	public SocialNetworkingClient(MessageRepository repository) {
		this.repository = repository;
	}

	public String process(String commandToInterpret, Calendar timeOfExecution) {
		Pattern pattern = Pattern.compile("(\\w+)(\\s(->|follows|wall)(\\s(.*))*)*");
		Matcher matcher = pattern.matcher(commandToInterpret);
		matcher.matches();

		String username = matcher.group(1);
		String action = matcher.group(3);
		String arguments = matcher.group(5);

		Command command = Command.build(repository, action);
		return command.execute(username, arguments, timeOfExecution);
	}
}
