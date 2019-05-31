package yuan.interview.railroad.interactive;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import yuan.interview.railroad.graph.base.GraphReader;
import yuan.interview.railroad.graph.policy.GraphPolicy;
import yuan.interview.railroad.impl.Y_Railroad_Info.PlainTextGraphReader;
import yuan.interview.railroad.impl.Y_Railroad_Info.SimpleCommandParser;
import yuan.interview.railroad.impl.Y_Railroad_Info.SimpleMapPolicy;
import yuan.interview.railroad.test.util.TWDataProvider;

/**
 * @ClassName: CommandExecutorTest
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-下午5:47:09
 */
@RunWith(Parameterized.class)
public class CommandExecutorTest extends TWDataProvider {

	private CommandExecutor commandExecutor = null;
	
	private String command = null;
	private String result = null;

	public CommandExecutorTest(String command, String result) {
		this.command = command;
		this.result = result;
	}

	@Parameters
	public static List<String[]> data() {
		List<String[]> dataSet = Arrays.asList(
															new String[][]{
																{"dist -p A-B-C","9"},
																{"dist -p A-D","5"},
																{"dist -p A-D-C","13"},
																{"dist -p A-E-B-C-D","22"},
																{"dist -p A-E-D","NO SUCH ROUTE"},
																{"count -f s -b C -e C -M 3","2"},
																{"count -f s -b A -e C -m 4 -M 4","3"},
																{"dist -f md -b A -e C","9"},
																{"dist -f md -b B -e B","9"},
																{"count -f d -b C -e C -M n30","7"}
															}
														);
		return dataSet;
	}

	@Test
	public void testExec() throws IOException {
		String runResult = null;
		try {
			runResult = String.valueOf(commandExecutor.exec(command));
		} catch(Exception e) {
			runResult = e.getMessage();
		}
		
		Assert.assertThat("运行命令 " + command + " 的结果不正确.", runResult,CoreMatchers.equalTo(result));
	}
	
	@Before
	public void beforeClass() throws IOException {
		GraphPolicy<Command, ?> mapPolicy = SimpleMapPolicy.getInstance();
		GraphReader graphReader = new PlainTextGraphReader(dataPath);
		mapPolicy.setGraphReader(graphReader);
		Map<String,Command> commands = mapPolicy.getCommands();
		
		commandExecutor = new CommandExecutor();
		commandExecutor.setCommandParser(SimpleCommandParser.getInstance());
		commandExecutor.registeCommands(commands);
	}

}
