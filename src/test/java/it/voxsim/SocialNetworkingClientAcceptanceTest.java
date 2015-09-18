package it.voxsim;

import static it.voxsim.AssertUtils.ONE_MINUTE;
import static it.voxsim.AssertUtils.ONE_SECOND;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.voxsim.command.CommandDispatcher;
import it.voxsim.message.EnhacedCalendar;
import it.voxsim.repository.InMemoryLinkRepository;
import it.voxsim.repository.InMemoryMessageRepository;
import it.voxsim.repository.LinkRepository;
import it.voxsim.repository.MessageRepository;

public class SocialNetworkingClientAcceptanceTest {
	private SocialNetworkingClient client;

	@Before
	public void setUp() {
		MessageRepository messageRepository = new InMemoryMessageRepository();
		LinkRepository linkRepository = new InMemoryLinkRepository();
		CommandDispatcher commandDispatcher = new CommandDispatcher(messageRepository, linkRepository);
		client = new SocialNetworkingClient(commandDispatcher);
	}

	@Test
	public void postAMessage() throws Exception {
		typeCommandAndAssertThatOutputIs("Bob -> Damn! We lost!", "");
	}

	@Test
	public void showEmptyUserMessages() throws Exception {
		typeCommandAndAssertThatOutputIs("Alice", "no messages from Alice");
	}

	@Test
	public void readMessagesFromBobAfterHePostedOneMessage() throws Exception {
		GregorianCalendar timeRead = new EnhacedCalendar();
		GregorianCalendar timePosting = new EnhacedCalendar(timeRead, 2 * ONE_MINUTE);

		typeCommandAndAssertThatOutputIs("Bob -> Damn! We lost!", "", timePosting);

		typeCommandAndAssertThatOutputIs("Bob", "Damn! We lost! (2 minutes ago)", timeRead);
	}

	@Test
	public void readMessagesFromBobAfterHePostedTwoMessages() throws Exception {
		GregorianCalendar timeRead = new EnhacedCalendar();
		GregorianCalendar timePosting = new EnhacedCalendar(timeRead, 2 * ONE_MINUTE);
		GregorianCalendar timeSecondPosting = new EnhacedCalendar(timeRead, 1 * ONE_MINUTE);

		typeCommandAndAssertThatOutputIs("Bob -> Damn! We lost!", "", timePosting);
		typeCommandAndAssertThatOutputIs("Bob -> Good game though.", "", timeSecondPosting);

		typeCommandAndAssertThatOutputIs("Bob", "Good game though. (1 minute ago)\nDamn! We lost! (2 minutes ago)",
				timeRead);
	}

	@Test
	public void showEmptyUserWall() throws Exception {
		typeCommandAndAssertThatOutputIs("Alice wall", "no messages in Alice wall");
	}

	@Test
	public void showWallAfterComplexScenario() throws Exception {
		GregorianCalendar timeRead = new EnhacedCalendar();
		GregorianCalendar timePosting = new EnhacedCalendar(timeRead, 5 * ONE_MINUTE);
		GregorianCalendar timeSecondPosting = new EnhacedCalendar(timeRead, 15 * ONE_SECOND);
		GregorianCalendar timeWall = timeRead;

		typeCommandAndAssertThatOutputIs("Alice -> I love the weather today", "", timePosting);

		typeCommandAndAssertThatOutputIs("Alice", "I love the weather today (5 minutes ago)", timeRead);

		typeCommandAndAssertThatOutputIs("Alice follows Charlie", "");

		typeCommandAndAssertThatOutputIs("Charlie -> I'm in New York today!", "", timeSecondPosting);

		typeCommandAndAssertThatOutputIs("Alice wall", "Charlie - I'm in New York today! (15 seconds ago)\n"
				+ "Alice - I love the weather today (5 minutes ago)", timeWall);
	}

	private void typeCommandAndAssertThatOutputIs(String command, String expectedOutput) {
		typeCommandAndAssertThatOutputIs(command, expectedOutput, new GregorianCalendar());
	}

	private void typeCommandAndAssertThatOutputIs(String command, String expectedOutput, Calendar timeOfExecution) {
		String actualOutput = client.process(command, timeOfExecution);
		Assert.assertEquals(expectedOutput, actualOutput);
	}
}