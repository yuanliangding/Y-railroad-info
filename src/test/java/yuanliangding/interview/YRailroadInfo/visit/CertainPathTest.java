package yuanliangding.interview.YRailroadInfo.visit;

import java.io.IOException;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import yuanliangding.interview.YRailroadInfo.map.Stop;
import yuanliangding.interview.YRailroadInfo.map.StopMap;
import yuanliangding.interview.YRailroadInfo.reader.PlainTextMapReader;

/**
 * @ClassName: CertainPathTest
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午12:26:56
 */
public class CertainPathTest {

	private StopMap stopMap = null;

	@Rule
	public final ExpectedException noSuchRouteException = ExpectedException.none();

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
		stopMap.addRoute(stopMap.getStop("A"), stopMap.getStop("E"), PlainTextMapReader.DIST, 7);
	}

	@Test
	public void testGetTotalWeight() {
		Stop a = stopMap.getStop("A");
		Stop b = stopMap.getStop("B");
		Stop c = stopMap.getStop("C");
		Stop d = stopMap.getStop("D");
		Stop e = stopMap.getStop("E");

		CertainPath abc = new CertainPath(a, b, c);
		Assert.assertThat("验证" + abc + "路径长度出错", abc.getTotalWeight(PlainTextMapReader.DIST), CoreMatchers.equalTo(9));

		CertainPath ad = new CertainPath(a, d);
		Assert.assertThat("验证" + ad + "路径长度出错", ad.getTotalWeight(PlainTextMapReader.DIST), CoreMatchers.equalTo(5));

		CertainPath adc = new CertainPath(a, d, c);
		Assert.assertThat("验证" + adc + "路径长度出错", adc.getTotalWeight(PlainTextMapReader.DIST), CoreMatchers.equalTo(13));

		CertainPath aebcd = new CertainPath(a, e, b, c, d);
		Assert.assertThat("验证" + aebcd + "路径长度出错", aebcd.getTotalWeight(PlainTextMapReader.DIST), CoreMatchers.equalTo(22));
	}
	
	@Test
	public void testNoSuchRoute() {
		Stop a = stopMap.getStop("A");
		Stop d = stopMap.getStop("D");
		Stop e = stopMap.getStop("E");
		
		noSuchRouteException.expect(RuntimeException.class);
		noSuchRouteException.expectMessage("NO SUCH ROUTE");
		CertainPath aed = new CertainPath(a, e, d);
		aed.getTotalWeight(PlainTextMapReader.DIST);
	}

}
