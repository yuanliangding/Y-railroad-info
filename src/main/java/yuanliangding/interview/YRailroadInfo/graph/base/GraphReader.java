package yuanliangding.interview.YRailroadInfo.graph.base;

import java.util.List;

/** 
 * @ClassName: GraphReader
 * @Description:  地图数据读取器,从文件中或者外界其他的流式数据中,读取图数据的有向边信息.并以普通JSON对象WeightInfo表示.
 * 
 * @see Graph
 * @see Vertex
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-下午11:50:08
 */
public interface GraphReader {
	
	public List<WeightInfo> read();
	
	/**
	 * 一条有向边权重分量值的普通JSON表示
	 * */
	public static class WeightInfo {
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

}
