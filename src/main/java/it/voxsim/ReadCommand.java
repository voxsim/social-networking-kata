package it.voxsim;

import java.util.Calendar;
import java.util.List;

public class ReadCommand extends Command {

	private MessageRepository repository;
	private DeltaTimeTranslator deltaTimeTranslator;

	public ReadCommand(MessageRepository repository, DeltaTimeTranslator deltaTimeTranslator) {
		this.repository = repository;
		this.deltaTimeTranslator = deltaTimeTranslator;
	}

	@Override
	public String execute(String username, String argument, Calendar timeOfExecution) {
		List<Message> messages = repository.retrieveMessagesByUsername(username);

		if (messages == null || messages.isEmpty())
			return "no messages from " + username;

		String output = "";
		String separator = "";
		for (Message message : messages) {
			String deltaTime = deltaTimeTranslator.translate(timeOfExecution, message.getTime());
			output += separator + message.getDescription() + " (" + deltaTime + ")";
			separator = "\n";
		}
		return output + "\n";
	}
}