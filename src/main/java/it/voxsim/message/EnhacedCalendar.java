package it.voxsim.message;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EnhacedCalendar extends GregorianCalendar {

	private static final long serialVersionUID = 1L;

	public EnhacedCalendar(Calendar startedCalendar, long diffTime) {
		setTimeInMillis(startedCalendar.getTimeInMillis() - diffTime);
	}

	public EnhacedCalendar() {
	}
}