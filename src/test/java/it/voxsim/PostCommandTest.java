package it.voxsim;

import static it.voxsim.AssertUtils.assertEmpty;
import static it.voxsim.AssertUtils.assertNotEmpty;
import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class PostCommandTest {

	private static final String A_USER = "USER_A";
	private static final String A_MESSAGE = "message";
	private static final Calendar A_TIME_OF_EXECUTION = new GregorianCalendar();

	private MessageRepository messageRepository;
	private InMemoryLinkRepository linkRepository;
	private PostCommand command;

	@Before
	public void setUp() {
		messageRepository = new InMemoryMessageRepository();
		linkRepository = new InMemoryLinkRepository();
		command = new PostCommand(messageRepository, linkRepository);
	}

	@Test
	public void firstPostForOneUserShouldSaveThatUserInMessageRepository() {
		assertEmpty(messageRepository.retrieveMessagesByUsername(A_USER));

		command.execute(A_USER, A_MESSAGE, A_TIME_OF_EXECUTION);

		assertNotEmpty(messageRepository.retrieveMessagesByUsername(A_USER));
	}

	@Test
	public void firstPostForOneUserShouldSaveThatUserInLinkRepository() {
		command.execute(A_USER, A_MESSAGE, A_TIME_OF_EXECUTION);

		assertEmpty(linkRepository.retrieveByUsername(A_USER));
	}

	@Test
	public void saveMessagePosted() throws Exception {
		command.execute(A_USER, A_MESSAGE, A_TIME_OF_EXECUTION);

		Message message = messageRepository.retrieveMessagesByUsername(A_USER).get(0);
		assertEquals(A_MESSAGE, message.getDescription());
		assertEquals(A_TIME_OF_EXECUTION, message.getTime());
	}
}