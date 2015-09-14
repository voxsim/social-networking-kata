package it.voxsim.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;
import java.util.Set;

import org.junit.Test;

import it.voxsim.repository.InMemoryLinkRepository;
import it.voxsim.repository.LinkRepository;

public class FollowCommandTest {

	private static final String USER_A = "Alice";
	private static final String USER_B = "Charlie";

	@Test
	public void userAfollowsUserB() {
		LinkRepository repository = new InMemoryLinkRepository();
		repository.saveIfNotExist(USER_A);
		repository.saveIfNotExist(USER_B);

		assertTrue(repository.retrieveLinksByUsername(USER_A).isEmpty());

		FollowCommand command = new FollowCommand(repository);

		command.execute(USER_A, USER_B, new GregorianCalendar());

		assertTrue(repository.retrieveLinksByUsername(USER_A).contains(USER_B));
	}

	@Test
	public void userAfollowsUserBTwice() {
		LinkRepository repository = new InMemoryLinkRepository();
		repository.saveIfNotExist(USER_A);
		repository.saveIfNotExist(USER_B);

		FollowCommand command = new FollowCommand(repository);

		command.execute(USER_A, USER_B, new GregorianCalendar());
		command.execute(USER_A, USER_B, new GregorianCalendar());

		Set<String> followedByUserA = repository.retrieveLinksByUsername(USER_A);
		assertTrue(followedByUserA.contains(USER_B));
		assertEquals(1, followedByUserA.size());
	}
}