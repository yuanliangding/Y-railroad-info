package yuan.interview.railroad.impl.simple;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/** 
 * @ClassName: SimpleMapPolicyTest
 *
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-下午4:48:18
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
		{
			SimpleMapPolicy_SetGraphReader_Test.class,
			SimpleMapPolicy_CalcPath_Individual_Test.class,
			SimpleMapPolicy_CalcPath_Bounded_Test.class,
			SimpleMapPolicy_CalcPath_Min_Test.class
		})
public class SimpleMapPolicyTest {}