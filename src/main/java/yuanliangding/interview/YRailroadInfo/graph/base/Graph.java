package yuanliangding.interview.YRailroadInfo.graph.base;

import java.util.HashMap;
import java.util.Map;

import yuanliangding.interview.YRailroadInfo.graph.GraphException;

/** 
 * @ClassName: Graph
 * @Description:  图数据引擎.该数据引擎提供了存储多层图(不是多重图)的方案.
 * 						既,除了存储顶点(Vertex)和边(Edge),还可以为边标识是属于哪一层(layer)的.
 * 						图论算法,比如最短路径,一般是基于具体某一个层的.比如指定了层L,最短路径算法
 * 						只在被标识为层L的所有边中进行遍历计算,
 *
 *	@see Vertex
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-上午10:06:52
 */
public class Graph {
	
	//存储所有顶点,可以根据顶点名快速索引
	private final Map<String, Vertex> vertexs = new HashMap<>();
	
	public void clear() {
		vertexs.clear();
	}
	
	/**
	 * 增加一条有向边
	 * 
	 * @param start		边的起点
	 * @param end		边的终点
	 * @param layer		边所属的层
	 * @param weight	边的权重
	 * */
	public void addEdge(Vertex start, Vertex end, String layer, int weight) {
		if (start == null || end == null) {
			throw new GraphException("必须为边指定");
		}
		
		start.setWeight(end, layer, weight);
	}
	
	/**
	 * 根据名称获得一个顶点
	 * 
	 * @param name	顶点名称
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
