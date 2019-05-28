package yuanliangding.interview.YRailroadInfo.core;

import yuanliangding.interview.YRailroadInfo.interactive.CommandReceiver;
import yuanliangding.interview.YRailroadInfo.interactive.SimpleCommandParser;
import yuanliangding.interview.YRailroadInfo.interactive.TerminatorCommandReceiver;
import yuanliangding.interview.YRailroadInfo.map.StopMap;
import yuanliangding.interview.YRailroadInfo.reader.MapReader;
import yuanliangding.interview.YRailroadInfo.reader.PlainTextMapReader;

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
	protected void start(String mapUrl, String exit) {
		
		// 1 初始化地图
		StopMap stopMap = StopMap.getInstance();
		MapReader mapReader = PlainTextMapReader.getInstance();
		mapReader.from(stopMap, mapUrl);
		
		// 2 准备命令接收器
		CommandReceiver commandReceiver = TerminatorCommandReceiver.getInstance();
		commandReceiver.setCommandParser(SimpleCommandParser.getInstance());
		commandReceiver.setExitCommand(exit);
//		commandReceiver.registeCommand(name, command);

		// 3 在终端显示banner
		System.out.println(banner());
		
		// 4 命令接收器进入工作状态
		commandReceiver.work();
	}

	@Override
	protected String banner() {
		return "这个系统好用不.....";
	}

}
