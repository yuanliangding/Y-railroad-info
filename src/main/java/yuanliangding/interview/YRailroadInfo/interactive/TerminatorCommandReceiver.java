package yuanliangding.interview.YRailroadInfo.interactive;

import java.io.InputStream;
import java.io.PrintStream;

/** 
 * @ClassName: TerminatorCommandReceiver
 * @Description:  命令行等式的命令接收器
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午3:22:58
 */
public class TerminatorCommandReceiver extends CommandReceiver {

	@Override
	protected InputStream getStandardIn() {
		return System.in;
	}

	@Override
	protected PrintStream getStandardOut() {
		return System.out;
	}

	@Override
	protected PrintStream getStandardError() {
		return System.err;
	}
	
	@Override
	protected void destroy() {}

}
