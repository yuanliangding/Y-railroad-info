package yuan.interview.railroad.exception;

/** 
 * @ClassName: InteractiveException
 * @Description:  交互异常
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月31日-上午10:52:25
 */
public class InteractiveException extends RuntimeException {

	private static final long serialVersionUID = 2245382964757352652L;

	public InteractiveException(String message) {
		super(message);
	}

	public InteractiveException(Throwable cause) {
		super(cause);
	}

	public InteractiveException(String message, Throwable cause) {
		super(message, cause);
	}

	public InteractiveException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
