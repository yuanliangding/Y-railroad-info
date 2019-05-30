package yuanliangding.interview.YRailroadInfo.graph;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import yuanliangding.interview.YRailroadInfo.graph.IndividualPath;
import yuanliangding.interview.YRailroadInfo.graph.base.Graph.Vertex;

/**
 * @ClassName: IndividualPathTest
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午12:26:56
 */
public class IndividualPathTest extends DefaultDataProvider{

	@Rule
	public final ExpectedException noSuchRouteException = ExpectedException.none();

	@Test
	public void testGetTotalWeight() {
		Vertex a = graph.getVertex("A");
		Vertex b = graph.getVertex("B");
		Vertex c = graph.getVertex("C");
		Vertex d = graph.getVertex("D");
		Vertex e = graph.getVertex("E");

		IndividualPath abc = new IndividualPath(a, b, c);
		Assert.assertThat("验证" + abc + "路径长度出错", abc.getTotalWeight(DIST), CoreMatchers.equalTo(9));

		IndividualPath ad = new IndividualPath(a, d);
		Assert.assertThat("验证" + ad + "路径长度出错", ad.getTotalWeight(DIST), CoreMatchers.equalTo(5));

		IndividualPath adc = new IndividualPath(a, d, c);
		Assert.assertThat("验证" + adc + "路径长度出错", adc.getTotalWeight(DIST), CoreMatchers.equalTo(13));

		IndividualPath aebcd = new IndividualPath(a, e, b, c, d);
		Assert.assertThat("验证" + aebcd + "路径长度出错", aebcd.getTotalWeight(DIST), CoreMatchers.equalTo(22));
	}
	
	@Test
	public void testNoSuchRoute() {
		Vertex a = graph.getVertex("A");
		Vertex d = graph.getVertex("D");
		Vertex e = graph.getVertex("E");
		
		noSuchRouteException.expect(RuntimeException.class);
		noSuchRouteException.expectMessage("NO SUCH ROUTE");
		IndividualPath aed = new IndividualPath(a, e, d);
		aed.getTotalWeight(DIST);
	}

}
