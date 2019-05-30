package yuanliangding.interview.YRailroadInfo.graph.base;

import java.util.List;

/** 
 * @ClassName: GraphReader
 * @Description:  地图加载器,通过指定一个文件路径进行地图数据的加载
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-下午11:50:08
 */
public interface GraphReader {
	
	public List<GraphEdge> read();
	
	public static class GraphEdge {
		private String start = null;
		private String end = null;
		private String weightName = null;
		private int weightValue = 0;
		
		public GraphEdge(String start, String end, String weightName, int weightValue) {
			this.start = start;
			this.end = end;
			this.weightName = weightName;
			this.weightValue = weightValue;
		}

		/**起点*/
		public String getStart() {
			return start;
		}

		/**终点*/
		public String getEnd() {
			return end;
		}

		/**权重名称*/
		public String getWeightName() {
			return weightName;
		}

		/**权重值*/
		public int getWeightValue() {
			return weightValue;
		}
	}

}
