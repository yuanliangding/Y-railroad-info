package yuan.interview.railroad.impl.Y_Railroad_Info;

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

import yuan.interview.railroad.graph.algorithm.IndividualPath;
import yuan.interview.railroad.interactive.CommandData;
import yuan.interview.railroad.test.util.YRailroadGraphPolicyPrepared;

/**
 * @ClassName: YRailroadGraphPolicy_CalcPath_Individual_Test
 * @Description: 测试YRailroadGraphPolicy类中方法calcPath中-p相关逻辑测试
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-下午5:47:09
 */
@RunWith(Parameterized.class)
public class YRailroadGraphPolicy_CalcPath_Individual_Test extends YRailroadGraphPolicyPrepared {

	private String path = null;

	public YRailroadGraphPolicy_CalcPath_Individual_Test(String path) {
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
		List<IndividualPath> paths = yRailroadGraphPolicy.calcPath(commandData);
		Assert.assertThat("生成失败", paths.size(), CoreMatchers.equalTo(1));
		Assert.assertThat("生成路径信息不准确", paths.get(0).toString(), CoreMatchers.equalTo(path));
	}

}
