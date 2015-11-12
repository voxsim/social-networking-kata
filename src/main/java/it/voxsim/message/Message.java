package it.voxsim.message;

import java.util.Calendar;

public class Message implements Comparable<Message> {

	private String description;
	private Calendar time;
	private String user;
	private DeltaTimeTranslator deltaTimeTranslator;

	private Message(String user, String description, Calendar time, DeltaTimeTranslator deltaTimeTranslator) {
		this.user = user;
		this.description = description;
		this.time = time;
		this.deltaTimeTranslator = deltaTimeTranslator;
	}

	public String description(String format, Calendar timeOfExecution) {
		return format.replace("%{user}", user).replace("%{description}", description).replace("%{time}",
				deltaTime(timeOfExecution));
	}

	private String deltaTime(Calendar timeOfExecution) {
		return deltaTimeTranslator.translate(timeOfExecution, time);
	}

	public int compareTo(Message anotherMessage) {
		return (int) (anotherMessage.time.getTimeInMillis() - time.getTimeInMillis());
	}

	public static Message create(String user, String description, Calendar time) {
		DeltaTimeTranslator deltaTimeTranslator = new EnglishDeltaTimeTranslator();
		return new Message(user, description, time, deltaTimeTranslator);
	}
}