package it.voxsim.command;

import java.util.Calendar;

public interface Command {
	public abstract String execute(String username, String argument, Calendar timeOfExecution);
}
