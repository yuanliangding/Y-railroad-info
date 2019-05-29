package yuanliangding.interview.YRailroadInfo.map.simple;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import yuanliangding.interview.YRailroadInfo.graph.GraphReader;
import yuanliangding.interview.YRailroadInfo.interactive.Command;
import yuanliangding.interview.YRailroadInfo.map.MapPolicy;

/**
 * @ClassName: TerminatorCommandReceiverTest
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-下午5:47:09
 */
@RunWith(Parameterized.class)
public class TerminatorCommandReceiverTest {

	private static String path = null;
	private static TerminatorCommandReceiver terminatorCommandReceiver = null;
	
	private String command = null;
	private String result = null;

	public TerminatorCommandReceiverTest(String command, String result) {
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
			runResult = String.valueOf(terminatorCommandReceiver.exec(command));
		} catch(Exception e) {
			runResult = e.getMessage();
		}
		
		Assert.assertThat("运行命令 " + command + " 的结果不正确.", runResult,CoreMatchers.equalTo(result));
	}
	
	@BeforeClass
	public static void beforeClass() throws IOException {
		File mapTextFile = File.createTempFile("y_railroad_info_map_plain_text", ".txt");
		path = mapTextFile.getCanonicalPath();
		try (FileWriter fileWriter = new FileWriter(mapTextFile)) {
			fileWriter.write("AB5\nBC4\nCD8\nDC8\nDE6\nAD5\nCE2\nEB3\nAE7\n");
		}
		
		MapPolicy<Command, ?> mapPolicy = SimpleMapPolicy.getInstance();
		GraphReader graphReader = new PlainTextGraphReader(path);
		mapPolicy.setGraphReader(graphReader);
		Map<String,Command> commands = mapPolicy.getCommands();
		
		terminatorCommandReceiver = TerminatorCommandReceiver.getInstance();
		terminatorCommandReceiver.setCommandParser(SimpleCommandParser.getInstance());
		terminatorCommandReceiver.registeCommands(commands);
	}

	@AfterClass
	public static void afterClass() {
		File mapTextFile = new File(path);
		mapTextFile.deleteOnExit();
	}

}
