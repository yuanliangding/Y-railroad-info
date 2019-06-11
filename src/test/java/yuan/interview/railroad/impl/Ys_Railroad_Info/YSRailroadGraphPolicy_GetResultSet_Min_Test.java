package yuan.interview.railroad.impl.Ys_Railroad_Info;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import yuan.interview.railroad.graph.algorithm.IndividualPath;
import yuan.interview.railroad.test.util.QuadGraphPolicyPrepared;

/** 
 * @ClassName: YSRailroadGraphPolicy_GetResultSet_Min_Test
 * @Description:  测试YRailroadGraphPolicy类中方法calcPath中-f md相关逻辑测试
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-下午5:26:57
 */
public class YSRailroadGraphPolicy_GetResultSet_Min_Test extends QuadGraphPolicyPrepared {
	
	@Test
	public void test() {
		
		//		dist -f md -b A -e C
		Map<String, String> options1 = new HashMap<>();
		options1.put("f", "md");
		options1.put("b", "A");
		options1.put("e", "C");
		List<IndividualPath> paths1 = ysRailroadGraphPolicy.getResultSet(options1);
		Assert.assertThat("从A到C，最短路径路线有1条", paths1.size(), CoreMatchers.equalTo(1));
		Assert.assertThat(
				"从A到C，最短路径路线有1条:A-B-C", 
				paths1.stream().map(IndividualPath::toString).collect(Collectors.toList()), 
				CoreMatchers.hasItems("A-B-C")
				);

		//		dist -f md -b B -e B
		Map<String, String> options2 = new HashMap<>();
		options2.put("f", "md");
		options2.put("b", "B");
		options2.put("e", "B");
		List<IndividualPath> paths2 = ysRailroadGraphPolicy.getResultSet(options2);
		Assert.assertThat("从B到B，最短路径路线有1条", paths2.size(), CoreMatchers.equalTo(1));
		Assert.assertThat(
				"从B到B，最短路径路线有1条：B-C-E-B", 
				paths2.stream().map(IndividualPath::toString).collect(Collectors.toList()), 
				CoreMatchers.hasItems("B-C-E-B")
				);
	}

}
