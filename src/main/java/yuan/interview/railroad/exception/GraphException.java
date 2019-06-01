package yuan.interview.railroad.exception;

/** 
 * @ClassName: GraphException
 * @Description: 底层图相关的异常信息
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月30日-上午10:29:30
 */
public class GraphException extends RuntimeException {

	private static final long serialVersionUID = -2342529582088837052L;

	public GraphException() {
		super();
	}

	public GraphException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GraphException(String message, Throwable cause) {
		super(message, cause);
	}

	public GraphException(String message) {
		super(message);
	}

	public GraphException(Throwable cause) {
		super(cause);
	}

}
