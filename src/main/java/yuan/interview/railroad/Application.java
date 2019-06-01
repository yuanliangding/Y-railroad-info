package yuan.interview.railroad;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import yuan.interview.railroad.core.ApplicationContext;
import yuan.interview.railroad.impl.Y_Railroad_Info.YRailroadInfoContext;

/** 
 * @ClassName: Application
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-下午8:52:43
 */
public class Application {

	public static void main(String[] args) {
		
		/*
		 * 以 -data map.txt -exit quit 的方式接收参数
		 * map.txt		指定的地图数据文件(每行一条信息。格式如 AB32，A、B分别代表一个节点，32为A到B的路程)
		 * 					不指定,则以map/default.txt为默认的数据
		 * quit			这里输入quit，程序的退出命令就是quit，默认为exit
		 * 					不指定,则默认是‘exit’
		 * */
		
		// 处理和补充参数
		List<String> argsList = Stream.of(args).collect(Collectors.toList());
		if (argsList.size() % 2 == 1) {
			argsList.add(null);
		}
		
		Map<String,String> argsMap = IntStream.rangeClosed(1, argsList.size()/2).boxed()
				.collect(
					Collectors.toMap(
							i -> argsList.get(i*2-2), 
							i -> argsList.get(i*2-1)
						)
				);
		
		// 启动应用
		String data = argsMap.get("--data");
		String exit = argsMap.get("--exit");
		ApplicationContext applicationContext = new YRailroadInfoContext();
		applicationContext.start(System.in, System.out, System.err, data, exit);
	}

}
