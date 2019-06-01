package yuan.interview.railroad.graph.base;

import java.util.HashMap;
import java.util.Map;

import yuan.interview.railroad.exception.GraphException;

/** 
 * @ClassName: Graph
 * @Description:  图数据引擎
 * 						该数据引擎提供以下数据结构的表示及相应的操作。
 * 						图中所有顶点集。某个顶点到另一个顶点的有向边。
 * 						以及在这个有向边上的多维度权重。
 * 						即，权重值不只是一个简单的数字。而是由若干维度的不同值分别表示权重的各分量值。
 * 						权重是由各维度名称及相应的权重值组成
 * 						图论算法，比如最短路径。在这里是根据某个维度名称。基于相应的权重分量进行遍历计算。
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
	 * 为有向边的某个权重分量设值，
	 * 由起点和终天确定有向边，如果不存在则自动建立。
	 * 
	 * @param start		边的起点
	 * @param end		边的终点
	 * @param dim		权重维度
	 * @param weight	权重值
	 * */
	public void setWeight(Vertex start, Vertex end, String dim, int weight) {
		if (start == null || end == null) {
			throw new GraphException("必须为边指定起点和终点");
		}
		
		if (dim == null || "".equals(dim)) {
			throw new GraphException("必须指定权重的维度名称");
		}
		
		start.setWeight(end, dim, weight);
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
