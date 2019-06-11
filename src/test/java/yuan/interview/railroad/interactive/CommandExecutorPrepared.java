package yuan.interview.railroad.interactive;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;

import yuan.interview.railroad.exception.InteractiveException;

/**
 * @ClassName : CommandExecutorPrepared
 * @Description :  
 * @author  袁良锭(https://github.com/yuanliangding)
 * @date  2019年6月11日-下午7:06:05
 */
public class CommandExecutorPrepared {

	public CommandExecutor commandExecutor;
	
	@Before
	public void before() throws IOException {
		commandExecutor = new CommandExecutor();
		commandExecutor.registeCommands(getCommands());
	}
	
	private Map<String, Command> getCommands() {
		Map<String, Command> result = new HashMap<>();
		
		result.put("cmd1", cd -> {
			return commandDataToString(cd);
		});
		result.put("cmd2", cd -> {
			return commandDataToString(cd);
		});
		result.put("cmdException", cd -> {
			throw new InteractiveException("CMD Exception");
		});
		
		return result;
	}
	
	private String commandDataToString(CommandData commandData) {
		Map<String, String> options = commandData.getOptions();
		return commandData.getName() + " -> " + options.keySet().stream().sorted().map(k -> k + "=" + options.get(k)).collect(Collectors.joining(","));
	}

}