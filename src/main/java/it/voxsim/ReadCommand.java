package it.voxsim;

public class ReadCommand extends Command {

	@Override
	public String execute(String username, String argument) {
		return "no messages from " + username;
	}

}
