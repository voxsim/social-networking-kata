package it.voxsim.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import it.voxsim.repository.LinkRepository;

@RunWith(MockitoJUnitRunner.class)
public class FollowCommandTest {
	private static final String USER = "Alice";
	private static final String ANOTHER_USER = "Charlie";

	@Test
	public void userFollowsAnotherUser() {
		LinkRepository repository = mock(LinkRepository.class);

		FollowCommand command = new FollowCommand(repository);

		command.execute(USER, ANOTHER_USER, new GregorianCalendar());

		verify(repository).addLinkBetween(USER, ANOTHER_USER);
	}

	@Test
	public void userCanNotFollowHimself() {
		LinkRepository repository = mock(LinkRepository.class);

		FollowCommand command = new FollowCommand(repository);

		command.execute(USER, USER, new GregorianCalendar());

		verify(repository, never()).addLinkBetween(USER, USER);
	}
}