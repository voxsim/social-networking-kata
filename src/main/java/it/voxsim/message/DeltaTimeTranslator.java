package it.voxsim.message;

import java.util.Calendar;

public interface DeltaTimeTranslator {

	String translate(Calendar endTime, Calendar startTime);

}
