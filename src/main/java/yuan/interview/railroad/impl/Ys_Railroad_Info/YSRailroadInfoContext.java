package yuan.interview.railroad.impl.Ys_Railroad_Info;

import yuan.interview.railroad.graph.io.GraphReader;
import yuan.interview.railroad.graph.policy.GraphPolicy;
import yuan.interview.railroad.impl.GeneralApplicationContext;
import yuan.interview.railroad.impl.XStyleCommandParser;
import yuan.interview.railroad.interactive.Command;
import yuan.interview.railroad.interactive.CommandExecutor;

/** 
 * @ClassName: YSRailroadInfoContext
 * @Description:  Y*-Railroad-Info系统Context
 * 						Y-Railroad-Info升级版本
 * 						1 从本地文本文件读取通勤新路数据{@link QuadGraphReader}
 * 						2 基于命令行窗口交互
 * 
 * @see QuadGraphReader
 * @see XStyleCommandParser
 * @see YSRailroadGraphPolicy
 * @see CommandExecutor
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午7:18:08
 */
public class YSRailroadInfoContext extends GeneralApplicationContext {
	
	private final String VERSION = "1.0.0";

	@Override
	protected GraphPolicy<Command, ?> createGraphPolicy(String mapUrl) {
		GraphPolicy<Command, ?> mapPolicy = new YSRailroadGraphPolicy();
		GraphReader graphReader = new QuadGraphReader(mapUrl);
		mapPolicy.setGraphReader(graphReader);
		return mapPolicy;
	}

	@Override
	public String banner() {
		String banner = 
				"                                                               \n" +
				"\n" + 
				"oooooo   oooo    o            ooooooooo.              o8o  oooo                                     .o8  \n" + 
				" `888.   .8'  `8.8.8'         `888   `Y88.            `\"'  `888                                    \"888  \n" + 
				"  `888. .8'   .8'8`8.          888   .d88'  .oooo.   oooo   888  oooo d8b  .ooooo.   .oooo.    .oooo888  \n" + 
				"   `888.8'       \"             888ooo88P'  `P  )88b  `888   888  `888\"\"8P d88' `88b `P  )88b  d88' `888  \n" + 
				"    `888'             8888888  888`88b.     .oP\"888   888   888   888     888   888  .oP\"888  888   888  \n" + 
				"     888                       888  `88b.  d8(  888   888   888   888     888   888 d8(  888  888   888  \n" + 
				"    o888o                     o888o  o888o `Y888\"\"8o o888o o888o d888b    `Y8bod8P' `Y888\"\"8o `Y8bod88P\" \n" + 
				"                                                                                                         \n" + 
				"欢迎使用 Y*-Railroad info 系统  (" + VERSION + ")" +
				"\n\n" +
				"启动该程序的完整命令是： java -jar XXX.jar -data map.txt -exit quit\n" +
				"	XXX.jar		该程序jar包\n" +
				"	map.txt		地图数据文件(每行一条信息.格式如A->B DIST:5 A起点，B终点，DIST权重分量名，5 权重分量值)\n" +
				"	quit		这里输入quit，程序的退出命令就是quit。默认为exit\n" +
				"\n" + 
				"你可以使用的命令有:\n";
		
		return banner;
	}

}
