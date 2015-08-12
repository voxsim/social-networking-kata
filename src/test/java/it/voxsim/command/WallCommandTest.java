package it.voxsim.command;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import it.voxsim.InMemoryLinkRepository;
import it.voxsim.InMemoryMessageRepository;
import it.voxsim.LinkRepository;
import it.voxsim.MessageRepository;

public class WallCommandTest {

	private static final String A_USER = "user";
	private static final String A_FOLLOWED_USER = "followed-user";
	private static final String A_MESSAGE = "message";
	private static final String A_MESSAGE_2 = "message 2";
	private static final String A_MESSAGE_3 = "message 3";
	private static final String A_DELTA_TIME = "(0 second ago)";
	private static final String A_DELTA_TIME_2 = "(1 minute ago)";
	private static final String A_DELTA_TIME_3 = "(2 hours ago)";
	private static final Calendar A_TIME_OF_EXECUTION = new GregorianCalendar();
	private static final Calendar A_TIME_OF_MESSAGE = new GregorianCalendar();
	private static final Calendar A_TIME_OF_MESSAGE_2 = new GregorianCalendar();
	private static final Calendar A_TIME_OF_MESSAGE_3 = new GregorianCalendar();

	private MessageRepository messageRepository;
	private WallCommand command;
	private LinkRepository linkRepository;

	@Before
	public void setUp() {
		A_TIME_OF_MESSAGE_2.setTimeInMillis(A_TIME_OF_MESSAGE.getTimeInMillis() - 60000);
		A_TIME_OF_MESSAGE_3.setTimeInMillis(A_TIME_OF_MESSAGE.getTimeInMillis() - 2 * 60 * 60000);

		messageRepository = new InMemoryMessageRepository();
		linkRepository = new InMemoryLinkRepository();
		command = new WallCommand(messageRepository, linkRepository);
	}

	@Test
	public void noMessagesFromUser() {
		String output = command.execute(A_USER, null, A_TIME_OF_EXECUTION);

		assertEquals("no messages in " + A_USER + " wall", output);
	}

	@Test
	public void oneMessageFromUser() {
		messageRepository.saveIfNotExist(A_USER);
		messageRepository.addMessageTo(A_USER, A_MESSAGE, A_TIME_OF_MESSAGE);

		String output = command.execute(A_USER, null, A_TIME_OF_EXECUTION);

		assertEquals(A_USER + " - " + A_MESSAGE + " " + A_DELTA_TIME, output);
	}

	@Test
	public void multipleMessagesFromUser() {
		messageRepository.saveIfNotExist(A_USER);
		messageRepository.addMessageTo(A_USER, A_MESSAGE, A_TIME_OF_MESSAGE);
		messageRepository.addMessageTo(A_USER, A_MESSAGE_2, A_TIME_OF_MESSAGE_2);
		messageRepository.addMessageTo(A_USER, A_MESSAGE_3, A_TIME_OF_MESSAGE_3);

		String output = command.execute(A_USER, null, A_TIME_OF_EXECUTION);

		String message1 = A_USER + " - " + A_MESSAGE + " " + A_DELTA_TIME + "\n";
		String message2 = A_USER + " - " + A_MESSAGE_2 + " " + A_DELTA_TIME_2 + "\n";
		String message3 = A_USER + " - " + A_MESSAGE_3 + " " + A_DELTA_TIME_3;
		assertEquals(message1 + message2 + message3, output);
	}

	@Test
	public void oneMessageFromUserAndOneFromFollowed() throws Exception {
		messageRepository.saveIfNotExist(A_USER);
		messageRepository.saveIfNotExist(A_FOLLOWED_USER);

		messageRepository.addMessageTo(A_USER, A_MESSAGE, A_TIME_OF_MESSAGE);
		messageRepository.addMessageTo(A_FOLLOWED_USER, A_MESSAGE_2, A_TIME_OF_MESSAGE_2);

		linkRepository.saveIfNotExist(A_USER);
		linkRepository.saveIfNotExist(A_FOLLOWED_USER);

		linkRepository.addTo(A_USER, A_FOLLOWED_USER);

		String output = command.execute(A_USER, null, A_TIME_OF_EXECUTION);

		String message1 = A_USER + " - " + A_MESSAGE + " " + A_DELTA_TIME + "\n";
		String message2 = A_FOLLOWED_USER + " - " + A_MESSAGE_2 + " " + A_DELTA_TIME_2;
		assertEquals(message1 + message2, output);
	}
}
