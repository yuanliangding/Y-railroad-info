package yuanliangding.interview.YRailroadInfo.visit;

import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import yuanliangding.interview.YRailroadInfo.map.MapDatum.Stop;

/** 
 * @ClassName: LimitedPathTest
 *
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午11:52:24
 */
public class LimitedPathTest extends PathTest {
	
	@Test
	public void testConcrete() {
		
		Stop a = mapDatum.getStop("A");
		Stop c = mapDatum.getStop("C");
		
		LimitedPath cc_stop_0_3 = new LimitedPath(c, c, STOP, 0, 3);
		List<IndividualPath> cc_stop_0_3_paths = cc_stop_0_3.concrete();
		Assert.assertThat("从C到C,路程最多跨越3站的路线有2条", cc_stop_0_3_paths.size(), CoreMatchers.equalTo(2));
		Assert.assertThat(
				"从C到C,路程最多跨越3站的路线有:C-D-C,C-E-B-C", 
				cc_stop_0_3_paths.stream().map(IndividualPath::toString).collect(Collectors.toList()), 
				CoreMatchers.hasItems("C-D-C","C-E-B-C")
				);
		
		LimitedPath ac_stop_4_4 = new LimitedPath(a, c, STOP, 4, 4);
		List<IndividualPath> ac_stop_4_4_paths = ac_stop_4_4.concrete();
		Assert.assertThat("从A到C,路程正好跨越4站的路线有3条", ac_stop_4_4_paths.size(), CoreMatchers.equalTo(3));
		Assert.assertThat(
				"从A到C,路程正好跨越4站的路线有3条:A-B-C-D-C,A-D-C-D-C,A-D-E-B-C", 
				ac_stop_4_4_paths.stream().map(IndividualPath::toString).collect(Collectors.toList()), 
				CoreMatchers.hasItems("A-B-C-D-C","A-D-C-D-C", "A-D-E-B-C")
				);
		
		LimitedPath cc_dist_0_30 = new LimitedPath(c, c, DIST, 0, 30, true, false);
		List<IndividualPath> cc_dist_0_30_paths = cc_dist_0_30.concrete();
		Assert.assertThat("从C到C,路程小于30的路线有7条", cc_dist_0_30_paths.size(), CoreMatchers.equalTo(7));
		Assert.assertThat(
				"从C到C,路程小于30的路线有7条:C-D-C,C-D-C-E-B-C,C-D-E-B-C,C-E-B-C,C-E-B-C-D-C,C-E-B-C-E-B-C,C-E-B-C-E-B-C-E-B-C", 
				cc_dist_0_30_paths.stream().map(IndividualPath::toString).collect(Collectors.toList()), 
				CoreMatchers.hasItems(
						"C-D-C",
						"C-D-C-E-B-C",
						"C-D-E-B-C",
						"C-E-B-C",
						"C-E-B-C-D-C",
						"C-E-B-C-E-B-C",
						"C-E-B-C-E-B-C-E-B-C")
				);
	}
	
}
