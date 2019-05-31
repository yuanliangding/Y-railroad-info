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
 * @Description:  简化版的通勤交通线路查询系统 Y-Railroad-Info
 * 						1 地图从本地简易文件读取
 * 						2 命令接收器是基于命令行窗口
 * 						3 采用简单的命令格式,参考{@link UnixStyleCommandParser}
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午7:18:08
 */
public class YRailroadInfoContext extends ApplicationContext {

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
		GraphPolicy<Command, ?> mapPolicy = YRailroadGraphPolicy.getInstance();
		GraphReader graphReader = new TWGraphReader(mapUrl);
		mapPolicy.setGraphReader(graphReader);
		Map<String,Command> commands = mapPolicy.getCommands();
		
		// 2 准备命令接收器
		CommandExecutor commandExecutor = new CommandExecutor();
		commandExecutor.setIO(standardIn, standardOut, standardError);
		commandExecutor.setCommandParser(UnixStyleCommandParser.getInstance());
		commandExecutor.setExitCommand(exit);
		commandExecutor.registeCommands(commands);

		// 3 在终端显示banner
		String bannerStr = 
				banner() + "\n" +
				"输入 " + exit + "退出该程序!\n" + 
				"\n\n" + 
				"你可以使用的命令有:\n" + 
				commands.keySet().stream().collect(Collectors.joining(", "));
		System.out.println(bannerStr);
		
		// 4 命令接收器进入工作状态
		commandExecutor.work();
	}

	@Override
	protected String banner() {
		return "欢迎使用 Y-Railroad info系统. (ver 1.0.0)";
	}

}
