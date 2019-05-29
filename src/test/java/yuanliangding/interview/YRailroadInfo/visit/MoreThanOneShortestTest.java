package yuanliangding.interview.YRailroadInfo.visit;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import yuanliangding.interview.YRailroadInfo.graph.GraphDatum;
import yuanliangding.interview.YRailroadInfo.graph.GraphDatum.Stop;

/** 
 * @ClassName: MoreThanOneShortestTest
 * @Description:  最短路径有两条以上的测试,是否都能找到 (这里数据和原题有所区别)
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-下午5:05:41
 */
public class MoreThanOneShortestTest {
	
	protected GraphDatum graphDatum = GraphDatum.getInstance();

	@Before
	public void before() throws IOException {
		graphDatum.clear();

		graphDatum.addRoute(graphDatum.getStop("A"), graphDatum.getStop("B"), PathTest.DIST, 5);
		graphDatum.addRoute(graphDatum.getStop("B"), graphDatum.getStop("C"), PathTest.DIST, 4);
		graphDatum.addRoute(graphDatum.getStop("C"), graphDatum.getStop("D"), PathTest.DIST, 8);
		graphDatum.addRoute(graphDatum.getStop("D"), graphDatum.getStop("C"), PathTest.DIST, 8);
		graphDatum.addRoute(graphDatum.getStop("D"), graphDatum.getStop("E"), PathTest.DIST, 6);
		graphDatum.addRoute(graphDatum.getStop("A"), graphDatum.getStop("D"), PathTest.DIST, 5);
		graphDatum.addRoute(graphDatum.getStop("C"), graphDatum.getStop("E"), PathTest.DIST, 2);
		graphDatum.addRoute(graphDatum.getStop("E"), graphDatum.getStop("B"), PathTest.DIST, 3);
	}
	
	@Test
	public void testConcrete() {
		
		Stop a = graphDatum.getStop("A");
		Stop e = graphDatum.getStop("E");
		
		LeastPath ae_dist = new LeastPath(a, e, PathTest.DIST);
		List<IndividualPath> ae_dist_paths = ae_dist.concrete();
		Assert.assertThat("从A到E,最短路径路线有2条", ae_dist_paths.size(), CoreMatchers.equalTo(2));
		Assert.assertThat(
				"从A到E,最短路径路线有2条:A-B-C-E, A-D-E", 
				ae_dist_paths.stream().map(IndividualPath::toString).collect(Collectors.toList()), 
				CoreMatchers.hasItems("A-B-C-E", "A-D-E")
				);
		Assert.assertThat(
				"从A到E,最短路径距离为11", 
				ae_dist_paths.get(0).getTotalWeight(PathTest.DIST), 
				CoreMatchers.equalTo(11));
		Assert.assertThat(
				"从A到E,最短路径距离为11", 
				ae_dist_paths.get(1).getTotalWeight(PathTest.DIST), 
				CoreMatchers.equalTo(11));
		}

}
