package yuan.interview.railroad.graph.algorithm;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import yuan.interview.railroad.graph.algorithm.IndividualPath;
import yuan.interview.railroad.graph.algorithm.MinPath;
import yuan.interview.railroad.graph.base.Graph;
import yuan.interview.railroad.graph.base.Vertex;
import yuan.interview.railroad.test.util.DefaultDataPrepared;

/** 
 * @ClassName: MoreThanOneShortestTest
 * @Description:  最短路径有两条以上的测试，是否都能找到 (这里数据和原题有所区别)
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-下午5:05:41
 */
public class MoreThanOneShortestTest {
	
	protected Graph graph = new Graph();

	@Before
	public void before() throws IOException {
		graph.clear();

		graph.setWeight(graph.getVertex("A"), graph.getVertex("B"), DefaultDataPrepared.DIST, 5);
		graph.setWeight(graph.getVertex("B"), graph.getVertex("C"), DefaultDataPrepared.DIST, 4);
		graph.setWeight(graph.getVertex("C"), graph.getVertex("D"), DefaultDataPrepared.DIST, 8);
		graph.setWeight(graph.getVertex("D"), graph.getVertex("C"), DefaultDataPrepared.DIST, 8);
		graph.setWeight(graph.getVertex("D"), graph.getVertex("E"), DefaultDataPrepared.DIST, 6);
		graph.setWeight(graph.getVertex("A"), graph.getVertex("D"), DefaultDataPrepared.DIST, 5);
		graph.setWeight(graph.getVertex("C"), graph.getVertex("E"), DefaultDataPrepared.DIST, 2);
		graph.setWeight(graph.getVertex("E"), graph.getVertex("B"), DefaultDataPrepared.DIST, 3);
	}
	
	@Test
	public void testSearch() {
		
		Vertex a = graph.getVertex("A");
		Vertex e = graph.getVertex("E");
		
		MinPath ae_dist = new MinPath(a, e, DefaultDataPrepared.DIST);
		List<IndividualPath> ae_dist_paths = ae_dist.search();
		Assert.assertThat("从A到E，最短路径路线有2条", ae_dist_paths.size(), CoreMatchers.equalTo(2));
		Assert.assertThat(
				"从A到E，最短路径路线有2条：A-B-C-E, A-D-E", 
				ae_dist_paths.stream().map(IndividualPath::toString).collect(Collectors.toList()), 
				CoreMatchers.hasItems("A-B-C-E", "A-D-E")
				);
		Assert.assertThat(
				"从A到E，最短路径距离为11", 
				ae_dist_paths.get(0).getTotalWeight(DefaultDataPrepared.DIST), 
				CoreMatchers.equalTo(11));
		Assert.assertThat(
				"从A到E，最短路径距离为11", 
				ae_dist_paths.get(1).getTotalWeight(DefaultDataPrepared.DIST), 
				CoreMatchers.equalTo(11));
		}

}
