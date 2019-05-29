package yuanliangding.interview.YRailroadInfo;

import yuanliangding.interview.YRailroadInfo.core.YRailroadContext;
import yuanliangding.interview.YRailroadInfo.map.simple.SimpleYRailroadContext;

/** 
 * @ClassName: Application
 * @Description:  TODO
 *
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-下午8:52:43
 */
public class Application {

	public static void main(String[] args) {
		YRailroadContext yRailroadContext = new SimpleYRailroadContext();
		yRailroadContext.start(null/*mapUrl*/, null/*exit*/);
	}

}
