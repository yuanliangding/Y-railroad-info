package yuan.interview.railroad.impl.Y_Railroad_Info;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import yuan.interview.railroad.graph.algorithm.IndividualPath;
import yuan.interview.railroad.test.util.YRailroadGraphPolicyPrepared;

/** 
 * @ClassName: YRailroadGraphPolicy_CalcPath_Bounded_Test
 * @Description:  测试YRailroadGraphPolicy类中方法calcPath中-f s，-f d相关逻辑测试
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-下午5:26:57
 */
public class YRailroadGraphPolicy_CalcPath_Bounded_Test extends YRailroadGraphPolicyPrepared {
	
	@Test
	public void test() {
		
		//	count -f s -b C -e C -M 3
		Map<String, String> options1 = new HashMap<>();
		options1.put("f", "s");
		options1.put("b", "C");
		options1.put("e", "C");
		options1.put("M", "3");
		List<IndividualPath> paths1 = yRailroadGraphPolicy.calcPath(options1);
		Assert.assertThat("查询的结果数量不对", paths1.size(), CoreMatchers.equalTo(2));
		Assert.assertThat(
				"从C到C，路程最多跨越3站的路线有：C-D-C,C-E-B-C", 
				paths1.stream().map(IndividualPath::toString).collect(Collectors.toList()), 
				CoreMatchers.hasItems("C-D-C","C-E-B-C")
				);
		
		//	count -f s -b A -e C -m 4 -M 4
		Map<String, String> options2 = new HashMap<>();
		options2.put("f", "s");
		options2.put("b", "A");
		options2.put("e", "C");
		options2.put("m", "4");
		options2.put("M", "4");
		List<IndividualPath> paths2 = yRailroadGraphPolicy.calcPath(options2);
		Assert.assertThat("从A到C，路程正好跨越4站的路线有3条", paths2.size(), CoreMatchers.equalTo(3));
		Assert.assertThat(
				"从A到C，路程正好跨越4站的路线有3条：A-B-C-D-C,A-D-C-D-C,A-D-E-B-C", 
				paths2.stream().map(IndividualPath::toString).collect(Collectors.toList()), 
				CoreMatchers.hasItems("A-B-C-D-C","A-D-C-D-C", "A-D-E-B-C")
				);
		
		//	count -f d -b C -e C -M n30
		Map<String, String> options3 = new HashMap<>();
		options3.put("f", "d");
		options3.put("b", "C");
		options3.put("e", "C");
		options3.put("M", "n30");
		List<IndividualPath> paths3 = yRailroadGraphPolicy.calcPath(options3);
		Assert.assertThat("从C到C，路程小于30的路线有7条", paths3.size(), CoreMatchers.equalTo(7));
		Assert.assertThat(
				"从C到C，路程小于30的路线有7条：C-D-C,C-D-C-E-B-C,C-D-E-B-C,C-E-B-C,C-E-B-C-D-C,C-E-B-C-E-B-C,C-E-B-C-E-B-C-E-B-C", 
				paths3.stream().map(IndividualPath::toString).collect(Collectors.toList()), 
				CoreMatchers.hasItems(
						"C-D-C",
						"C-D-C-E-B-C",
						"C-D-E-B-C",
						"C-E-B-C",
						"C-E-B-C-D-C",
						"C-E-B-C-E-B-C",
						"C-E-B-C-E-B-C-E-B-C")
				);
	}

}
