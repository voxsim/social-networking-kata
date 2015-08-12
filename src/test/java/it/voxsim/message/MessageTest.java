package it.voxsim.message;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class MessageTest {

	private static final String A_USER = "user";
	private static final String A_DESCRIPTION = "description";
	private static final Calendar A_TIME = new GregorianCalendar();
	private static final String A_DELTA_TIME = "0 second ago";
	private static final Message A_MESSAGE = Message.create(A_USER, A_DESCRIPTION, A_TIME);

	@Test
	public void descriptionShouldReturnTheUser() {
		assertEquals(A_USER, A_MESSAGE.description("%{user}", new GregorianCalendar()));
	}
	
	@Test
	public void descriptionShouldReturnTheDescription() {
		assertEquals(A_DESCRIPTION, A_MESSAGE.description("%{description}", new GregorianCalendar()));
	}
	
	@Test
	public void descriptionShouldReturnTheDeltaTime() {
		assertEquals(A_DELTA_TIME, A_MESSAGE.description("%{time}", A_TIME));
	}
}