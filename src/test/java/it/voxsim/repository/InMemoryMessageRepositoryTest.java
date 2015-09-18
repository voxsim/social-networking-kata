package it.voxsim.repository;

import static it.voxsim.AssertUtils.assertEmpty;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.voxsim.message.Message;

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
	public void saveIfNotExistAndListOfMessagesShouldBeEmpty() throws Exception {
		repository.saveIfNotExist(A_USER);

		List<Message> messages = repository.retrieveMessagesByUsernameOrderedByTime(A_USER);

		assertEmpty(messages);
	}

	@Test
	public void addMessageToListMessagesOfUser() throws Exception {
		repository.saveIfNotExist(A_USER);

		repository.addMessageTo(A_USER, A_MESSAGE, A_TIME);

		List<Message> messages = repository.retrieveMessagesByUsernameOrderedByTime(A_USER);
		assertNotNull(messages);
		assertFalse(messages.isEmpty());
		Message message = messages.get(0);
		assertNotNull("message should not be null", message);
	}

	@Test
	public void saveIfNotExistsShouldNotOverridePreesistentMessages() throws Exception {
		repository.saveIfNotExist(A_USER);
		repository.addMessageTo(A_USER, A_MESSAGE, A_TIME);

		repository.saveIfNotExist(A_USER);
		List<Message> messages = repository.retrieveMessagesByUsernameOrderedByTime(A_USER);

		assertNotNull(messages);
		assertFalse(messages.isEmpty());
		Message message = messages.get(0);
		assertNotNull("message should not be null", message);
	}
}