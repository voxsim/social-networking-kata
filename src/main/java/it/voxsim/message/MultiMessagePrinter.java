package it.voxsim.message;

import java.util.Calendar;
import java.util.List;

import it.voxsim.message.Message;

public class MultiMessagePrinter {
	private String emptyMessage;
	private String formatMessage;

	public MultiMessagePrinter(String emptyMessage, String formatMessage) {
		this.emptyMessage = emptyMessage;
		this.formatMessage = formatMessage;
	}
	
	public String print(String username, List<Message> messages, Calendar timeOfExecution) {
		if (messages.isEmpty())
			return emptyMessage.replace("%{username}", username);

		String output = "";
		String separator = "";
		for (Message message : messages) {
			output += separator + message.description(formatMessage, timeOfExecution);
			separator = "\n";
		}
		return output;
	}
}
