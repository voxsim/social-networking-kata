package it.voxsim;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class ReadCommandTest {

	private static final String A_USER = "user";
	private static final String A_MESSAGE = "message";
	private static final String A_DELTA_TIME = "(1 second ago)";
	private static final Calendar A_TIME_OF_EXECUTION = new GregorianCalendar();
	private static final Calendar A_TIME_OF_MESSAGE = new GregorianCalendar();
	
	private UserRepository repository;
	private ReadCommand command;
	private DeltaTimeTranslator deltaTimeTranslator;
	
	@Before
	public void setUp() {
		repository = new InMemoryUserRepository();
		deltaTimeTranslator = new EnglishDeltaTimeTranslator();
		command = new ReadCommand(repository, deltaTimeTranslator);
	}

	@Test
	public void noMessagesFromUser() {
		String output = command.execute(A_USER, A_MESSAGE, A_TIME_OF_EXECUTION);
		
		assertEquals("no messages from " + A_USER, output);
	}
	
	@Test
	public void oneMessageFromUser() {
		repository.saveIfNotExist(A_USER);
		repository.addMessageTo(A_USER, A_MESSAGE, A_TIME_OF_MESSAGE);
		
		String output = command.execute(A_USER, A_MESSAGE, A_TIME_OF_EXECUTION);
		
		assertEquals(A_MESSAGE + " " + A_DELTA_TIME, output);
	}
}
