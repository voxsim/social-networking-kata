package it.voxsim.message;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class EnglishDeltaTimeTranslatorTest {

	private static final long ONE_SECOND = 1000;
	private static final long ONE_MINUTE = 60 * ONE_SECOND;
	private static final long ONE_HOUR = 60 * ONE_MINUTE;
	private static final long ONE_DAY = 24 * ONE_HOUR;

	@Test
	public void sameTime() {
		EnglishDeltaTimeTranslator deltaTimeTranslator = new EnglishDeltaTimeTranslator();

		Calendar endTime = new GregorianCalendar();
		Calendar startTime = endTime;

		String deltaTimeDescription = deltaTimeTranslator.translate(endTime, startTime);

		assertEquals("0 second ago", deltaTimeDescription);
	}

	@Test
	public void oneSecondAgo() {
		EnglishDeltaTimeTranslator deltaTimeTranslator = new EnglishDeltaTimeTranslator();

		Calendar endTime = new GregorianCalendar();
		Calendar startTime = new GregorianCalendar();
		startTime.setTimeInMillis(endTime.getTimeInMillis() - ONE_SECOND);

		String deltaTimeDescription = deltaTimeTranslator.translate(endTime, startTime);

		assertEquals("1 second ago", deltaTimeDescription);
	}
	
	@Test
	public void oneMinuteAgo() {
		EnglishDeltaTimeTranslator deltaTimeTranslator = new EnglishDeltaTimeTranslator();

		Calendar endTime = new GregorianCalendar();
		Calendar startTime = new GregorianCalendar();
		startTime.setTimeInMillis(endTime.getTimeInMillis() - ONE_MINUTE);

		String deltaTimeDescription = deltaTimeTranslator.translate(endTime, startTime);

		assertEquals("1 minute ago", deltaTimeDescription);
	}
	
	@Test
	public void oneHourAgo() {
		EnglishDeltaTimeTranslator deltaTimeTranslator = new EnglishDeltaTimeTranslator();

		Calendar endTime = new GregorianCalendar();
		Calendar startTime = new GregorianCalendar();
		startTime.setTimeInMillis(endTime.getTimeInMillis() - 1 * ONE_HOUR);

		String deltaTimeDescription = deltaTimeTranslator.translate(endTime, startTime);

		assertEquals("1 hour ago", deltaTimeDescription);
	}

	@Test
	public void oneDayAgo() {
		EnglishDeltaTimeTranslator deltaTimeTranslator = new EnglishDeltaTimeTranslator();

		Calendar endTime = new GregorianCalendar();
		Calendar startTime = new GregorianCalendar();
		startTime.setTimeInMillis(endTime.getTimeInMillis() - 1 * ONE_DAY);

		String deltaTimeDescription = deltaTimeTranslator.translate(endTime, startTime);

		assertEquals("1 day ago", deltaTimeDescription);
	}
}
