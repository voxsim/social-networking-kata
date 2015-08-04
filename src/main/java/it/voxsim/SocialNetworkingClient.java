package it.voxsim;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SocialNetworkingClient {

	public String process(String commandToInterpret) {
		Pattern pattern = Pattern.compile("(\\w+)(\\s(->|follows|wall)(\\s(.*))*)*");
		Matcher matcher = pattern.matcher(commandToInterpret);
		matcher.matches();

		String username = matcher.group(1);
		String action = matcher.group(3);
		String argument = matcher.group(5);
		
		Command command = Command.build(action, username, argument);
		return command.execute();
	}
}
