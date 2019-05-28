package yuanliangding.interview.YRailroadInfo.core;

/** 
 * @ClassName: YRailroadContext
 * @Description:  通勤交通线路查询系统核心上下文
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午7:00:37
 */
public abstract class YRailroadContext {
	
	protected abstract void start(String mapUrl, String exit);
	
	protected abstract String banner();
}
