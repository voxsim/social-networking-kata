package it.voxsim;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class InMemoryMessageRepositoryTest {

	private static final String A_MESSAGE = "message";
	private static final String A_USER = "USER_A";
	private static final Calendar A_TIME = new GregorianCalendar();

	private InMemoryMessageRepository repository;

	@Before
	public void setUp() {
		repository = new InMemoryMessageRepository();
	}

	@Test
	public void retrieveMessagesByUsernameShouldRetrieveNullIfUserNotExist() {
		List<Message> messages = repository.retrieveMessagesByUsername(A_USER);

		assertNull(messages);
	}

	@Test
	public void saveIfNotExistAndListOfMessagesShouldBeEmpty() throws Exception {
		repository.saveIfNotExist(A_USER);

		List<Message> messages = repository.retrieveMessagesByUsername(A_USER);

		assertNotNull(messages);
		assertTrue(messages.isEmpty());
	}

	@Test
	public void addMessageToListMessagesOfUser() throws Exception {
		repository.saveIfNotExist(A_USER);

		repository.addMessageTo(A_USER, A_MESSAGE, A_TIME);

		List<Message> messages = repository.retrieveMessagesByUsername(A_USER);
		assertNotNull(messages);
		assertFalse(messages.isEmpty());
		Message message = messages.get(0);
		assertEquals(A_MESSAGE, message.getDescription());
		assertEquals(A_TIME, message.getTime());
	}

	@Test
	public void saveIfNotExistsShouldNotOverridePreesistentUser() throws Exception {
		repository.saveIfNotExist(A_USER);
		repository.addMessageTo(A_USER, A_MESSAGE, A_TIME);

		repository.saveIfNotExist(A_USER);
		List<Message> messages = repository.retrieveMessagesByUsername(A_USER);

		assertNotNull(messages);
		assertFalse(messages.isEmpty());
		Message message = messages.get(0);
		assertEquals(A_MESSAGE, message.getDescription());
		assertEquals(A_TIME, message.getTime());
	}
}