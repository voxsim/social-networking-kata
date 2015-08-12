package it.voxsim;

import java.util.Calendar;

public class WallCommand implements Command {

	@Override
	public String execute(String username, String argument, Calendar timeOfExecution) {
		return "no messages in " + username + " wall";
	}

}
