package it.voxsim.command;

import java.util.HashMap;
import java.util.Map;

import it.voxsim.command.Command;
import it.voxsim.command.FollowCommand;
import it.voxsim.command.PostCommand;
import it.voxsim.command.ReadCommand;
import it.voxsim.command.WallCommand;
import it.voxsim.repository.LinkRepository;
import it.voxsim.repository.MessageRepository;

public class CommandDispatcher {
	private Map<String, Command> commandDispatcher;

	public CommandDispatcher(MessageRepository messageRepository, LinkRepository linkRepository) {
		commandDispatcher = new HashMap<String, Command>();
		commandDispatcher.put(null, ReadCommand.create(messageRepository));
		commandDispatcher.put("->", new PostCommand(messageRepository, linkRepository));
		commandDispatcher.put("follows", new FollowCommand(linkRepository));
		commandDispatcher.put("wall", WallCommand.create(messageRepository, linkRepository));
	}

	public Command dispatch(String action) {
		return commandDispatcher.get(action);
	}
}