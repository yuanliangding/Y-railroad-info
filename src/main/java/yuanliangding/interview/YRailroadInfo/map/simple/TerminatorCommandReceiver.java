package yuanliangding.interview.YRailroadInfo.map.simple;

import yuanliangding.interview.YRailroadInfo.interactive.CommandReceiver;

/** 
 * @ClassName: TerminatorCommandReceiver
 * @Description:  命令行等式的命令接收器
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午3:22:58
 */
public class TerminatorCommandReceiver extends CommandReceiver {
	
	private final static TerminatorCommandReceiver instance = new TerminatorCommandReceiver();

	/**
	 * @param standardIn
	 * @param standardOut
	 * @param standardError
	 * @param commandParser
	 */
	private TerminatorCommandReceiver() {
		super(System.in, System.out, System.err);
	}
	
	public static TerminatorCommandReceiver getInstance() {
		return instance;
	}

}
