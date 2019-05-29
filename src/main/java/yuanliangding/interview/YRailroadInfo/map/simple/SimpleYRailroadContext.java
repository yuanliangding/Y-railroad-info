package yuanliangding.interview.YRailroadInfo.map.simple;

import java.util.Map;
import java.util.stream.Collectors;

import yuanliangding.interview.YRailroadInfo.core.YRailroadContext;
import yuanliangding.interview.YRailroadInfo.graph.GraphReader;
import yuanliangding.interview.YRailroadInfo.interactive.Command;
import yuanliangding.interview.YRailroadInfo.interactive.CommandReceiver;
import yuanliangding.interview.YRailroadInfo.map.MapPolicy;

/** 
 * @ClassName: SimpleYRailroadContext
 * @Description:  简化版的通勤交通线路查询系统
 * 						1 地图从本地简易文件读取
 * 						2 命令接收器是基于命令行窗口
 * 						3 采用简单的命令格式,参考{@link SimpleCommandParser}
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午7:18:08
 */
public class SimpleYRailroadContext extends YRailroadContext {

	@Override
	public void start(String mapUrl, String exit) {
		
		// 1 初始化地图
		MapPolicy<Command, ?> mapPolicy = SimpleMapPolicy.getInstance();
		GraphReader graphReader = new PlainTextGraphReader("url");
		mapPolicy.setGraphReader(graphReader);
		Map<String,Command> commands = mapPolicy.getCommands();
		
		// 2 准备命令接收器
		CommandReceiver commandReceiver = TerminatorCommandReceiver.getInstance();
		commandReceiver.setCommandParser(SimpleCommandParser.getInstance());
		commandReceiver.setExitCommand(exit);
		commandReceiver.registeCommands(commands);

		// 3 在终端显示banner
		String bannerStr = 
				banner() + 
				"\n\n" + 
				"你可以使用的命令有:\n" + 
				commands.keySet().stream().collect(Collectors.joining(","));
		System.out.println(bannerStr);
		
		// 4 命令接收器进入工作状态
		commandReceiver.work();
	}

	@Override
	protected String banner() {
		return "欢迎使用Y-Railroad info系统.(ver 1.0.0)";
	}

}
