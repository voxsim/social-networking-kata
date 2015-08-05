package it.voxsim;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class InMemoryUserRepositoryTest {

	private static final String A_MESSAGE = "message";

	private static final String A_USER = "USER_A";

	private InMemoryUserRepository repository;

	@Before
	public void setUp() {
		repository = new InMemoryUserRepository();
	}

	@Test
	public void retrieveMessagesByUsernameShouldRetrieveNullIfUserNotExist() {
		List<String> messages = repository.retrieveMessagesByUsername(A_USER);

		assertNull(messages);
	}

	@Test
	public void saveIfNotExistAndListOfMessagesShouldBeEmpty() throws Exception {
		repository.saveIfNotExist(A_USER);

		List<String> messages = repository.retrieveMessagesByUsername(A_USER);

		assertNotNull(messages);
		assertTrue(messages.isEmpty());
	}

	@Test
	public void addMessageToListMessagesOfUser() throws Exception {
		repository.saveIfNotExist(A_USER);
		
		repository.addMessageTo(A_USER, A_MESSAGE);
		
		List<String> messages = repository.retrieveMessagesByUsername(A_USER);
		assertNotNull(messages);
		assertFalse(messages.isEmpty());
		assertEquals(A_MESSAGE, messages.get(0));
	}

	@Test
	public void saveIfNotExistsShouldNotOverridePreesistentUser() throws Exception {
		repository.saveIfNotExist(A_USER);
		repository.addMessageTo(A_USER, A_MESSAGE);
		
		repository.saveIfNotExist(A_USER);
		List<String> messages = repository.retrieveMessagesByUsername(A_USER);

		assertNotNull(messages);
		assertFalse(messages.isEmpty());
		assertEquals(A_MESSAGE, messages.get(0));
	}
}
