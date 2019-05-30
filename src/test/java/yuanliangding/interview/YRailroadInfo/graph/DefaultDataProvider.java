package yuanliangding.interview.YRailroadInfo.graph;

import java.io.IOException;

import org.junit.Before;

import yuanliangding.interview.YRailroadInfo.graph.base.Graph;

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
	
	protected Graph graph = new Graph();

	@Before
	public void before() throws IOException {
		graph.addEdge(graph.getVertex("A"), graph.getVertex("B"), DIST, 5);
		graph.addEdge(graph.getVertex("B"), graph.getVertex("C"), DIST, 4);
		graph.addEdge(graph.getVertex("C"), graph.getVertex("D"), DIST, 8);
		graph.addEdge(graph.getVertex("D"), graph.getVertex("C"), DIST, 8);
		graph.addEdge(graph.getVertex("D"), graph.getVertex("E"), DIST, 6);
		graph.addEdge(graph.getVertex("A"), graph.getVertex("D"), DIST, 5);
		graph.addEdge(graph.getVertex("C"), graph.getVertex("E"), DIST, 2);
		graph.addEdge(graph.getVertex("E"), graph.getVertex("B"), DIST, 3);
		graph.addEdge(graph.getVertex("A"), graph.getVertex("E"), DIST, 7);
		
		graph.addEdge(graph.getVertex("A"), graph.getVertex("B"), STOP, 1);
		graph.addEdge(graph.getVertex("B"), graph.getVertex("C"), STOP, 1);
		graph.addEdge(graph.getVertex("C"), graph.getVertex("D"), STOP, 1);
		graph.addEdge(graph.getVertex("D"), graph.getVertex("C"), STOP, 1);
		graph.addEdge(graph.getVertex("D"), graph.getVertex("E"), STOP, 1);
		graph.addEdge(graph.getVertex("A"), graph.getVertex("D"), STOP, 1);
		graph.addEdge(graph.getVertex("C"), graph.getVertex("E"), STOP, 1);
		graph.addEdge(graph.getVertex("E"), graph.getVertex("B"), STOP, 1);
		graph.addEdge(graph.getVertex("A"), graph.getVertex("E"), STOP, 1);
	}

}
