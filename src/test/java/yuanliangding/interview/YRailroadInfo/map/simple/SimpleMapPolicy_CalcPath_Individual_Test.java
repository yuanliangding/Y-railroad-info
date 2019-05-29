package yuanliangding.interview.YRailroadInfo.map.simple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import yuanliangding.interview.YRailroadInfo.graph.IndividualPath;
import yuanliangding.interview.YRailroadInfo.interactive.CommandParser.CommandData;

/**
 * @ClassName: SimpleMapPolicy_CalcPath_Individual_Test
 * @Description: 测试SimpleMapPolicy方法calcPath中-p相关逻辑测试
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-下午5:47:09
 */
@RunWith(Parameterized.class)
public class SimpleMapPolicy_CalcPath_Individual_Test extends TWDataProvider {

	private String path = null;

	public SimpleMapPolicy_CalcPath_Individual_Test(String path) {
		this.path = path;
	}

	@Parameters
	public static List<String> data() {
		return Arrays.asList("A-B-C","A-D","A-D-C","A-E-B-C-D","A-E-D");
	}

	@Test
	public void test() {
		Map<String, String> options = new HashMap<>();
		options.put("p", path);
		CommandData commandData = new CommandData("dist", options);
		List<IndividualPath> paths = simpleMapPolicy.calcPath(commandData);
		Assert.assertThat("生成失败", paths.size(), CoreMatchers.equalTo(1));
		Assert.assertThat("生成路径信息不准确", paths.get(0).toString(), CoreMatchers.equalTo(path));
	}

}