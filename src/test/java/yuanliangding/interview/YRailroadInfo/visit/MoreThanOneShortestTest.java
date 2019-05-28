package yuanliangding.interview.YRailroadInfo.visit;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import yuanliangding.interview.YRailroadInfo.map.Stop;
import yuanliangding.interview.YRailroadInfo.map.StopMap;
import yuanliangding.interview.YRailroadInfo.reader.PlainTextMapReader;

/** 
 * @ClassName: MoreThanOneShortestTest
 * @Description:  最短路径有两条以上的测试,是否都能找到 (这里数据和原题有所区别)
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-下午5:05:41
 */
public class MoreThanOneShortestTest {
	
	protected StopMap stopMap = null;

	@Before
	public void before() throws IOException {
		stopMap = StopMap.getInstance();

		stopMap.addRoute(stopMap.getStop("A"), stopMap.getStop("B"), PlainTextMapReader.DIST, 5);
		stopMap.addRoute(stopMap.getStop("B"), stopMap.getStop("C"), PlainTextMapReader.DIST, 4);
		stopMap.addRoute(stopMap.getStop("C"), stopMap.getStop("D"), PlainTextMapReader.DIST, 8);
		stopMap.addRoute(stopMap.getStop("D"), stopMap.getStop("C"), PlainTextMapReader.DIST, 8);
		stopMap.addRoute(stopMap.getStop("D"), stopMap.getStop("E"), PlainTextMapReader.DIST, 6);
		stopMap.addRoute(stopMap.getStop("A"), stopMap.getStop("D"), PlainTextMapReader.DIST, 5);
		stopMap.addRoute(stopMap.getStop("C"), stopMap.getStop("E"), PlainTextMapReader.DIST, 2);
		stopMap.addRoute(stopMap.getStop("E"), stopMap.getStop("B"), PlainTextMapReader.DIST, 3);
	}
	
	@Test
	public void testConcrete() {
		
		Stop a = stopMap.getStop("A");
		Stop e = stopMap.getStop("E");
		
		LeastPath ae_dist = new LeastPath(a, e, PlainTextMapReader.DIST);
		List<IndividualPath> ae_dist_paths = ae_dist.concrete();
		Assert.assertThat("从A到E,最短路径路线有2条", ae_dist_paths.size(), CoreMatchers.equalTo(2));
		Assert.assertThat(
				"从A到E,最短路径路线有2条:A-B-C-E, A-D-E", 
				ae_dist_paths.stream().map(IndividualPath::toString).collect(Collectors.toList()), 
				CoreMatchers.hasItems("A-B-C-E", "A-D-E")
				);
		Assert.assertThat(
				"从A到E,最短路径距离为11", 
				ae_dist_paths.get(0).getTotalWeight(PlainTextMapReader.DIST), 
				CoreMatchers.equalTo(11));
		Assert.assertThat(
				"从A到E,最短路径距离为11", 
				ae_dist_paths.get(1).getTotalWeight(PlainTextMapReader.DIST), 
				CoreMatchers.equalTo(11));
		}

}
