package yuanliangding.interview.YRailroadInfo.graph.base;

import java.util.List;

/** 
 * @ClassName: GraphReader
 * @Description:  地图数据读取器,从文件中或者外界别流式数据中,读取到图数据的有向边.并以普通JSON对象EdgeData表示.
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-下午11:50:08
 */
public interface GraphReader {
	
	public List<EdgeData> read();
	
	/**
	 * 一条有向边的普通JSON表示
	 * */
	public static class EdgeData {
		private String start = null;
		private String end = null;
		private String layer = null;
		private int weight = 0;
		
		public EdgeData(String start, String end, String layer, int weight) {
			this.start = start;
			this.end = end;
			this.layer = layer;
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

		/**该有向边所属的层名称*/
		public String getLayer() {
			return layer;
		}

		/**该有向边的权重值*/
		public int getWeight() {
			return weight;
		}
	}

}
