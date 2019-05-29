package yuanliangding.interview.YRailroadInfo.visit;

import java.io.IOException;

import org.junit.Before;

import yuanliangding.interview.YRailroadInfo.map.StopMap;

/** 
 * @ClassName: PathTest
 * @Description:  为路径搜索相关类(Path的各种子类)的测试,提供通用的逻辑模块.
 *
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午11:48:16
 */
public class PathTest {
	
	public static final String DIST = "dist";
	
	public static final String STOP = "stop";
	
	protected StopMap stopMap = StopMap.getInstance();

	@Before
	public void before() throws IOException {
		stopMap.clear();

		stopMap.addRoute(stopMap.getStop("A"), stopMap.getStop("B"), DIST, 5);
		stopMap.addRoute(stopMap.getStop("B"), stopMap.getStop("C"), DIST, 4);
		stopMap.addRoute(stopMap.getStop("C"), stopMap.getStop("D"), DIST, 8);
		stopMap.addRoute(stopMap.getStop("D"), stopMap.getStop("C"), DIST, 8);
		stopMap.addRoute(stopMap.getStop("D"), stopMap.getStop("E"), DIST, 6);
		stopMap.addRoute(stopMap.getStop("A"), stopMap.getStop("D"), DIST, 5);
		stopMap.addRoute(stopMap.getStop("C"), stopMap.getStop("E"), DIST, 2);
		stopMap.addRoute(stopMap.getStop("E"), stopMap.getStop("B"), DIST, 3);
		stopMap.addRoute(stopMap.getStop("A"), stopMap.getStop("E"), DIST, 7);
		
		stopMap.addRoute(stopMap.getStop("A"), stopMap.getStop("B"), STOP, 1);
		stopMap.addRoute(stopMap.getStop("B"), stopMap.getStop("C"), STOP, 1);
		stopMap.addRoute(stopMap.getStop("C"), stopMap.getStop("D"), STOP, 1);
		stopMap.addRoute(stopMap.getStop("D"), stopMap.getStop("C"), STOP, 1);
		stopMap.addRoute(stopMap.getStop("D"), stopMap.getStop("E"), STOP, 1);
		stopMap.addRoute(stopMap.getStop("A"), stopMap.getStop("D"), STOP, 1);
		stopMap.addRoute(stopMap.getStop("C"), stopMap.getStop("E"), STOP, 1);
		stopMap.addRoute(stopMap.getStop("E"), stopMap.getStop("B"), STOP, 1);
		stopMap.addRoute(stopMap.getStop("A"), stopMap.getStop("E"), STOP, 1);
	}

}
