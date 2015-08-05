package it.voxsim;

import java.util.Calendar;

public class PostCommand extends Command {

	private UserRepository userRepository;

	public PostCommand() {
		this(new InMemoryUserRepository());
	}

	public PostCommand(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public String execute(String username, String argument, Calendar timeOfExecution) {
		userRepository.saveIfNotExist(username);
		userRepository.addMessageTo(username, argument, timeOfExecution);
		return "";
	}
}
