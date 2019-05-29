package yuanliangding.interview.YRailroadInfo.map;

import java.util.HashMap;
import java.util.Map;

/** 
 * @ClassName: MapDatum
 * @Description:  地图存储引擎.由站点(Stop)构成的地图.
 *
 *	@see Stop
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-上午10:06:52
 */
public class MapDatum {
	
	private final Map<String, Stop> stops = new HashMap<>();
	
	private static MapDatum instance = new MapDatum();
	
	public static MapDatum getInstance() {
		return instance;
	}
	
	private MapDatum() {}
	
	public void clear() {
		stops.clear();
	}
	
	/**
	 * 增加地图信息,既增加路线信息.为某两个站点增加路径信息,如,距离,行程时间等.
	 * 
	 * @param start		路线起始站点
	 * @param end		路线结束站点
	 * @param dim		维度(根据实际,可以有路程"dist",行程耗时"time",跨越站数"stop")
	 * @param weight	权重,如上可以是路程距离,行程耗时或者跨越站数值.
	 * */
	public void addRoute(Stop start, Stop end, String dim, int weight) {
		if (start == null || end == null) {
			throw new RuntimeException("两个合法的站点之间才可以增加路线信息");
		}
		
		start.addRoute(end, dim, weight);
	}
	
	/**
	 * 根据站名获得站点.如果第一次获取则自动创建,同样的站名,只能创建一个站点实例
	 * 
	 * @param name	站名
	 * */
	public Stop getStop(String name) {
		Stop stop = stops.get(name);
		if (stop == null) {
			stop = new Stop(name);
			stops.put(name, stop);
		}
		
		return stop;
	}

}
