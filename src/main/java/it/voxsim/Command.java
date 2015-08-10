package it.voxsim;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public abstract class Command {
	public abstract String execute(String username, String argument, Calendar timeOfExecution);

	public static Command build(MessageRepository repository, String action) {
		DeltaTimeTranslator deltaTimeTranslator = new EnglishDeltaTimeTranslator();

		Map<String, Command> commandDispatcher = new HashMap<String, Command>();
		commandDispatcher.put(null, new ReadCommand(repository, deltaTimeTranslator));
		commandDispatcher.put("->", new PostCommand(repository));
		commandDispatcher.put("follows", new FollowCommand());
		commandDispatcher.put("wall", new WallCommand());

		try {
			return commandDispatcher.get(action);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
