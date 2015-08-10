package it.voxsim;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public abstract class Command {
	public abstract String execute(String username, String argument, Calendar timeOfExecution);

	public static Command build(MessageRepository messageRepository, LinkRepository linkRepository, String action) {
		DeltaTimeTranslator deltaTimeTranslator = new EnglishDeltaTimeTranslator();

		Map<String, Command> commandDispatcher = new HashMap<String, Command>();
		commandDispatcher.put(null, new ReadCommand(messageRepository, deltaTimeTranslator));
		commandDispatcher.put("->", new PostCommand(messageRepository, linkRepository));
		commandDispatcher.put("follows", new FollowCommand(linkRepository));
		commandDispatcher.put("wall", new WallCommand());

		try {
			return commandDispatcher.get(action);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
