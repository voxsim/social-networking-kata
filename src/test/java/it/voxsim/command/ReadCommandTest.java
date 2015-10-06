package it.voxsim.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import it.voxsim.message.Message;
import it.voxsim.message.MultiMessagePrinter;
import it.voxsim.repository.MessageRepository;

public class ReadCommandTest {

	private static final String A_USER = "user";
	private static final Calendar A_TIME_OF_EXECUTION = new GregorianCalendar();
	private static final ArrayList<Message> AN_EMPTY_LIST = new ArrayList<Message>();

	private MessageRepository messageRepository;
	private ReadCommand command;
	private MultiMessagePrinter printer;

	@Before
	public void setUp() {
		messageRepository = mock(MessageRepository.class);
		printer = mock(MultiMessagePrinter.class);
		command = new ReadCommand(messageRepository, printer);
	}

	@Test
	public void retrieveMessagesOfUser() {
		command.execute(A_USER, null, A_TIME_OF_EXECUTION);

		verify(messageRepository).retrieveMessagesByUsernameOrderedByTime(A_USER);
	}

	@Test
	public void printMessagesOfUser() {
		command.execute(A_USER, null, A_TIME_OF_EXECUTION);

		verify(printer).print(A_USER, AN_EMPTY_LIST, A_TIME_OF_EXECUTION);
	}
}
