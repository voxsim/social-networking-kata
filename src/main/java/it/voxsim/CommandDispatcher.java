package it.voxsim;

import java.util.HashMap;
import java.util.Map;

public class CommandDispatcher {
	private Map<String, Command> commandDispatcher;

	public CommandDispatcher(MessageRepository messageRepository, LinkRepository linkRepository, DeltaTimeTranslator deltaTimeTranslator) {
		commandDispatcher = new HashMap<String, Command>();
		commandDispatcher.put(null, new ReadCommand(messageRepository, deltaTimeTranslator));
		commandDispatcher.put("->", new PostCommand(messageRepository, linkRepository));
		commandDispatcher.put("follows", new FollowCommand(linkRepository));
		commandDispatcher.put("wall", new WallCommand(messageRepository, linkRepository, deltaTimeTranslator));
	}

	public Command dispatch(String action) {
		return commandDispatcher.get(action);
	}
}