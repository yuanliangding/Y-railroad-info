package yuanliangding.interview.YRailroadInfo.visit;

import java.io.IOException;

import org.junit.Before;

import yuanliangding.interview.YRailroadInfo.graph.MapDatum;

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
	
	protected MapDatum mapDatum = MapDatum.getInstance();

	@Before
	public void before() throws IOException {
		mapDatum.clear();

		mapDatum.addRoute(mapDatum.getStop("A"), mapDatum.getStop("B"), DIST, 5);
		mapDatum.addRoute(mapDatum.getStop("B"), mapDatum.getStop("C"), DIST, 4);
		mapDatum.addRoute(mapDatum.getStop("C"), mapDatum.getStop("D"), DIST, 8);
		mapDatum.addRoute(mapDatum.getStop("D"), mapDatum.getStop("C"), DIST, 8);
		mapDatum.addRoute(mapDatum.getStop("D"), mapDatum.getStop("E"), DIST, 6);
		mapDatum.addRoute(mapDatum.getStop("A"), mapDatum.getStop("D"), DIST, 5);
		mapDatum.addRoute(mapDatum.getStop("C"), mapDatum.getStop("E"), DIST, 2);
		mapDatum.addRoute(mapDatum.getStop("E"), mapDatum.getStop("B"), DIST, 3);
		mapDatum.addRoute(mapDatum.getStop("A"), mapDatum.getStop("E"), DIST, 7);
		
		mapDatum.addRoute(mapDatum.getStop("A"), mapDatum.getStop("B"), STOP, 1);
		mapDatum.addRoute(mapDatum.getStop("B"), mapDatum.getStop("C"), STOP, 1);
		mapDatum.addRoute(mapDatum.getStop("C"), mapDatum.getStop("D"), STOP, 1);
		mapDatum.addRoute(mapDatum.getStop("D"), mapDatum.getStop("C"), STOP, 1);
		mapDatum.addRoute(mapDatum.getStop("D"), mapDatum.getStop("E"), STOP, 1);
		mapDatum.addRoute(mapDatum.getStop("A"), mapDatum.getStop("D"), STOP, 1);
		mapDatum.addRoute(mapDatum.getStop("C"), mapDatum.getStop("E"), STOP, 1);
		mapDatum.addRoute(mapDatum.getStop("E"), mapDatum.getStop("B"), STOP, 1);
		mapDatum.addRoute(mapDatum.getStop("A"), mapDatum.getStop("E"), STOP, 1);
	}

}
