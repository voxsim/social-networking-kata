package it.voxsim.message;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.voxsim.message.Message;
import it.voxsim.message.MultiMessagePrinter;

public class MultiMessagePrinterTest {

	private static final Message A_MESSAGE = Message.create("", "", new GregorianCalendar());
	private static final String A_USER = "user";
	private static final String FORMAT_MESSAGE = "format";
	private static final String EMPTY_MESSAGE = "empty";
	private static final String EMPTY_USER_MESSAGE = "%{username}";

	private MultiMessagePrinter multiMessagePrinter;

	@Before
	public void setUp() {
		multiMessagePrinter = new MultiMessagePrinter(EMPTY_MESSAGE, FORMAT_MESSAGE);
	}

	@Test
	public void printEmptyMessageIfCallPrintWithEmptyList() {
		String actual = multiMessagePrinter.print(A_USER, new ArrayList<Message>(), new GregorianCalendar());

		assertEquals(EMPTY_MESSAGE, actual);
	}

	@Test
	public void printUserIfCallPrintWithEmptyList() {
		multiMessagePrinter = new MultiMessagePrinter(EMPTY_USER_MESSAGE, FORMAT_MESSAGE);

		String actual = multiMessagePrinter.print(A_USER, new ArrayList<Message>(), new GregorianCalendar());

		assertEquals(A_USER, actual);
	}

	@Test
	public void printFormatMessageOneTimeForAListWithOneElement() {
		List<Message> messages = new ArrayList<Message>();
		messages.add(A_MESSAGE);

		String actual = multiMessagePrinter.print(A_USER, messages, new GregorianCalendar());

		assertEquals(FORMAT_MESSAGE, actual);
	}

	@Test
	public void printFormatMessageTwoTimesWithNewLineForAListWithTwoElement() {
		List<Message> messages = new ArrayList<Message>();
		messages.add(A_MESSAGE);
		messages.add(A_MESSAGE);

		String actual = multiMessagePrinter.print(A_USER, messages, new GregorianCalendar());

		assertEquals(FORMAT_MESSAGE + "\n" + FORMAT_MESSAGE, actual);
	}
}
