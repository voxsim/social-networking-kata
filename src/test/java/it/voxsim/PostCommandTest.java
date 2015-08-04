package it.voxsim;

import static org.junit.Assert.*;

import org.junit.Test;

public class PostCommandTest {

	private static final String A_USER = "USER_A";
	private static final String A_MESSAGE = "message";

	@Test
	public void firstPostForOneUserShouldSaveThatUser() {
		UserRepository userRepository = new InMemoryUserRepository();
		PostCommand command = new PostCommand(userRepository);
		
		assertNull(userRepository.findByUsername(A_USER));
		
		command.execute(A_USER, A_MESSAGE);
		
		assertNotNull(userRepository.findByUsername(A_USER));
	}
}