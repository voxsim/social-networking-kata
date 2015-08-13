package it.voxsim.command;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.voxsim.command.Command;
import it.voxsim.command.CommandDispatcher;
import it.voxsim.command.FollowCommand;
import it.voxsim.command.PostCommand;
import it.voxsim.command.ReadCommand;
import it.voxsim.command.WallCommand;

public class CommandDispatcherTest {
	
	private CommandDispatcher commandDispatcher;

	@Before
	public void setUp() {
		commandDispatcher = new CommandDispatcher(null, null);
	}
	
	@Test
	public void nullParameterShouldDispatchReadCommand() throws Exception {
		Command command = commandDispatcher.dispatch(null);
		
		assertEquals(ReadCommand.class, command.getClass());
	}

	@Test
	public void arrowParameterShouldDispatchPostCommand() throws Exception {
		Command command = commandDispatcher.dispatch("->");
		
		assertEquals(PostCommand.class, command.getClass());
	}

	@Test
	public void followsParameterShouldDispatchPostCommand() throws Exception {
		Command command = commandDispatcher.dispatch("follows");
		
		assertEquals(FollowCommand.class, command.getClass());
	}
	
	@Test
	public void wallParameterShouldDispatchPostCommand() throws Exception {
		Command command = commandDispatcher.dispatch("wall");
		
		assertEquals(WallCommand.class, command.getClass());
	}
}