package it.voxsim;

public class WallCommand extends Command {

	@Override
	public String execute(String username, String argument) {
		return "no messages in " + username + " wall";
	}

}
