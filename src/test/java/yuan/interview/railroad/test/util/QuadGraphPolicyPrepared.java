package yuan.interview.railroad.test.util;

import java.io.IOException;

import org.junit.Before;

import yuan.interview.railroad.impl.Ys_Railroad_Info.QuadGraphReader;
import yuan.interview.railroad.impl.Ys_Railroad_Info.YSRailroadGraphPolicy;

/** 
 * @ClassName: YRailroadGraphPolicyPrepared
 * @Description:  为测试准备数据,TW题目要求里的数据
 *
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-下午5:20:38
 */
public class QuadGraphPolicyPrepared {
	
	protected YSRailroadGraphPolicy ysRailroadGraphPolicy = null;
	
	@Before
	public void before() throws IOException {
		ysRailroadGraphPolicy = new YSRailroadGraphPolicy();
		ysRailroadGraphPolicy.setGraphReader(new QuadGraphReader(null));
	}

}
