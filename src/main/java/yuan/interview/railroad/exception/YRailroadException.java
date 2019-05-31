package yuan.interview.railroad.exception;

/** 
 * @ClassName: GraphException
 * @Description: 底层图相关的异常信息.
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月30日-上午10:29:30
 */
public class YRailroadException extends RuntimeException {

	private static final long serialVersionUID = -2342529582088837052L;

	public YRailroadException() {
		super();
	}

	public YRailroadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public YRailroadException(String message, Throwable cause) {
		super(message, cause);
	}

	public YRailroadException(String message) {
		super(message);
	}

	public YRailroadException(Throwable cause) {
		super(cause);
	}

}
