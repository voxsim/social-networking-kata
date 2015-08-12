package it.voxsim.message;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TimeDescriptionTest {

	@Test
	public void singleElementDescription() {
		TimeDescription timeDescription = new TimeDescription(1, TimeType.SECOND);
	
		assertEquals("1 second", timeDescription.description());
	}
	
	@Test
	public void multipleElementDescription() {
		TimeDescription timeDescription = new TimeDescription(2, TimeType.SECOND);
		
		assertEquals("2 seconds", timeDescription.description());
	}
}