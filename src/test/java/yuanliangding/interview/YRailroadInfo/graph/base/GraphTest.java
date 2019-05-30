package yuanliangding.interview.YRailroadInfo.graph.base;

import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import yuanliangding.interview.YRailroadInfo.graph.base.Graph;

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
    public void testGetVertex() {
    	Vertex a1 = graph.getVertex("A");
    	Vertex a2 = graph.getVertex("A");
    	Vertex b = graph.getVertex("B");
    	
    	Assert.assertThat(
    			"获取到的顶点,其中的顶点名称应该和参数传入的名称一致", 
    			a1.getName() , 
    			CoreMatchers.equalTo("A")
    			);
    	
    	Assert.assertThat(
    			"相同的顶点名称,获取到的应该是同样的顶点实例", 
    			a1, 
    			CoreMatchers.sameInstance(a2)
    			);
    	
    	Assert.assertThat(
    			"不同的顶点名称,获取到的应该是不同的顶点实例,并且通过实例equal计算应该是不相等的", 
    			a1,
    			CoreMatchers.allOf(
    					CoreMatchers.not(CoreMatchers.sameInstance(b)),
    					CoreMatchers.not(CoreMatchers.equalTo(b)))
    			);
    }
    
    @Test
    public void testAddEdge() {
    	Vertex a = graph.getVertex("A");
    	Vertex b = graph.getVertex("B");
    	Vertex c = graph.getVertex("C");
    	
    	String layer = "L0";
    	int weightAB = 8;
    	int weightAC = 9;
    	
    	graph.addEdge(a, b, layer, weightAB);
    	graph.addEdge(a, c, layer, weightAC);
    	
    	Vertex a_ = graph.getVertex("A");
    	Vertex b_ = graph.getVertex("B");
    	Vertex c_ = graph.getVertex("C");
    	Map<Vertex, Integer> nexts = a_.getEdges(layer);
    	
    	Assert.assertThat("A,B之间所属层为'"+layer+"'的边的权重为:"+weightAB, nexts.get(b_),CoreMatchers.equalTo(weightAB));
    	Assert.assertThat("A,C之间所层层为'"+layer+"'的边的权重为:"+weightAC, nexts.get(c_),CoreMatchers.equalTo(weightAC));
    }

}
