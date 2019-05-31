package yuan.interview.railroad.graph.algorithm;

import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import yuan.interview.railroad.graph.algorithm.IndividualPath;
import yuan.interview.railroad.graph.algorithm.MinPath;
import yuan.interview.railroad.graph.base.Vertex;
import yuan.interview.railroad.test.util.DefaultDataPrepared;

/** 
 * @ClassName: MinPathTest
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-下午4:13:29
 */
public class MinPathTest extends DefaultDataPrepared {
	
	@Test
	public void testConcrete() {
		
		Vertex a = graph.getVertex("A");
		Vertex b = graph.getVertex("B");
		Vertex c = graph.getVertex("C");
		
		MinPath ac_dist = new MinPath(a, c, DIST);
		List<IndividualPath> ac_dist_paths = ac_dist.concrete();
		Assert.assertThat("从A到C,最短路径的路线有1条", ac_dist_paths.size(), CoreMatchers.equalTo(1));
		Assert.assertThat(
				"从A到C,最短路径的路线有1条:A-B-C", 
				ac_dist_paths.stream().map(IndividualPath::toString).collect(Collectors.toList()), 
				CoreMatchers.hasItems("A-B-C")
				);
		Assert.assertThat(
				"从A到C,最短路径路程为9", 
				ac_dist_paths.get(0).getTotalWeight(DIST), 
				CoreMatchers.equalTo(9));
		
		MinPath bb_dist = new MinPath(b, b, DIST);
		List<IndividualPath> bb_dist_paths = bb_dist.concrete();
		Assert.assertThat("从B到B,最短路径的路线有1条", bb_dist_paths.size(), CoreMatchers.equalTo(1));
		Assert.assertThat(
				"从B到B,最短路径的路线有1条:B-C-E-B", 
				bb_dist_paths.stream().map(IndividualPath::toString).collect(Collectors.toList()), 
				CoreMatchers.hasItems("B-C-E-B")
				);
		Assert.assertThat(
				"从A到C,最短路径路程为 9", 
				bb_dist_paths.get(0).getTotalWeight(DIST), 
				CoreMatchers.equalTo(9));
		}

}
