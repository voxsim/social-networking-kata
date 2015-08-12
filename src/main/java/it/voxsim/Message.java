package it.voxsim;

import java.util.Calendar;

public class Message {

	private String description;
	private Calendar time;
	private String user;

	public Message(String user, String description, Calendar time) {
		this.user = user;
		this.description = description;
		this.time = time;
	}

	public String getUser() {
		return this.user;
	}

	public String getDescription() {
		return description;
	}

	public Calendar getTime() {
		return time;
	}

}
