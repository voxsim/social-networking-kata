package it.voxsim;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class PostCommandTest {

	private static final String A_USER = "USER_A";
	private static final String A_MESSAGE = "message";
	private static final Calendar A_TIME_OF_EXECUTION = new GregorianCalendar();

	private UserRepository repository;
	private PostCommand command;

	@Before
	public void setUp() {
		repository = new InMemoryUserRepository();
		command = new PostCommand(repository);
	}

	@Test
	public void firstPostForOneUserShouldSaveThatUser() {
		assertNull(repository.retrieveMessagesByUsername(A_USER));

		command.execute(A_USER, A_MESSAGE, A_TIME_OF_EXECUTION);

		assertNotNull(repository.retrieveMessagesByUsername(A_USER));
	}

	@Test
	public void saveMessagePosted() throws Exception {
		command.execute(A_USER, A_MESSAGE, A_TIME_OF_EXECUTION);

		Message message = repository.retrieveMessagesByUsername(A_USER).get(0);
		assertEquals(A_MESSAGE, message.getDescription());
		assertEquals(A_TIME_OF_EXECUTION, message.getTime());
	}
}