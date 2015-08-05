package it.voxsim;

import java.util.Calendar;

public class Message {

	private String description;
	private Calendar time;

	public Message(String description, Calendar time) {
		this.description = description;
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public Calendar getTime() {
		return time;
	}

}
