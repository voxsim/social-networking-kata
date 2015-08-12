package it.voxsim.command;

import static it.voxsim.AssertUtils.ONE_HOUR;
import static it.voxsim.AssertUtils.ONE_MINUTE;
import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import it.voxsim.message.EnhacedCalendar;
import it.voxsim.repository.InMemoryMessageRepository;
import it.voxsim.repository.MessageRepository;

public class ReadCommandTest {

	private static final String A_USER = "user";
	private static final String A_MESSAGE = "message";
	private static final String A_MESSAGE_2 = "message 2";
	private static final String A_MESSAGE_3 = "message 3";
	private static final String A_DELTA_TIME = "(0 second ago)";
	private static final String A_DELTA_TIME_2 = "(1 minute ago)";
	private static final String A_DELTA_TIME_3 = "(2 hours ago)";
	private static final Calendar A_TIME_OF_EXECUTION = new GregorianCalendar();
	private static final Calendar A_TIME_OF_MESSAGE = A_TIME_OF_EXECUTION;
	private static final Calendar A_TIME_OF_MESSAGE_2 = new EnhacedCalendar(A_TIME_OF_EXECUTION, ONE_MINUTE);
	private static final Calendar A_TIME_OF_MESSAGE_3 = new EnhacedCalendar(A_TIME_OF_EXECUTION, 2 * ONE_HOUR);

	private MessageRepository repository;
	private ReadCommand command;

	@Before
	public void setUp() {
		repository = new InMemoryMessageRepository();
		command = new ReadCommand(repository);
	}

	@Test
	public void noMessagesFromUser() {
		String output = command.execute(A_USER, A_MESSAGE, A_TIME_OF_EXECUTION);

		assertEquals("no messages from " + A_USER, output);
	}

	@Test
	public void oneMessageFromUser() {
		repository.saveIfNotExist(A_USER);
		repository.addMessageTo(A_USER, A_MESSAGE, A_TIME_OF_MESSAGE);

		String output = command.execute(A_USER, A_MESSAGE, A_TIME_OF_EXECUTION);

		assertEquals(A_MESSAGE + " " + A_DELTA_TIME, output);
	}

	@Test
	public void multipleMessagesFromUser() {
		repository.saveIfNotExist(A_USER);
		repository.addMessageTo(A_USER, A_MESSAGE, A_TIME_OF_MESSAGE);
		repository.addMessageTo(A_USER, A_MESSAGE_2, A_TIME_OF_MESSAGE_2);
		repository.addMessageTo(A_USER, A_MESSAGE_3, A_TIME_OF_MESSAGE_3);

		String output = command.execute(A_USER, A_MESSAGE, A_TIME_OF_EXECUTION);

		assertEquals(A_MESSAGE + " " + A_DELTA_TIME + "\n" + A_MESSAGE_2 + " " + A_DELTA_TIME_2 + "\n" + A_MESSAGE_3
				+ " " + A_DELTA_TIME_3, output);
	}
}
