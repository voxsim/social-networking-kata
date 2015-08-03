package it.voxsim;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import org.junit.Assert;

public class AcceptanceTest {
	
	private ByteArrayOutputStream arrayOutputStream;

	@Before
	public void setUp() {
		String[] args = new String[] {};
		App.main(args);
		
		arrayOutputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(arrayOutputStream, true));
	}

	@Test
	@Ignore("It will be red until everything works")
	public void test() {
		typeCommand("Alice -> I love the weather today");
		
		assertThatLastOutputIs("");
		
		typeCommand("Alice");
		
		assertThatLastOutputIs("I love the weather today (5 minutes ago)");
		
		typeCommand("Alice follows Charlie");
		
		assertThatLastOutputIs("");
		
		typeCommand("Charlie -> I'm in New York today!");
		
		assertThatLastOutputIs("");
		
		typeCommand("Alice wall");
		
		assertThatLastOutputIs(
				"I'm in New York today! (15 seconds ago)\n" +
				"I love the weather today (5 minutes ago)"
		);
	}

	private void assertThatLastOutputIs(String expectedOutput) {
		Assert.assertEquals(expectedOutput, arrayOutputStream.toString());
	}

	private void typeCommand(String consoleCommand) {
		consoleCommand += "\r\n";
		System.setIn(new ByteArrayInputStream(consoleCommand.getBytes()));
	}
}