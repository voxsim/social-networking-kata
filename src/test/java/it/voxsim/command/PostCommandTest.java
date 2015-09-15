package it.voxsim.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import it.voxsim.repository.LinkRepository;
import it.voxsim.repository.MessageRepository;

public class PostCommandTest {

	private static final String A_USER = "USER_A";
	private static final String A_MESSAGE = "message";
	private static final Calendar A_TIME_OF_EXECUTION = new GregorianCalendar();

	private MessageRepository messageRepository;
	private LinkRepository linkRepository;
	private PostCommand command;

	@Before
	public void setUp() {
		messageRepository = mock(MessageRepository.class);
		linkRepository = mock(LinkRepository.class);
		command = new PostCommand(messageRepository, linkRepository);
	}

	@Test
	public void firstPostForOneUserShouldSaveThatUserInMessageRepository() {
		command.execute(A_USER, A_MESSAGE, A_TIME_OF_EXECUTION);

		verify(messageRepository).saveIfNotExist(A_USER);
	}

	@Test
	public void firstPostForOneUserShouldSaveThatUserInLinkRepository() {
		command.execute(A_USER, A_MESSAGE, A_TIME_OF_EXECUTION);

		verify(linkRepository).saveIfNotExist(A_USER);
	}

	@Test
	public void saveMessagePosted() throws Exception {
		command.execute(A_USER, A_MESSAGE, A_TIME_OF_EXECUTION);

		verify(messageRepository).addMessageTo(A_USER, A_MESSAGE, A_TIME_OF_EXECUTION);
	}
}