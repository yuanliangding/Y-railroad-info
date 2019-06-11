package yuan.interview.railroad.interactive;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
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
public class CommandExecutor_Exec_Test extends CommandExecutorPrepared {
	
	@Rule
	public final ExpectedException noSuchRouteException = ExpectedException.none();

	@Test
	public void testExec() throws IOException {
		
		Map<String, String> options1 = new HashMap<>();
		options1.put("-a", "x");
		options1.put("-b", "y");
		CommandData commandData1 = new CommandData("cmd1", options1);
		String result1 = (String) commandExecutor.exec(commandData1);
		
		Assert.assertThat("运行命令没有返回有效果结果", result1, CoreMatchers.equalTo("cmd1 -> -a=x,-b=y"));
		
		Map<String, String> options2 = new HashMap<>();
		options2.put("-m", "p");
		options2.put("-n", "q");
		CommandData commandData2 = new CommandData("cmd2", options2);
		String result2 = (String) commandExecutor.exec(commandData2);
		
		Assert.assertThat("运行命令没有返回有效果结果", result2, CoreMatchers.equalTo("cmd2 -> -m=p,-n=q"));
	}
	
	@Test
	public void testExecWithException() throws IOException {
		noSuchRouteException.expect(InteractiveException.class);
		noSuchRouteException.expectMessage("CMD Exception");
		
		CommandData commandData = new CommandData("cmdException", null);
		commandExecutor.exec(commandData);
	}

}
