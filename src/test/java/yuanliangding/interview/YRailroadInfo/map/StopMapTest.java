package yuanliangding.interview.YRailroadInfo.map;

import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/** 
 * @ClassName: StopMapTest
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-下午11:03:00
 */
public class StopMapTest {
	
	private StopMap stopMap = null;
	
	@Before
	public void before() {
		stopMap = StopMap.getInstance();
	}
	
    @Test
    public void testGetStop() {
    	Stop a1 = stopMap.getStop("A");
    	Stop a2 = stopMap.getStop("A");
    	Stop b = stopMap.getStop("B");
    	
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
    	Stop a = stopMap.getStop("A");
    	Stop b = stopMap.getStop("B");
    	Stop c = stopMap.getStop("C");
    	
    	String dimName = "dist";
    	int dimValAB = 8;
    	int dimValAC = 9;
    	
    	stopMap.addRoute(a, b, dimName, dimValAB);
    	stopMap.addRoute(a, c, dimName, dimValAC);
    	
    	Stop a_ = stopMap.getStop("A");
    	Stop b_ = stopMap.getStop("B");
    	Stop c_ = stopMap.getStop("C");
    	Map<Stop, Integer> nexts = a_.getNexts(dimName);
    	
    	Assert.assertThat("A,BC之间维度'"+dimName+"'的边权为:"+dimValAB, nexts.get(b_),CoreMatchers.equalTo(dimValAB));
    	Assert.assertThat("A,C之间维度'"+dimName+"'的边权为:"+dimValAC, nexts.get(c_),CoreMatchers.equalTo(dimValAC));
    }

}
