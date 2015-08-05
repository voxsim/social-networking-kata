package it.voxsim;

public class PostCommand extends Command {

	private UserRepository userRepository;

	public PostCommand() {
		this(new InMemoryUserRepository());
	}

	public PostCommand(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public String execute(String username, String argument) {
		userRepository.saveIfNotExist(username);
		userRepository.addMessageTo(username, argument);
		return "";
	}
}
