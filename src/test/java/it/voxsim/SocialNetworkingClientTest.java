package it.voxsim;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class SocialNetworkingClientTest {
	private SocialNetworkingClient client;

	@Before
	public void setUp() {
		client = new SocialNetworkingClient();
	}

	@Test
	public void noMessagesToRead() throws Exception {
		typeCommandAndAssertThatLastOutputIs("Alice", "no messages from Alice");
	}

	@Test
	public void sendMessage() throws Exception {
		typeCommandAndAssertThatLastOutputIs("Alice -> I love the weather today", "");
	}
	
	@Test
	public void followsAnotherUser() throws Exception {
		typeCommandAndAssertThatLastOutputIs("Alice follows Charlie", "");
	}
	
	@Test
	public void showUserWall() throws Exception {
		typeCommandAndAssertThatLastOutputIs("Alice wall", "no messages in Alice wall");
	}

	@Test
	@Ignore("It will be red until everything works")
	public void acceptanceTest() {
		typeCommandAndAssertThatLastOutputIs("Alice -> I love the weather today", "");

		typeCommandAndAssertThatLastOutputIs("Alice", "I love the weather today (5 minutes ago)");

		typeCommandAndAssertThatLastOutputIs("Alice follows Charlie", "");

		typeCommandAndAssertThatLastOutputIs("Charlie -> I'm in New York today!", "");

		typeCommandAndAssertThatLastOutputIs("Alice wall", "Charlie - I'm in New York today! (15 seconds ago)\n" + 
														   "Alice - I love the weather today (5 minutes ago)");
	}

	private void typeCommandAndAssertThatLastOutputIs(String command, String expectedOutput) {
		String actualOutput = client.process(command);
		Assert.assertEquals(expectedOutput, actualOutput);
	}
}