package yuan.interview.railroad.interactive;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import yuan.interview.railroad.exception.InteractiveException;

/**
 * @ClassName: CommandExecutor_Exec_Test
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-下午5:47:09
 */
public class CommandExecutor_Exec_Test {

	private CommandExecutor commandExecutor = null;
	
	@Rule
	public final ExpectedException noSuchRouteException = ExpectedException.none();

	@Test
	public void testExec() throws IOException {
		
		Map<String, String> options1 = new HashMap<>();
		CommandData commandData1 = new CommandData("cmd1", options1);
		CommandResult result1 = (CommandResult) commandExecutor.exec(commandData1);
		
		Assert.assertThat("运行命令没有返回有效果结果", result1, CoreMatchers.notNullValue());
		Assert.assertThat("运行前选取的命令错误", result1.getCommand(), CoreMatchers.equalTo("cmd1"));
		Assert.assertThat("命令中的具体数据错误", result1.getCommandData(), CoreMatchers.equalTo(commandData1));
		
		Map<String, String> options2 = new HashMap<>();
		CommandData commandData2 = new CommandData("cmd2", options2);
		CommandResult result2 = (CommandResult) commandExecutor.exec(commandData2);
		
		Assert.assertThat("运行命令没有返回有效果结果", result2, CoreMatchers.notNullValue());
		Assert.assertThat("运行前选取的命令错误", result2.getCommand(), CoreMatchers.equalTo("cmd2"));
		Assert.assertThat("命令中的具体数据错误", result2.getCommandData(), CoreMatchers.equalTo(commandData2));
	}
	
	@Test
	public void testExecWithException() throws IOException {
		noSuchRouteException.expect(InteractiveException.class);
		noSuchRouteException.expectMessage("CMD Exception");
		
		CommandData commandData = new CommandData("cmdException", null);
		commandExecutor.exec(commandData);
	}
	
	@Before
	public void before() throws IOException {
		commandExecutor = new CommandExecutor();
		commandExecutor.registeCommands(getCommands());
	}
	
	private Map<String, Command> getCommands() {
		Map<String, Command> result = new HashMap<>();
		
		result.put("cmd1", cd -> {
			return new CommandResult("cmd1", cd);
		});
		result.put("cmd2", cd -> {
			return new CommandResult("cmd2", cd);
		});
		result.put("cmdException", cd -> {
			throw new InteractiveException("CMD Exception");
		});
		
		return result;
	}
	
	private class CommandResult {
		private String command;
		private CommandData commandData;
		
		public CommandResult(String command, CommandData commandData) {
			super();
			this.command = command;
			this.commandData = commandData;
		}
		
		public String getCommand() {
			return command;
		}
		public CommandData getCommandData() {
			return commandData;
		}
	}

}
