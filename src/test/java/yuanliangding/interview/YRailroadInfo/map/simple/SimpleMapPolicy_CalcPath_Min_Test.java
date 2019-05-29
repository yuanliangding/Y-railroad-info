package yuanliangding.interview.YRailroadInfo.map.simple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import yuanliangding.interview.YRailroadInfo.graph.IndividualPath;
import yuanliangding.interview.YRailroadInfo.interactive.CommandParser.CommandData;

/** 
 * @ClassName: SimpleMapPolicy_CalcPath_Min_Test
 * @Description:  测试SimpleMapPolicy方法calcPath中-f md相关逻辑测试
 *
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-下午5:26:57
 */
public class SimpleMapPolicy_CalcPath_Min_Test extends TWDataProvider {
	
	@Test
	public void test() {
		
		//		dist -f md -b A -e C
		Map<String, String> options1 = new HashMap<>();
		options1.put("f", "md");
		options1.put("b", "A");
		options1.put("e", "C");
		CommandData commandData1 = new CommandData("dist", options1);
		List<IndividualPath> paths1 = simpleMapPolicy.calcPath(commandData1);
		Assert.assertThat("从A到C,最短路径路线有1条", paths1.size(), CoreMatchers.equalTo(1));
		Assert.assertThat(
				"从A到C,最短路径路线有1条:A-B-C", 
				paths1.stream().map(IndividualPath::toString).collect(Collectors.toList()), 
				CoreMatchers.hasItems("A-B-C")
				);

		//		dist -f md -b B -e B
		Map<String, String> options2 = new HashMap<>();
		options2.put("f", "md");
		options2.put("b", "B");
		options2.put("e", "B");
		CommandData commandData2 = new CommandData("dist", options2);
		List<IndividualPath> paths2 = simpleMapPolicy.calcPath(commandData2);
		Assert.assertThat("从B到B,最短路径路线有1条", paths2.size(), CoreMatchers.equalTo(1));
		Assert.assertThat(
				"从B到B,最短路径路线有1条:B-C-E-B", 
				paths2.stream().map(IndividualPath::toString).collect(Collectors.toList()), 
				CoreMatchers.hasItems("B-C-E-B")
				);
	}

}
