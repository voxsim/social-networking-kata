package it.voxsim;

import java.util.Calendar;

public class WallCommand extends Command {

	@Override
	public String execute(String username, String argument, Calendar timeOfExecution) {
		return "no messages in " + username + " wall";
	}

}
