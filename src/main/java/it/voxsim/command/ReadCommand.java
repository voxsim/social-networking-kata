package it.voxsim.command;

import java.util.Calendar;
import java.util.List;

import it.voxsim.DeltaTimeTranslator;
import it.voxsim.Message;
import it.voxsim.MessageRepository;

public class ReadCommand implements Command {

	private MessageRepository messageRepository;
	private DeltaTimeTranslator deltaTimeTranslator;

	public ReadCommand(MessageRepository messageRepository, DeltaTimeTranslator deltaTimeTranslator) {
		this.messageRepository = messageRepository;
		this.deltaTimeTranslator = deltaTimeTranslator;
	}

	@Override
	public String execute(String username, String argument, Calendar timeOfExecution) {
		List<Message> messages = messageRepository.retrieveMessagesByUsername(username);

		if (messages.isEmpty())
			return "no messages from " + username;

		String output = "";
		String separator = "";
		for (Message message : messages) {
			String deltaTime = deltaTimeTranslator.translate(timeOfExecution, message.getTime());
			output += separator + message.getDescription() + " (" + deltaTime + ")";
			separator = "\n";
		}
		return output;
	}
}