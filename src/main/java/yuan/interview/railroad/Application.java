package yuan.interview.railroad;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import yuan.interview.railroad.core.YRailroadContext;
import yuan.interview.railroad.map.simple.SimpleYRailroadContext;

/** 
 * @ClassName: Application
 * @Description:  TODO
 *
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-下午8:52:43
 */
public class Application {

	public static void main(String[] args) {
		
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
		
		argsMap.forEach((k,v) -> System.out.println(k + "  ->  " + v));
		
		String data = argsMap.get("--data");
		String exit = argsMap.get("--exit");
		
		YRailroadContext yRailroadContext = new SimpleYRailroadContext();
		yRailroadContext.start(System.in, System.out, System.err, data, exit);
	}

}
