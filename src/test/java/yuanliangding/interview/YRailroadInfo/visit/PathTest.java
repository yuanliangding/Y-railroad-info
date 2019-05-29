package yuanliangding.interview.YRailroadInfo.visit;

import java.io.IOException;

import org.junit.Before;

import yuanliangding.interview.YRailroadInfo.graph.GraphDatum;

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
	
	protected GraphDatum graphDatum = GraphDatum.getInstance();

	@Before
	public void before() throws IOException {
		graphDatum.clear();

		graphDatum.addRoute(graphDatum.getStop("A"), graphDatum.getStop("B"), DIST, 5);
		graphDatum.addRoute(graphDatum.getStop("B"), graphDatum.getStop("C"), DIST, 4);
		graphDatum.addRoute(graphDatum.getStop("C"), graphDatum.getStop("D"), DIST, 8);
		graphDatum.addRoute(graphDatum.getStop("D"), graphDatum.getStop("C"), DIST, 8);
		graphDatum.addRoute(graphDatum.getStop("D"), graphDatum.getStop("E"), DIST, 6);
		graphDatum.addRoute(graphDatum.getStop("A"), graphDatum.getStop("D"), DIST, 5);
		graphDatum.addRoute(graphDatum.getStop("C"), graphDatum.getStop("E"), DIST, 2);
		graphDatum.addRoute(graphDatum.getStop("E"), graphDatum.getStop("B"), DIST, 3);
		graphDatum.addRoute(graphDatum.getStop("A"), graphDatum.getStop("E"), DIST, 7);
		
		graphDatum.addRoute(graphDatum.getStop("A"), graphDatum.getStop("B"), STOP, 1);
		graphDatum.addRoute(graphDatum.getStop("B"), graphDatum.getStop("C"), STOP, 1);
		graphDatum.addRoute(graphDatum.getStop("C"), graphDatum.getStop("D"), STOP, 1);
		graphDatum.addRoute(graphDatum.getStop("D"), graphDatum.getStop("C"), STOP, 1);
		graphDatum.addRoute(graphDatum.getStop("D"), graphDatum.getStop("E"), STOP, 1);
		graphDatum.addRoute(graphDatum.getStop("A"), graphDatum.getStop("D"), STOP, 1);
		graphDatum.addRoute(graphDatum.getStop("C"), graphDatum.getStop("E"), STOP, 1);
		graphDatum.addRoute(graphDatum.getStop("E"), graphDatum.getStop("B"), STOP, 1);
		graphDatum.addRoute(graphDatum.getStop("A"), graphDatum.getStop("E"), STOP, 1);
	}

}
