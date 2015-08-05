package it.voxsim;

import java.util.Calendar;
import java.util.List;

public class ReadCommand extends Command {

	private UserRepository repository;
	private DeltaTimeTranslator deltaTimeTranslator;
	
	public ReadCommand() {
		this(new InMemoryUserRepository(), new EnglishDeltaTimeTranslator());
	}

	public ReadCommand(UserRepository repository, DeltaTimeTranslator deltaTimeTranslator) {
		this.repository = repository;
		this.deltaTimeTranslator = deltaTimeTranslator;
	}

	@Override
	public String execute(String username, String argument, Calendar timeOfExecution) {
		List<Message> messages = repository.retrieveMessagesByUsername(username);
		
		if(messages == null || messages.isEmpty())
			return "no messages from " + username;
		
		String output = "";
		for(Message message : messages) {
			output += message.getDescription() + " (" + deltaTimeTranslator.translate(timeOfExecution, message.getTime()) + ")";
		}
		return output;
	}
}