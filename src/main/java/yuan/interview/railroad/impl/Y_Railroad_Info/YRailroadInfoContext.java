package yuan.interview.railroad.impl.Y_Railroad_Info;

import yuan.interview.railroad.graph.io.GraphReader;
import yuan.interview.railroad.graph.policy.GraphPolicy;
import yuan.interview.railroad.impl.GeneralApplicationContext;
import yuan.interview.railroad.impl.GeneralGraphPolicy;
import yuan.interview.railroad.impl.XStyleCommandParser;
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
public class YRailroadInfoContext extends GeneralApplicationContext {
	
	private final String VERSION = "1.0.0";

	@Override
	protected GraphPolicy<Command, ?> createGraphPolicy(String mapUrl) {
		GraphPolicy<Command, ?> mapPolicy = new YRailroadGraphPolicy();
		GraphReader graphReader = new TWGraphReader(mapUrl);
		mapPolicy.setGraphReader(graphReader);
		return mapPolicy;
	}

	@Override
	public String banner() {
		String banner = 
				"                                                               \n" + 
				",--.   ,--.    ,------.       ,--,--.                     ,--. \n" + 
				" \\  `.'  ,-----|  .--. ',--,--`--|  ,--.--.,---. ,--,--.,-|  | \n" + 
				"  '.    /'-----|  '--'.' ,-.  ,--|  |  .--| .-. ' ,-.  ' .-. | \n" + 
				"    |  |       |  |\\  \\\\ '-'  |  |  |  |  ' '-' \\ '-'  \\ `-' | \n" + 
				"    `--'       `--' '--'`--`--`--`--`--'   `---' `--`--'`---'  \n" + 
				"欢迎使用 Y-Railroad info 系统  (" + VERSION + ")" +
				"\n\n" +
				"启动该程序的完整命令是： java -jar XXX.jar -data map.txt -exit quit\n" +
				"	XXX.jar		该程序jar包\n" +
				"	map.txt		地图数据文件(每行一条信息.格式如 AB32, A、B分别代表一个节点，32为A到B的路程)\n" +
				"	quit		这里输入quit，程序的退出命令就是quit。默认为exit\n" +
				"\n" + 
				"你可以使用的命令有:\n";
		
		return banner;
	}

}
