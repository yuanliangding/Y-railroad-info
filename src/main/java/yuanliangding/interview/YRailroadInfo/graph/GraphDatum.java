package yuanliangding.interview.YRailroadInfo.graph;

import java.util.HashMap;
import java.util.Map;

/** 
 * @ClassName: GraphDatum
 * @Description:  地图存储引擎.由站点(Stop)构成的地图.
 *
 *	@see Stop
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-上午10:06:52
 */
public class GraphDatum {
	
	private final Map<String, Stop> stops = new HashMap<>();
	
	private static GraphDatum instance = new GraphDatum();
	
	public static GraphDatum getInstance() {
		return instance;
	}
	
	private GraphDatum() {}
	
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
	
	/** 
	 * @ClassName: Stop
	 * @Description:  地图路线上的站点.
	 * 
	 * 	站点一般包括的信息有:
	 * 		1,	站名名称.
	 * 		2,	站点的后续站点.后续站点相关信息会划分成几个维度来记录.
	 * 			比如当前站点为A,后续直接站点有B和C.从A到B的距离为3,行驶时间为20,跨越站数为1,从A到C的距离为2,行驶时间为15,跨越站数为1,
	 * 			则可以记录为
	 * 				[A] {
	 * 						"dist":{"<B>":3,"<C>":2},
	 * 						"time":{"<B>":20,"<C>":15},
	 * 						"stop":{"<B>":1,"<C>":1}
	 * 					}
	 * 			
	 * 			站点和站点之间的跨度,耗时,或者经信站,分别用不同的带权重有向边进行记录.三条有向边被视为三个维度的权重有向边.
	 * 			对于要计算最短经停,或最短耗时,或最短距离的路线,可以采用统一的算法.只要选择对应的维度边便可以.
	 * 			或者需要在相应维度上其它要求的路径计算(编码)
	 *
	 * @author 袁良锭(https://github.com/yuanliangding)
	 * @date 2019年5月26日-上午10:11:41
	 */
	public static class Stop {
		
		private final Map<String, Map<Stop,Integer>> routes = new HashMap<>();
		
		private final String name;

		/**
		 * @param name 站点名称
		 * */
		private Stop(String name) {
			if (name == null || "".equals(name)) {
				throw new RuntimeException("站名不合法,名称长度至少为1");
			}
			this.name = name;
		}
		
		/**
		 * 获取站点名称
		 * */
		public String getName() {
			return name;
		}
		
		/**
		 * 根据维度信息,返回后续站点.和对应的权重值
		 * 
		 * @param dim
		 * */
		public Map<Stop,Integer> getNexts(String dim) {
			return routes.getOrDefault(dim, new HashMap<>());
		}
		
		/**
		 * 为指定的维度,增加有向边.
		 * 
		 * TODO 该方法不是线程安全的
		 * 
		 * @param stop		相应站点
		 * @param dim 		维度
		 * @param weight	权重
		 * 
		 * */
		protected void addRoute(Stop stop, String dim, int weight) {
			Map<Stop,Integer> routesByDim = routes.get(dim);
			
			if (routesByDim == null) {
				routesByDim = new HashMap<>();
				routes.put(dim, routesByDim);
			}
			
			routesByDim.put(stop, weight);
		}

		@Override
		public int hashCode() {
			return name.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			boolean result = false;
			if (obj != null && obj instanceof Stop) {
				Stop other = (Stop)obj;
				result = other.getName().equals(this.getName());
			}
			return result;
		}

	}

}
