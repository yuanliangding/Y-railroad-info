package yuan.interview.railroad.core;

import java.io.InputStream;
import java.io.PrintStream;

/** 
 * @ClassName: ApplicationContext
 * @Description:  基于yuan.interview.railroad.graph和yuan.interview.railroad.interactive包构建的图数据系统上下文
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午7:00:37
 */
public interface ApplicationContext {
	
	void start(
			InputStream standardIn, 
			PrintStream standardOut,
			PrintStream standardError,
			String mapUrl, 
			String exit);
	
	String banner();
	
}
