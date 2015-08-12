package it.voxsim;

import java.util.Calendar;

public interface Command {
	public abstract String execute(String username, String argument, Calendar timeOfExecution);
}
