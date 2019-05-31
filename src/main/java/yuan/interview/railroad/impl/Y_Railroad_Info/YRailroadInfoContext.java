package yuan.interview.railroad.impl.Y_Railroad_Info;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.stream.Collectors;

import yuan.interview.railroad.core.ApplicationContext;
import yuan.interview.railroad.graph.base.GraphReader;
import yuan.interview.railroad.graph.policy.GraphPolicy;
import yuan.interview.railroad.interactive.Command;
import yuan.interview.railroad.interactive.CommandExecutor;

/** 
 * @ClassName: YRailroadInfoContext
 * @Description:  Y-Railroad-Info系统Context.
 * 						简化版的通勤交通线路查询系统.
 * 						1 从本地文本文件读取通勤新路数据{@link TWGraphReader}
 * 						2 基于命令行窗口交互
 * 
 * @see TWGraphReader
 * @see UnixStyleCommandParser
 * @see YRailroadGraphPolicy
 * @see CommandExecutor
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午7:18:08
 */
public class YRailroadInfoContext extends ApplicationContext {
	
	private final String VERSION = "1.0.0";

	@Override
	public void start(
			InputStream standardIn, 
			PrintStream standardOut,
			PrintStream standardError,
			String mapUrl,
			String exit) {
		
		if (exit == null || "".equals(exit)) {
			exit = "exit";
		}
		
		// 1 初始化地图
		GraphPolicy<Command, ?> mapPolicy = new YRailroadGraphPolicy();
		GraphReader graphReader = new TWGraphReader(mapUrl);
		mapPolicy.setGraphReader(graphReader);
		Map<String,Command> commands = mapPolicy.getCommands();
		
		// 2 准备命令接收器
		CommandExecutor commandExecutor = new CommandExecutor();
		commandExecutor.setIO(standardIn, standardOut, standardError);
		commandExecutor.setCommandParser(new UnixStyleCommandParser());
		commandExecutor.setExitCommand(exit);
		commandExecutor.registeCommands(commands);

		// 3 在终端显示banner
		String bannerStr = 
				banner() + "\n\n" +
				"输入 " + exit + "退出该程序!\n" + 
				"\n" + 
				"你可以使用的命令有:\n" + 
				commands.keySet().stream().collect(Collectors.joining(", "));
		System.out.println(bannerStr);
		
		// 4 命令接收器进入工作状态
		commandExecutor.work();
	}

	@Override
	protected String banner() {
		String banner = 
				"                                                               \n" + 
				",--.   ,--.    ,------.       ,--,--.                     ,--. \n" + 
				" \\  `.'  ,-----|  .--. ',--,--`--|  ,--.--.,---. ,--,--.,-|  | \n" + 
				"  '.    /'-----|  '--'.' ,-.  ,--|  |  .--| .-. ' ,-.  ' .-. | \n" + 
				"    |  |       |  |\\  \\\\ '-'  |  |  |  |  ' '-' \\ '-'  \\ `-' | \n" + 
				"    `--'       `--' '--'`--`--`--`--`--'   `---' `--`--'`---'  \n" + 
				"                                                               \n" +
				"欢迎使用 Y-Railroad info 系统  (" + VERSION + ")";
		
		return banner;
	}

}
