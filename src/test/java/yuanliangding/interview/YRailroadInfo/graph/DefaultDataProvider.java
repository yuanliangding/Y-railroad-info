package yuanliangding.interview.YRailroadInfo.graph;

import java.io.IOException;

import org.junit.Before;

import yuanliangding.interview.YRailroadInfo.graph.GraphDatum;

/** 
 * @ClassName: DefaultDataProvider
 * @Description:  为路径搜索相关类(Path的各种子类)的测试,提供通用的逻辑模块.
 *
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午11:48:16
 */
public class DefaultDataProvider {
	
	public static final String DIST = "dist";
	
	public static final String STOP = "stop";
	
	protected GraphDatum graphDatum = new GraphDatum();

	@Before
	public void before() throws IOException {
		graphDatum.addEdge(graphDatum.getVertex("A"), graphDatum.getVertex("B"), DIST, 5);
		graphDatum.addEdge(graphDatum.getVertex("B"), graphDatum.getVertex("C"), DIST, 4);
		graphDatum.addEdge(graphDatum.getVertex("C"), graphDatum.getVertex("D"), DIST, 8);
		graphDatum.addEdge(graphDatum.getVertex("D"), graphDatum.getVertex("C"), DIST, 8);
		graphDatum.addEdge(graphDatum.getVertex("D"), graphDatum.getVertex("E"), DIST, 6);
		graphDatum.addEdge(graphDatum.getVertex("A"), graphDatum.getVertex("D"), DIST, 5);
		graphDatum.addEdge(graphDatum.getVertex("C"), graphDatum.getVertex("E"), DIST, 2);
		graphDatum.addEdge(graphDatum.getVertex("E"), graphDatum.getVertex("B"), DIST, 3);
		graphDatum.addEdge(graphDatum.getVertex("A"), graphDatum.getVertex("E"), DIST, 7);
		
		graphDatum.addEdge(graphDatum.getVertex("A"), graphDatum.getVertex("B"), STOP, 1);
		graphDatum.addEdge(graphDatum.getVertex("B"), graphDatum.getVertex("C"), STOP, 1);
		graphDatum.addEdge(graphDatum.getVertex("C"), graphDatum.getVertex("D"), STOP, 1);
		graphDatum.addEdge(graphDatum.getVertex("D"), graphDatum.getVertex("C"), STOP, 1);
		graphDatum.addEdge(graphDatum.getVertex("D"), graphDatum.getVertex("E"), STOP, 1);
		graphDatum.addEdge(graphDatum.getVertex("A"), graphDatum.getVertex("D"), STOP, 1);
		graphDatum.addEdge(graphDatum.getVertex("C"), graphDatum.getVertex("E"), STOP, 1);
		graphDatum.addEdge(graphDatum.getVertex("E"), graphDatum.getVertex("B"), STOP, 1);
		graphDatum.addEdge(graphDatum.getVertex("A"), graphDatum.getVertex("E"), STOP, 1);
	}

}
