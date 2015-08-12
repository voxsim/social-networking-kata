package it.voxsim.message;

import static it.voxsim.AssertUtils.*;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class EnglishDeltaTimeTranslatorTest {

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
		Calendar startTime = new EnhacedCalendar(endTime, ONE_SECOND);

		String deltaTimeDescription = deltaTimeTranslator.translate(endTime, startTime);

		assertEquals("1 second ago", deltaTimeDescription);
	}
	
	@Test
	public void oneMinuteAgo() {
		EnglishDeltaTimeTranslator deltaTimeTranslator = new EnglishDeltaTimeTranslator();

		Calendar endTime = new GregorianCalendar();
		Calendar startTime = new EnhacedCalendar(endTime, ONE_MINUTE);

		String deltaTimeDescription = deltaTimeTranslator.translate(endTime, startTime);

		assertEquals("1 minute ago", deltaTimeDescription);
	}
	
	@Test
	public void oneHourAgo() {
		EnglishDeltaTimeTranslator deltaTimeTranslator = new EnglishDeltaTimeTranslator();

		Calendar endTime = new GregorianCalendar();
		Calendar startTime = new EnhacedCalendar(endTime, ONE_HOUR);

		String deltaTimeDescription = deltaTimeTranslator.translate(endTime, startTime);

		assertEquals("1 hour ago", deltaTimeDescription);
	}

	@Test
	public void oneDayAgo() {
		EnglishDeltaTimeTranslator deltaTimeTranslator = new EnglishDeltaTimeTranslator();

		Calendar endTime = new GregorianCalendar();
		Calendar startTime = new EnhacedCalendar(endTime, ONE_DAY);

		String deltaTimeDescription = deltaTimeTranslator.translate(endTime, startTime);

		assertEquals("1 day ago", deltaTimeDescription);
	}
}
