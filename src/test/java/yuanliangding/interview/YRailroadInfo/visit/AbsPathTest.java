package yuanliangding.interview.YRailroadInfo.visit;

import java.io.IOException;

import org.junit.Before;

import yuanliangding.interview.YRailroadInfo.map.StopMap;
import yuanliangding.interview.YRailroadInfo.reader.PlainTextMapReader;

/** 
 * @ClassName: PathTest
 * @Description:  为路径搜索相关类(Path的各种子类)的测试,提供通用的逻辑模块.
 *
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午11:48:16
 */
public class PathTest {
	
	protected StopMap stopMap = null;

	@Before
	public void before() throws IOException {
		stopMap = StopMap.getInstance();

		stopMap.addRoute(stopMap.getStop("A"), stopMap.getStop("B"), PlainTextMapReader.DIST, 5);
		stopMap.addRoute(stopMap.getStop("B"), stopMap.getStop("C"), PlainTextMapReader.DIST, 4);
		stopMap.addRoute(stopMap.getStop("C"), stopMap.getStop("D"), PlainTextMapReader.DIST, 8);
		stopMap.addRoute(stopMap.getStop("D"), stopMap.getStop("C"), PlainTextMapReader.DIST, 8);
		stopMap.addRoute(stopMap.getStop("D"), stopMap.getStop("E"), PlainTextMapReader.DIST, 6);
		stopMap.addRoute(stopMap.getStop("A"), stopMap.getStop("D"), PlainTextMapReader.DIST, 5);
		stopMap.addRoute(stopMap.getStop("C"), stopMap.getStop("E"), PlainTextMapReader.DIST, 2);
		stopMap.addRoute(stopMap.getStop("E"), stopMap.getStop("B"), PlainTextMapReader.DIST, 3);
		stopMap.addRoute(stopMap.getStop("A"), stopMap.getStop("E"), PlainTextMapReader.DIST, 7);
		
		stopMap.addRoute(stopMap.getStop("A"), stopMap.getStop("B"), PlainTextMapReader.STOP, 1);
		stopMap.addRoute(stopMap.getStop("B"), stopMap.getStop("C"), PlainTextMapReader.STOP, 1);
		stopMap.addRoute(stopMap.getStop("C"), stopMap.getStop("D"), PlainTextMapReader.STOP, 1);
		stopMap.addRoute(stopMap.getStop("D"), stopMap.getStop("C"), PlainTextMapReader.STOP, 1);
		stopMap.addRoute(stopMap.getStop("D"), stopMap.getStop("E"), PlainTextMapReader.STOP, 1);
		stopMap.addRoute(stopMap.getStop("A"), stopMap.getStop("D"), PlainTextMapReader.STOP, 1);
		stopMap.addRoute(stopMap.getStop("C"), stopMap.getStop("E"), PlainTextMapReader.STOP, 1);
		stopMap.addRoute(stopMap.getStop("E"), stopMap.getStop("B"), PlainTextMapReader.STOP, 1);
		stopMap.addRoute(stopMap.getStop("A"), stopMap.getStop("E"), PlainTextMapReader.STOP, 1);
	}

}
