package yuan.interview.railroad.test.util;

import java.io.IOException;

import org.junit.Before;

import yuan.interview.railroad.graph.base.Graph;

/** 
 * @ClassName: DefaultDataPrepared
 * @Description:  为路径搜索相关类(Path的各种子类)的测试，已经提供通用的测试数据
 *
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午11:48:16
 */
public class DefaultDataPrepared {
	
	public static final String DIST = "dist";
	
	public static final String STOP = "stop";
	
	protected Graph graph = new Graph();

	@Before
	public void before() throws IOException {
		graph.setWeight(graph.getVertex("A"), graph.getVertex("B"), DIST, 5);
		graph.setWeight(graph.getVertex("B"), graph.getVertex("C"), DIST, 4);
		graph.setWeight(graph.getVertex("C"), graph.getVertex("D"), DIST, 8);
		graph.setWeight(graph.getVertex("D"), graph.getVertex("C"), DIST, 8);
		graph.setWeight(graph.getVertex("D"), graph.getVertex("E"), DIST, 6);
		graph.setWeight(graph.getVertex("A"), graph.getVertex("D"), DIST, 5);
		graph.setWeight(graph.getVertex("C"), graph.getVertex("E"), DIST, 2);
		graph.setWeight(graph.getVertex("E"), graph.getVertex("B"), DIST, 3);
		graph.setWeight(graph.getVertex("A"), graph.getVertex("E"), DIST, 7);
		
		graph.setWeight(graph.getVertex("A"), graph.getVertex("B"), STOP, 1);
		graph.setWeight(graph.getVertex("B"), graph.getVertex("C"), STOP, 1);
		graph.setWeight(graph.getVertex("C"), graph.getVertex("D"), STOP, 1);
		graph.setWeight(graph.getVertex("D"), graph.getVertex("C"), STOP, 1);
		graph.setWeight(graph.getVertex("D"), graph.getVertex("E"), STOP, 1);
		graph.setWeight(graph.getVertex("A"), graph.getVertex("D"), STOP, 1);
		graph.setWeight(graph.getVertex("C"), graph.getVertex("E"), STOP, 1);
		graph.setWeight(graph.getVertex("E"), graph.getVertex("B"), STOP, 1);
		graph.setWeight(graph.getVertex("A"), graph.getVertex("E"), STOP, 1);
	}

}
