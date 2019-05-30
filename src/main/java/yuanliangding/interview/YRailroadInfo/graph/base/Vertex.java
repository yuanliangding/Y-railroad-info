package yuanliangding.interview.YRailroadInfo.graph.base;

import java.util.HashMap;
import java.util.Map;

import yuanliangding.interview.YRailroadInfo.graph.GraphException;

/** 
 * @ClassName: Vertex
 * @Description:  	图上的一个顶点.
 * 						名称相同则代表是同一个顶点.
 * 
 * 	顶点一般包括的信息有:
 * 		1,	顶名名称.
 * 		2,	顶点通过有向边关联的后续所有顶点.有向边除了具有权重信息外,还具有所属层.
 * 			比如顶点A,以A为起点有有一条权重为3的有向边关联到B,另一条权重为2的有向边关联到C,这两条边所属的层为L0,
 * 			属于L1层的,以A为起点还有两有向条,分别是权重为20关联到B,权重为15关联到C.
 * 			属于L2层,以A为起点的有向条是:权重1关联到B,权重2关联到C.
 * 			则被记录为
 * 				A: {
 * 						"L0":{"<B>":3,"<C>":2},
 * 						"L1":{"<B>":20,"<C>":15},
 * 						"L2":{"<B>":1,"<C>":2}
 * 					}
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-上午10:11:41
 */
public class Vertex {
	
	// 以该顶点为起点的所有有向边.先按层进行存储,在层里,再存储所有边.
	private final Map<Vertex, Map<String, Integer>> edges = new HashMap<>();
	
	// 顶点名称
	private final String name;

	/**
	 * @param name 顶点名称
	 * */
	protected Vertex(String name) {
		if (name == null || "".equals(name)) {
			throw new GraphException("顶点名称不合法,名称长度至少为1");
		}
		this.name = name;
	}
	
	/**
	 * 获取顶点名称
	 * */
	public String getName() {
		return name;
	}
	
	/**
	 * 根据所属层,返回以该顶点为起点的所有有向边,用(顶点,权重)对来表示返回结果
	 * */
	public Map<Vertex, Map<String, Integer>> getEdges() {
		return edges;
	}
	
	/**
	 * 为当前顶点增加一条出去的有向边,当前顶点做为有向边的起始点.
	 * 
	 * @param vertex	边的终点
	 * @param dim 	边所属的层
	 * @param weight	边的权重
	 * 
	 * */
	protected void setWeight(Vertex vertex, String dim, int weight) {
		if (!edges.containsKey(vertex)) {
			edges.put(vertex, new HashMap<>());
		}
		
		Map<String, Integer> weights = edges.get(vertex);
		weights.put(dim, weight);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj != null && obj instanceof Vertex) {
			Vertex other = (Vertex)obj;
			result = other.getName().equals(this.getName());
		}
		return result;
	}

}