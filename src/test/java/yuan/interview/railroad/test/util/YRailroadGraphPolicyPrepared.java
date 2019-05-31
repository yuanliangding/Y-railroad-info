package yuan.interview.railroad.test.util;

import java.io.IOException;

import org.junit.Before;

import yuan.interview.railroad.impl.Y_Railroad_Info.TWGraphReader;
import yuan.interview.railroad.impl.Y_Railroad_Info.YRailroadGraphPolicy;

/** 
 * @ClassName: YRailroadGraphPolicyPrepared
 * @Description:  为测试准备数据,TW题目要求里的数据
 *
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-下午5:20:38
 */
public class YRailroadGraphPolicyPrepared extends TWDataPrepared {
	
	protected YRailroadGraphPolicy yRailroadGraphPolicy = null;
	
	@Before
	public void before() throws IOException {
		yRailroadGraphPolicy = new YRailroadGraphPolicy();
		yRailroadGraphPolicy.setGraphReader(new TWGraphReader(dataPath));
	}

}
