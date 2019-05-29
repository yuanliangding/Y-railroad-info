package yuanliangding.interview.YRailroadInfo.map;

import java.util.Map;

import yuanliangding.interview.YRailroadInfo.interactive.Command;
import yuanliangding.interview.YRailroadInfo.map.MapDatum.Stop;

/** 
 * @ClassName: SimpleMapPolicy
 * @Description:  简单地图策略
 * 						维护两个维度的信息.1 dist维护的边权重,2 stop维护的边权重
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-上午6:28:46
 */
public class SimpleMapPolicy implements MapPolicy<Command, SimpleMapPolicy.Weight> {
	
	private static SimpleMapPolicy instance = new SimpleMapPolicy();

	public static SimpleMapPolicy getInstance() {
		return instance;
	}

	private SimpleMapPolicy() {}

	/**
	 * 一般只会传起点,终点和这之间有向边的权重值.在该策略中,既表示dist=weight,stop=1
	 * @param map		地图存储器
	 * @param start		起点
	 * @param end		终点
	 * @param weight	(这个参数,在该策略中不需要)
	 * @param value		权重值
	 */
	@Override
	public void addRoute(MapDatum map, Stop start, Stop end, Weight weight, int value) {
		map.addRoute(start, end, Weight.DIST.name(), value);
		map.addRoute(start, end, Weight.STOP.name(), 1);
	}
	
	/* (non-Javadoc)
	 * @see yuanliangding.interview.YRailroadInfo.map.MapPolicy#getCommands()
	 */
	@Override
	public Map<String, Command> getCommands() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/** 权重名 */
	public static enum Weight {
		DIST,	//距离
		STOP	//跨越站数
	}
	
}
