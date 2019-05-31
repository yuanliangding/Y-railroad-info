package yuan.interview.railroad.core;

import java.io.InputStream;
import java.io.PrintStream;

/** 
 * @ClassName: YRailroadContext
 * @Description:  通勤交通线路查询系统核心上下文
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午7:00:37
 */
public abstract class YRailroadContext {
	
	public abstract void start(
			InputStream standardIn, 
			PrintStream standardOut,
			PrintStream standardError,
			String mapUrl, 
			String exit);
	
	protected abstract String banner();
}
