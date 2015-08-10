package it.voxsim;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class SocialNetworkingClientTest {
	private SocialNetworkingClient client;

	@Before
	public void setUp() {
		MessageRepository messageRepository = new InMemoryMessageRepository();
		LinkRepository linkRepository = new InMemoryLinkRepository();
		client = new SocialNetworkingClient(messageRepository, linkRepository);
	}

	@Test
	public void showUserWall() throws Exception {
		typeCommandAndAssertThatOutputIs("Alice wall", "no messages in Alice wall");
	}

	@Test
	public void firstPieceOfAcceptanceTest() throws Exception {
		GregorianCalendar timeFirstMessage = new GregorianCalendar();
		GregorianCalendar timeSecondMessage = new GregorianCalendar();
		timeFirstMessage.setTimeInMillis(timeSecondMessage.getTimeInMillis() - 5 * 60 * 1000);

		typeCommandAndAssertThatOutputIs("Alice -> I love the weather today", "", timeFirstMessage);

		typeCommandAndAssertThatOutputIs("Alice", "I love the weather today (5 minutes ago)\n", timeSecondMessage);
		
		typeCommandAndAssertThatOutputIs("Alice follows Charlie", "");

		typeCommandAndAssertThatOutputIs("Charlie -> I'm in New York today!", "");
	}

	@Test
	@Ignore("It will be red until everything works")
	public void acceptanceTest() {
		typeCommandAndAssertThatOutputIs("Alice -> I love the weather today", "");

		typeCommandAndAssertThatOutputIs("Alice", "I love the weather today (5 minutes ago)\n");

		typeCommandAndAssertThatOutputIs("Alice follows Charlie", "");

		typeCommandAndAssertThatOutputIs("Charlie -> I'm in New York today!", "");

		typeCommandAndAssertThatOutputIs("Alice wall", "Charlie - I'm in New York today! (15 seconds ago)\n"
				+ "Alice - I love the weather today (5 minutes ago)");
	}

	private void typeCommandAndAssertThatOutputIs(String command, String expectedOutput) {
		typeCommandAndAssertThatOutputIs(command, expectedOutput, new GregorianCalendar());
	}

	private void typeCommandAndAssertThatOutputIs(String command, String expectedOutput, Calendar timeOfExecution) {
		String actualOutput = client.process(command, timeOfExecution);
		Assert.assertEquals(expectedOutput, actualOutput);
	}
}