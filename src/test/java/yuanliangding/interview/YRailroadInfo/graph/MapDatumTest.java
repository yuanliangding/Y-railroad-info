package yuanliangding.interview.YRailroadInfo.graph;

import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import yuanliangding.interview.YRailroadInfo.graph.MapDatum;
import yuanliangding.interview.YRailroadInfo.graph.MapDatum.Stop;

/** 
 * @ClassName: MapDatumTest
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-下午11:03:00
 */
public class MapDatumTest {
	
	private MapDatum mapDatum = null;
	
	@Before
	public void before() {
		mapDatum = MapDatum.getInstance();
	}
	
    @Test
    public void testGetStop() {
    	Stop a1 = mapDatum.getStop("A");
    	Stop a2 = mapDatum.getStop("A");
    	Stop b = mapDatum.getStop("B");
    	
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
    	Stop a = mapDatum.getStop("A");
    	Stop b = mapDatum.getStop("B");
    	Stop c = mapDatum.getStop("C");
    	
    	String dimName = "dist";
    	int dimValAB = 8;
    	int dimValAC = 9;
    	
    	mapDatum.addRoute(a, b, dimName, dimValAB);
    	mapDatum.addRoute(a, c, dimName, dimValAC);
    	
    	Stop a_ = mapDatum.getStop("A");
    	Stop b_ = mapDatum.getStop("B");
    	Stop c_ = mapDatum.getStop("C");
    	Map<Stop, Integer> nexts = a_.getNexts(dimName);
    	
    	Assert.assertThat("A,BC之间维度'"+dimName+"'的边权为:"+dimValAB, nexts.get(b_),CoreMatchers.equalTo(dimValAB));
    	Assert.assertThat("A,C之间维度'"+dimName+"'的边权为:"+dimValAC, nexts.get(c_),CoreMatchers.equalTo(dimValAC));
    }

}
