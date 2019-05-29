package yuanliangding.interview.YRailroadInfo.visit;

import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import yuanliangding.interview.YRailroadInfo.graph.MapDatum.Stop;

/** 
 * @ClassName: LeastPathTest
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-下午4:13:29
 */
public class LeastPathTest extends PathTest {
	
	@Test
	public void testConcrete() {
		
		Stop a = mapDatum.getStop("A");
		Stop b = mapDatum.getStop("B");
		Stop c = mapDatum.getStop("C");
		
		LeastPath ac_dist = new LeastPath(a, c, DIST);
		List<IndividualPath> ac_dist_paths = ac_dist.concrete();
		Assert.assertThat("从A到C,最短路径路线有1条", ac_dist_paths.size(), CoreMatchers.equalTo(1));
		Assert.assertThat(
				"从A到C,最短路径路线有1条:A-B-C", 
				ac_dist_paths.stream().map(IndividualPath::toString).collect(Collectors.toList()), 
				CoreMatchers.hasItems("A-B-C")
				);
		Assert.assertThat(
				"从A到C,最短路径距离为9", 
				ac_dist_paths.get(0).getTotalWeight(DIST), 
				CoreMatchers.equalTo(9));
		
		LeastPath bb_dist = new LeastPath(b, b, DIST);
		List<IndividualPath> bb_dist_paths = bb_dist.concrete();
		Assert.assertThat("从B到B,最短路径路线有1条", bb_dist_paths.size(), CoreMatchers.equalTo(1));
		Assert.assertThat(
				"从B到B,最短路径路线有1条:B-C-E-B", 
				bb_dist_paths.stream().map(IndividualPath::toString).collect(Collectors.toList()), 
				CoreMatchers.hasItems("B-C-E-B")
				);
		Assert.assertThat(
				"从A到C,最短路径距离为 9", 
				bb_dist_paths.get(0).getTotalWeight(DIST), 
				CoreMatchers.equalTo(9));
		}

}
