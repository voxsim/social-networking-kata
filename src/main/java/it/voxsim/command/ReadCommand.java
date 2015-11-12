package it.voxsim.command;

import java.util.Calendar;
import java.util.List;

import it.voxsim.message.Message;
import it.voxsim.message.MultiMessagePrinter;
import it.voxsim.repository.MessageRepository;

public class ReadCommand implements Command {

	private MessageRepository messageRepository;
	private MultiMessagePrinter printer;

	public ReadCommand(MessageRepository messageRepository, MultiMessagePrinter printer) {
		this.messageRepository = messageRepository;
		this.printer = printer;
	}

	public String execute(String username, String argument, Calendar timeOfExecution) {
		List<Message> messages = messageRepository.retrieveMessagesByUsernameOrderedByTime(username);
		return printer.print(username, messages, timeOfExecution);
	}

	public static ReadCommand create(MessageRepository messageRepository) {
		MultiMessagePrinter printer = new MultiMessagePrinter("no messages from %{username}",
				"%{description} (%{time})");
		return new ReadCommand(messageRepository, printer);
	}
}