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
		User user = userRepository.findByUsername(username);

		if (user == null) {
			userRepository.save(username);
			user = userRepository.findByUsername(username);
		}

		return "";
	}
}
