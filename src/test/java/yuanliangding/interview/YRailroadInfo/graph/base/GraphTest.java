package yuanliangding.interview.YRailroadInfo.graph.base;

import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import yuanliangding.interview.YRailroadInfo.graph.base.Graph;
import yuanliangding.interview.YRailroadInfo.graph.base.Graph.Vertex;

/** 
 * @ClassName: GraphTest
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-下午11:03:00
 */
public class GraphTest {
	
	private Graph graph = null;
	
	@Before
	public void before() {
		graph = new Graph();
	}
	
    @Test
    public void testGetStop() {
    	Vertex a1 = graph.getVertex("A");
    	Vertex a2 = graph.getVertex("A");
    	Vertex b = graph.getVertex("B");
    	
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
    	Vertex a = graph.getVertex("A");
    	Vertex b = graph.getVertex("B");
    	Vertex c = graph.getVertex("C");
    	
    	String dimName = "dist";
    	int dimValAB = 8;
    	int dimValAC = 9;
    	
    	graph.addEdge(a, b, dimName, dimValAB);
    	graph.addEdge(a, c, dimName, dimValAC);
    	
    	Vertex a_ = graph.getVertex("A");
    	Vertex b_ = graph.getVertex("B");
    	Vertex c_ = graph.getVertex("C");
    	Map<Vertex, Integer> nexts = a_.getEdges(dimName);
    	
    	Assert.assertThat("A,BC之间维度'"+dimName+"'的边权为:"+dimValAB, nexts.get(b_),CoreMatchers.equalTo(dimValAB));
    	Assert.assertThat("A,C之间维度'"+dimName+"'的边权为:"+dimValAC, nexts.get(c_),CoreMatchers.equalTo(dimValAC));
    }

}
