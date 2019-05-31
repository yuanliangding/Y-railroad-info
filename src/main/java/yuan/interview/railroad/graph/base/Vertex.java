package yuan.interview.railroad.graph.base;

import java.util.HashMap;
import java.util.Map;

import yuan.interview.railroad.exception.GraphException;

/**
 * @ClassName: Vertex
 * @Description: 图上的一个顶点. 名称相同则代表是同一个顶点.
 * 
 *               顶点一般包括的信息有: 1, 顶名名称. 2, 顶点通过有向边关联的后续所有顶点.及有向边上的多维度权重值.
 *               比如顶点A,有到B的有向边.及在有向边上的权重 {"D0":3,"D1":20,"D2":1},
 *               表示该有向边的权重有D0,D1,D2三个维度,各维度的分量值分别是3,20,1.
 *               又假设还有一条到C的有向边,并且这个有向边上的权重值为 {"D0":2,"D1":15,"D2":2}.
 *               由该A顶点的有向边信息可以表示为 A: { "B":{"D0":3,"D1":20,"D2":1},
 *               "C":{"D0":2,"D1":15,"D2":2} }
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-上午10:11:41
 */
public class Vertex {

	// 顶点名称
	private final String name;

	// 以该顶点为起点的所有有向边.按顶点进行存储各权重信息
	private final Map<Vertex, Map<String, Integer>> edges = new HashMap<>();

	/**
	 * @param name 顶点名称
	 */
	protected Vertex(String name) {
		if (name == null || "".equals(name)) {
			throw new GraphException("顶点名称不合法,名称长度至少为1");
		}
		this.name = name;
	}

	/**
	 * 获取顶点名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取当前顶点为起点的所有有向边信息
	 */
	public Map<Vertex, Map<String, Integer>> getEdges() {
		return edges;
	}

	/**
	 * 为当前顶点为起点,参数所指定的点为终点的有向边,若没有则自动增加一条有向边. 在这个有向边上,为某个权重维度设置权重分量值.
	 * 
	 * @param vertex 边的终点
	 * @param dim    权重的维度名称
	 * @param weight 权重的值
	 * 
	 */
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
			Vertex other = (Vertex) obj;
			result = other.getName().equals(this.getName());
		}
		return result;
	}

}