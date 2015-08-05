package it.voxsim;

import java.util.Calendar;

public interface DeltaTimeTranslator {

	String translate(Calendar endTime, Calendar startTime);

}
