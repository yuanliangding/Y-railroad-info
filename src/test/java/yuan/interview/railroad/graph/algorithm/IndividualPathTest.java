package yuan.interview.railroad.graph.algorithm;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import yuan.interview.railroad.exception.GraphException;
import yuan.interview.railroad.graph.algorithm.IndividualPath;
import yuan.interview.railroad.graph.base.Vertex;
import yuan.interview.railroad.test.util.DefaultDataPrepared;

/**
 * @ClassName: IndividualPathTest
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午12:26:56
 */
public class IndividualPathTest extends DefaultDataPrepared{

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
		Assert.assertThat("验证" + abc + "路径权重总值出错", abc.getTotalWeight(DIST), CoreMatchers.equalTo(9L));

		IndividualPath ad = new IndividualPath(a, d);
		Assert.assertThat("验证" + ad + "路径权重总值出错", ad.getTotalWeight(DIST), CoreMatchers.equalTo(5L));

		IndividualPath adc = new IndividualPath(a, d, c);
		Assert.assertThat("验证" + adc + "路径权重总值出错", adc.getTotalWeight(DIST), CoreMatchers.equalTo(13L));

		IndividualPath aebcd = new IndividualPath(a, e, b, c, d);
		Assert.assertThat("验证" + aebcd + "路径权重总值出错", aebcd.getTotalWeight(DIST), CoreMatchers.equalTo(22L));
	}
	
	@Test
	public void testNoSuchRoute() {
		Vertex a = graph.getVertex("A");
		Vertex d = graph.getVertex("D");
		Vertex e = graph.getVertex("E");
		
		noSuchRouteException.expect(GraphException.class);
		noSuchRouteException.expectMessage("NO SUCH ROUTE");
		IndividualPath aed = new IndividualPath(a, e, d);
		aed.getTotalWeight(DIST);
	}

}
