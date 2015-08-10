package it.voxsim;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

public class FollowCommandTest {

	private static final String USER_A = "Alice";
	private static final String USER_B = "Charlie";

	@Test
	public void userAfollowsUserBAndBothAlreadyExists() {
		LinkRepository repository = new InMemoryLinkRepository();
		repository.saveIfNotExist(USER_A);
		repository.saveIfNotExist(USER_B);
		
		assertTrue(repository.retrieveByUsername(USER_A).isEmpty());
		
		FollowCommand command = new FollowCommand(repository);
		
		command.execute(USER_A, USER_B, new GregorianCalendar());
		
		assertTrue(repository.retrieveByUsername(USER_A).contains(USER_B));
	}
}