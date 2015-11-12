package it.voxsim.command;

import java.util.Calendar;

public interface Command {
	String execute(String username, String argument, Calendar timeOfExecution);
}
