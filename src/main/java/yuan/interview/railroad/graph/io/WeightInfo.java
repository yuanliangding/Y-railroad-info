package yuan.interview.railroad.graph.io;

/**
 * @ClassName: WeightInfo
 * @Description:一条有向边权重分量值的普通JSON表示
 * */
public class WeightInfo {
	
	private String start = null;
	private String end = null;
	private String dim = null;
	private int weight = 0;
	
	public WeightInfo(String start, String end, String dim, int weight) {
		this.start = start;
		this.end = end;
		this.dim = dim;
		this.weight = weight;
	}

	/**该有向边的起点名称*/
	public String getStart() {
		return start;
	}

	/**该有向边的终点名称*/
	public String getEnd() {
		return end;
	}

	/**在该有向边上的权重维度*/
	public String getDim() {
		return dim;
	}

	/**有向边的权重分量值*/
	public int getWeight() {
		return weight;
	}
}