package it.voxsim;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.GregorianCalendar;

import it.voxsim.command.CommandDispatcher;
import it.voxsim.repository.InMemoryLinkRepository;
import it.voxsim.repository.InMemoryMessageRepository;
import it.voxsim.repository.LinkRepository;
import it.voxsim.repository.MessageRepository;

public class App {
	private SocialNetworkingClient client;

	public App(SocialNetworkingClient client) {
		this.client = client;
	}

	public void run(Reader input, PrintStream output) {
		try {
			BufferedReader bufferedReader = new BufferedReader(input);

			String command;
			output.print("> ");
			while ((command = bufferedReader.readLine()) != null) {
				String result = client.process(command, new GregorianCalendar());

				if (!result.isEmpty())
					output.println(result);

				output.print("> ");
			}
			input.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		CommandDispatcher commandDispatcher = createCommandDispatcher();
		SocialNetworkingClient client = new SocialNetworkingClient(commandDispatcher);
		App app = new App(client);
		app.run(new InputStreamReader(System.in), System.out);
	}

	private static CommandDispatcher createCommandDispatcher() {
		MessageRepository messageRepository = new InMemoryMessageRepository();
		LinkRepository linkRepository = new InMemoryLinkRepository();
		return new CommandDispatcher(messageRepository, linkRepository);
	}
}
