package it.voxsim;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class PostCommandTest {

	private static final String A_USER = "USER_A";
	private static final String A_MESSAGE = "message";

	private UserRepository userRepository;
	private PostCommand command;

	@Before
	public void setUp() {
		userRepository = new InMemoryUserRepository();
		command = new PostCommand(userRepository);
	}

	@Test
	public void firstPostForOneUserShouldSaveThatUser() {
		assertNull(userRepository.retrieveMessagesByUsername(A_USER));

		command.execute(A_USER, A_MESSAGE);

		assertNotNull(userRepository.retrieveMessagesByUsername(A_USER));
	}

	@Test
	public void saveMessagePosted() throws Exception {
		command.execute(A_USER, A_MESSAGE);

		assertEquals(A_MESSAGE, userRepository.retrieveMessagesByUsername(A_USER).get(0));
	}
}