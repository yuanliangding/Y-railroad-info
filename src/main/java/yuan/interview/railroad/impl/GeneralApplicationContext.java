package yuan.interview.railroad.impl;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.stream.Collectors;

import yuan.interview.railroad.core.ApplicationContext;
import yuan.interview.railroad.graph.policy.GraphPolicy;
import yuan.interview.railroad.impl.Y_Railroad_Info.TWGraphReader;
import yuan.interview.railroad.interactive.Command;
import yuan.interview.railroad.interactive.CommandExecutor;

/** 
 * @ClassName: GeneralApplicationContext
 * @Description:  Y-Railroad-Info系统Context
 * 						简化版的通勤交通线路查询系统
 * 						1 从本地文本文件读取通勤新路数据{@link TWGraphReader}
 * 						2 基于命令行窗口交互
 * 
 * @see TWGraphReader
 * @see XStyleCommandParser
 * @see GeneralGraphPolicy
 * @see CommandExecutor
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午7:18:08
 */
public abstract class GeneralApplicationContext implements ApplicationContext {
	
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
		GraphPolicy<Command, ?> mapPolicy = createGraphPolicy(mapUrl);
		Map<String,Command> commands = mapPolicy.getCommands();
		
		// 2 准备命令接收器
		CommandExecutor commandExecutor = new CommandExecutor();
		commandExecutor.setIO(standardIn, standardOut, standardError);
		commandExecutor.setCommandParser(new XStyleCommandParser());
		commandExecutor.setExitCommand(exit);
		commandExecutor.registeCommands(commands);

		// 3 在终端显示banner
		String bannerStr =
				banner() +
				commands.keySet().stream().collect(Collectors.joining(", "));
		System.out.println(bannerStr);
		
		// 4 命令接收器进入工作状态
		commandExecutor.work();
	}
	
	protected abstract GraphPolicy<Command, ?> createGraphPolicy(String mapUrl);

}
