package yuanliangding.interview.YRailroadInfo.graph.base;

import java.util.HashMap;
import java.util.Map;

/** 
 * @ClassName: Graph
 * @Description:  图数据存储引擎.由站点(Vertex)构成的地图.
 *
 *	@see Vertex
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-上午10:06:52
 */
public class Graph {
	
	private final Map<String, Vertex> vertexs = new HashMap<>();
	
	public void clear() {
		vertexs.clear();
	}
	
	/**
	 * 增加地图信息,既增加路线信息.为某两个站点增加路径信息,如,距离,行程时间等.
	 * 
	 * @param start		路线起始站点
	 * @param end		路线结束站点
	 * @param dim		维度(根据实际,可以有路程"dist",行程耗时"time",跨越站数"stop")
	 * @param weight	权重,如上可以是路程距离,行程耗时或者跨越站数值.
	 * */
	public void addEdge(Vertex start, Vertex end, String dim, int weight) {
		if (start == null || end == null) {
			throw new RuntimeException("两个合法的站点之间才可以增加路线信息");
		}
		
		start.addEdge(end, dim, weight);
	}
	
	/**
	 * 根据站名获得站点.如果第一次获取则自动创建,同样的站名,只能创建一个站点实例
	 * 
	 * @param name	站名
	 * */
	public Vertex getVertex(String name) {
		Vertex vertex = vertexs.get(name);
		if (vertex == null) {
			vertex = new Vertex(name);
			vertexs.put(name, vertex);
		}
		
		return vertex;
	}

}
