package yuanliangding.interview.YRailroadInfo.interactive;

/** 
 * @ClassName: TerminatorCommandReceiver
 * @Description:  命令行等式的命令接收器
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午3:22:58
 */
public class TerminatorCommandReceiver extends CommandReceiver {

	/**
	 * @param standardIn
	 * @param standardOut
	 * @param standardError
	 * @param commandParser
	 */
	public TerminatorCommandReceiver() {
		super(System.in, System.out, System.err, new SimpleCommandParser());
	}

}
