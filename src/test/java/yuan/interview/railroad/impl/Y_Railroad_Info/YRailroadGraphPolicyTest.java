package yuan.interview.railroad.impl.Y_Railroad_Info;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/** 
 * @ClassName: YRailroadGraphPolicyTest
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-下午4:48:18
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
		{
			YRailroadGraphPolicy_CalcPath_Individual_Test.class,
			YRailroadGraphPolicy_CalcPath_Bounded_Test.class,
			YRailroadGraphPolicy_CalcPath_Min_Test.class
		})
public class YRailroadGraphPolicyTest {}