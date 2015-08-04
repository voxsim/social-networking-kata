package it.voxsim;

import java.util.HashMap;
import java.util.Map;

public abstract class Command {
	public abstract String execute(String username, String argument);

	@SuppressWarnings("serial")
	public static Command build(String action) {
		Map<String, Class<? extends Command>> commands = new HashMap<String, Class<? extends Command>>() {{
			put(null, ReadCommand.class);
			put("->", PostCommand.class);
			put("follows", FollowCommand.class);
			put("wall", WallCommand.class);
		}};
		
		try {
			return commands.get(action).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
