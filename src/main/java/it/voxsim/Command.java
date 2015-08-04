package it.voxsim;

import java.util.HashMap;
import java.util.Map;

public abstract class Command {
	public abstract String execute();

	@SuppressWarnings("serial")
	public static Command build(String action, String username, String argument) {
		Map<String, Class<? extends Command>> commands = new HashMap<String, Class<? extends Command>>() {{
			put(null, WallCommand.class);
			put("->", NewMessageCommand.class);
		}};
		
		try {
			return commands.get(action).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
