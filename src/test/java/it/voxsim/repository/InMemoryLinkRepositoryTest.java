package it.voxsim.repository;

import static it.voxsim.AssertUtils.assertEmpty;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class InMemoryLinkRepositoryTest {

	private static final String A_FOLLOWED_USER = "USER_FOLLOWED";
	private static final String A_USER = "USER_A";

	private InMemoryLinkRepository repository;

	@Before
	public void setUp() {
		repository = new InMemoryLinkRepository();
	}

	@Test
	public void saveIfNotExistAndListOfLinksShouldBeEmpty() throws Exception {
		repository.saveIfNotExist(A_USER);

		List<String> links = repository.retrieveLinksByUsername(A_USER);

		assertEmpty(links);
	}

	@Test
	public void addLinkToUser() throws Exception {
		repository.saveIfNotExist(A_USER);

		repository.addLinkBetween(A_USER, A_FOLLOWED_USER);

		List<String> links = repository.retrieveLinksByUsername(A_USER);

		assertNotNull(links);
		assertFalse(links.isEmpty());
		assertTrue(links.contains(A_FOLLOWED_USER));
	}

	@Test
	public void saveIfNotExistsShouldNotOverridePreesistentLinks() throws Exception {
		repository.saveIfNotExist(A_USER);
		repository.addLinkBetween(A_USER, A_FOLLOWED_USER);

		repository.saveIfNotExist(A_USER);
		List<String> links = repository.retrieveLinksByUsername(A_USER);

		assertNotNull(links);
		assertFalse(links.isEmpty());
		assertTrue(links.contains(A_FOLLOWED_USER));
	}
}