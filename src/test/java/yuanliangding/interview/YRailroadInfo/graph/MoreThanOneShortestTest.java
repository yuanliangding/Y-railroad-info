package yuanliangding.interview.YRailroadInfo.graph;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import yuanliangding.interview.YRailroadInfo.graph.IndividualPath;
import yuanliangding.interview.YRailroadInfo.graph.MinPath;
import yuanliangding.interview.YRailroadInfo.graph.base.Graph;
import yuanliangding.interview.YRailroadInfo.graph.base.Vertex;

/** 
 * @ClassName: MoreThanOneShortestTest
 * @Description:  最短路径有两条以上的测试,是否都能找到 (这里数据和原题有所区别)
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-下午5:05:41
 */
public class MoreThanOneShortestTest {
	
	protected Graph graph = new Graph();

	@Before
	public void before() throws IOException {
		graph.clear();

		graph.addEdge(graph.getVertex("A"), graph.getVertex("B"), DefaultDataProvider.DIST, 5);
		graph.addEdge(graph.getVertex("B"), graph.getVertex("C"), DefaultDataProvider.DIST, 4);
		graph.addEdge(graph.getVertex("C"), graph.getVertex("D"), DefaultDataProvider.DIST, 8);
		graph.addEdge(graph.getVertex("D"), graph.getVertex("C"), DefaultDataProvider.DIST, 8);
		graph.addEdge(graph.getVertex("D"), graph.getVertex("E"), DefaultDataProvider.DIST, 6);
		graph.addEdge(graph.getVertex("A"), graph.getVertex("D"), DefaultDataProvider.DIST, 5);
		graph.addEdge(graph.getVertex("C"), graph.getVertex("E"), DefaultDataProvider.DIST, 2);
		graph.addEdge(graph.getVertex("E"), graph.getVertex("B"), DefaultDataProvider.DIST, 3);
	}
	
	@Test
	public void testConcrete() {
		
		Vertex a = graph.getVertex("A");
		Vertex e = graph.getVertex("E");
		
		MinPath ae_dist = new MinPath(a, e, DefaultDataProvider.DIST);
		List<IndividualPath> ae_dist_paths = ae_dist.concrete();
		Assert.assertThat("从A到E,最短路径路线有2条", ae_dist_paths.size(), CoreMatchers.equalTo(2));
		Assert.assertThat(
				"从A到E,最短路径路线有2条:A-B-C-E, A-D-E", 
				ae_dist_paths.stream().map(IndividualPath::toString).collect(Collectors.toList()), 
				CoreMatchers.hasItems("A-B-C-E", "A-D-E")
				);
		Assert.assertThat(
				"从A到E,最短路径距离为11", 
				ae_dist_paths.get(0).getTotalWeight(DefaultDataProvider.DIST), 
				CoreMatchers.equalTo(11));
		Assert.assertThat(
				"从A到E,最短路径距离为11", 
				ae_dist_paths.get(1).getTotalWeight(DefaultDataProvider.DIST), 
				CoreMatchers.equalTo(11));
		}

}
