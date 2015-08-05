package it.voxsim;

import java.util.Calendar;

public class EnglishDeltaTimeTranslator implements DeltaTimeTranslator {

	public String translate(Calendar endTime, Calendar startTime) {
		return "1 second ago";
	}

}
