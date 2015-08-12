package it.voxsim;

import java.util.HashMap;
import java.util.Map;

import it.voxsim.command.Command;
import it.voxsim.command.FollowCommand;
import it.voxsim.command.PostCommand;
import it.voxsim.command.ReadCommand;
import it.voxsim.command.WallCommand;

public class CommandDispatcher {
	private Map<String, Command> commandDispatcher;

	public CommandDispatcher(MessageRepository messageRepository, LinkRepository linkRepository) {
		commandDispatcher = new HashMap<String, Command>();
		commandDispatcher.put(null, new ReadCommand(messageRepository));
		commandDispatcher.put("->", new PostCommand(messageRepository, linkRepository));
		commandDispatcher.put("follows", new FollowCommand(linkRepository));
		commandDispatcher.put("wall", new WallCommand(messageRepository, linkRepository));
	}

	public Command dispatch(String action) {
		return commandDispatcher.get(action);
	}
}