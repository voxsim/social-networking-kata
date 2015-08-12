package it.voxsim.command;

import java.util.Calendar;
import java.util.List;

import it.voxsim.message.Message;
import it.voxsim.repository.MessageRepository;

public class ReadCommand implements Command {

	private MessageRepository messageRepository;

	public ReadCommand(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@Override
	public String execute(String username, String argument, Calendar timeOfExecution) {
		List<Message> messages = messageRepository.retrieveMessagesByUsername(username);

		if (messages.isEmpty())
			return "no messages from " + username;

		String output = "";
		String separator = "";
		for (Message message : messages) {
			output += separator + message.description("%{description} (%{time})", timeOfExecution);
			separator = "\n";
		}
		return output;
	}
}