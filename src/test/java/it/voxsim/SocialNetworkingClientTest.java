package it.voxsim;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.voxsim.repository.InMemoryLinkRepository;
import it.voxsim.repository.InMemoryMessageRepository;
import it.voxsim.repository.LinkRepository;
import it.voxsim.repository.MessageRepository;

public class SocialNetworkingClientTest {
	private SocialNetworkingClient client;

	@Before
	public void setUp() {
		MessageRepository messageRepository = new InMemoryMessageRepository();
		LinkRepository linkRepository = new InMemoryLinkRepository();
		CommandDispatcher commandDispatcher = new CommandDispatcher(messageRepository, linkRepository);
		client = new SocialNetworkingClient(commandDispatcher);
	}

	@Test
	public void showUserWall() throws Exception {
		typeCommandAndAssertThatOutputIs("Alice wall", "no messages in Alice wall");
	}

	@Test
	public void acceptanceTest() throws Exception {
		GregorianCalendar timeFirstMessage = new GregorianCalendar();
		GregorianCalendar timeSecondMessage = new GregorianCalendar();
		timeFirstMessage.setTimeInMillis(timeSecondMessage.getTimeInMillis() - 5 * 60 * 1000);
		GregorianCalendar timeThirdMessage = new GregorianCalendar();
		GregorianCalendar timeFourthMessage = new GregorianCalendar();
		timeThirdMessage.setTimeInMillis(timeFourthMessage.getTimeInMillis() - 15 * 1000);

		typeCommandAndAssertThatOutputIs("Alice -> I love the weather today", "", timeFirstMessage);

		typeCommandAndAssertThatOutputIs("Alice", "I love the weather today (5 minutes ago)", timeSecondMessage);

		typeCommandAndAssertThatOutputIs("Alice follows Charlie", "");

		typeCommandAndAssertThatOutputIs("Charlie -> I'm in New York today!", "", timeThirdMessage);

		typeCommandAndAssertThatOutputIs("Alice wall", "Charlie - I'm in New York today! (15 seconds ago)\n"
				+ "Alice - I love the weather today (5 minutes ago)", timeFourthMessage);
	}

	private void typeCommandAndAssertThatOutputIs(String command, String expectedOutput) {
		typeCommandAndAssertThatOutputIs(command, expectedOutput, new GregorianCalendar());
	}

	private void typeCommandAndAssertThatOutputIs(String command, String expectedOutput, Calendar timeOfExecution) {
		String actualOutput = client.process(command, timeOfExecution);
		Assert.assertEquals(expectedOutput, actualOutput);
	}
}