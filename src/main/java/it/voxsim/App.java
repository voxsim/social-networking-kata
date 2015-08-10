package it.voxsim;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.GregorianCalendar;

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
				output.println(client.process(command, new GregorianCalendar()));
				output.print("> ");
			}
			input.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		MessageRepository repository = new InMemoryMessageRepository();
		SocialNetworkingClient client = new SocialNetworkingClient(repository);
		App app = new App(client);
		app.run(new InputStreamReader(System.in), System.out);
	}
}
