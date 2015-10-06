package it.voxsim.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import it.voxsim.message.Message;
import it.voxsim.message.MultiMessagePrinter;
import it.voxsim.repository.LinkRepository;
import it.voxsim.repository.MessageRepository;

public class WallCommandTest {

	private static final String ANOTHER_USER = "another_user";
	private static final String A_USER = "user";
	private static final Calendar A_TIME_OF_EXECUTION = new GregorianCalendar();
	private static final ArrayList<Message> AN_EMPTY_LIST = new ArrayList<Message>();
	private static final ArrayList<String> A_LIST_WITH_ANOTHER_USER = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{
			add(ANOTHER_USER);
		}
	};

	private MessageRepository messageRepository;
	private WallCommand command;
	private LinkRepository linkRepository;
	private MultiMessagePrinter printer;

	@Before
	public void setUp() {
		messageRepository = mock(MessageRepository.class);
		linkRepository = mock(LinkRepository.class);
		printer = mock(MultiMessagePrinter.class);
		command = new WallCommand(messageRepository, linkRepository, printer);
	}

	@Test
	public void retrieveWhoFollowsTheUser() {
		command.execute(A_USER, null, A_TIME_OF_EXECUTION);

		verify(linkRepository).retrieveLinksByUsername(A_USER);
	}

	@Test
	public void retrieveMessagesOfUser() {
		command.execute(A_USER, null, A_TIME_OF_EXECUTION);

		verify(messageRepository).retrieveMessagesByUsernameOrderedByTime(A_USER);
	}

	@Test
	public void retrieveMessagesOfWhoFollowsTheUser() {
		when(linkRepository.retrieveLinksByUsername(A_USER)).thenReturn(A_LIST_WITH_ANOTHER_USER);

		command.execute(A_USER, null, A_TIME_OF_EXECUTION);

		verify(messageRepository).retrieveMessagesByUsernameOrderedByTime(ANOTHER_USER);
	}

	@Test
	public void printWallOfUser() {
		command.execute(A_USER, null, A_TIME_OF_EXECUTION);

		verify(printer).print(A_USER, AN_EMPTY_LIST, A_TIME_OF_EXECUTION);
	}
}
