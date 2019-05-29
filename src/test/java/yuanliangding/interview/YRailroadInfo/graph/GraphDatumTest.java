package yuanliangding.interview.YRailroadInfo.graph;

import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import yuanliangding.interview.YRailroadInfo.graph.GraphDatum;
import yuanliangding.interview.YRailroadInfo.graph.GraphDatum.Vertex;

/** 
 * @ClassName: GraphDatumTest
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-下午11:03:00
 */
public class GraphDatumTest {
	
	private GraphDatum graphDatum = null;
	
	@Before
	public void before() {
		graphDatum = new GraphDatum();
	}
	
    @Test
    public void testGetStop() {
    	Vertex a1 = graphDatum.getVertex("A");
    	Vertex a2 = graphDatum.getVertex("A");
    	Vertex b = graphDatum.getVertex("B");
    	
    	Assert.assertThat(
    			"获取到的站点,其中的站名应该和参数传入的名称一致", 
    			a1.getName() , 
    			CoreMatchers.equalTo("A")
    			);
    	
    	Assert.assertThat(
    			"相同的站名,获取到的应该是同样的站点实例", 
    			a1, 
    			CoreMatchers.sameInstance(a2)
    			);
    	
    	Assert.assertThat(
    			"不同的站名,获取到的应该是不同的站点实例,并且通过实例equal计算也获得不同的值", 
    			a1,
    			CoreMatchers.allOf(
    					CoreMatchers.not(CoreMatchers.sameInstance(b)),
    					CoreMatchers.not(CoreMatchers.equalTo(b)))
    			);
    }
    
    @Test
    public void testAddRoute() {
    	Vertex a = graphDatum.getVertex("A");
    	Vertex b = graphDatum.getVertex("B");
    	Vertex c = graphDatum.getVertex("C");
    	
    	String dimName = "dist";
    	int dimValAB = 8;
    	int dimValAC = 9;
    	
    	graphDatum.addEdge(a, b, dimName, dimValAB);
    	graphDatum.addEdge(a, c, dimName, dimValAC);
    	
    	Vertex a_ = graphDatum.getVertex("A");
    	Vertex b_ = graphDatum.getVertex("B");
    	Vertex c_ = graphDatum.getVertex("C");
    	Map<Vertex, Integer> nexts = a_.getEdges(dimName);
    	
    	Assert.assertThat("A,BC之间维度'"+dimName+"'的边权为:"+dimValAB, nexts.get(b_),CoreMatchers.equalTo(dimValAB));
    	Assert.assertThat("A,C之间维度'"+dimName+"'的边权为:"+dimValAC, nexts.get(c_),CoreMatchers.equalTo(dimValAC));
    }

}
