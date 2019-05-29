package yuanliangding.interview.YRailroadInfo.map.simple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import yuanliangding.interview.YRailroadInfo.graph.GraphDatum;
import yuanliangding.interview.YRailroadInfo.graph.GraphReader;
import yuanliangding.interview.YRailroadInfo.graph.IndividualPath;
import yuanliangding.interview.YRailroadInfo.graph.GraphDatum.Vertex;
import yuanliangding.interview.YRailroadInfo.interactive.Command;
import yuanliangding.interview.YRailroadInfo.map.MapPolicy;

/** 
 * @ClassName: SimpleMapPolicy
 * @Description:  简单地图策略
 * 						维护两个维度的信息.1 dist维护的边权重,2 stop维护的边权重
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-上午6:28:46
 */
public class SimpleMapPolicy implements MapPolicy<Command, SimpleMapPolicy.Weight> {
	
	private GraphDatum map = new GraphDatum();
	
	private static SimpleMapPolicy instance = new SimpleMapPolicy();

	public static SimpleMapPolicy getInstance() {
		return instance;
	}

	private SimpleMapPolicy() {}
	
	@Override
	public void setGraphReader(GraphReader graphReader) {
		graphReader.read().forEach(e -> addRoute(e.getStart(),e.getEnd(),null,e.getWeightValue()));
	}
	
	/**
	 * 一般只会传起点,终点和这之间有向边的权重值.在该策略中,既表示dist=weight,stop=1
	 * @param start		起点
	 * @param end		终点
	 * @param weight	(这个参数,在该策略中不需要)
	 * @param value		权重值
	 */
	private void addRoute(String start, String end, Weight weight, int value) {
		Vertex startStop = map.getVertex(start);
		Vertex endStop = map.getVertex(end);
		map.addEdge(startStop, endStop, Weight.DIST.name(), value);
		map.addEdge(startStop, endStop, Weight.STOP.name(), 1);
	}
	
	/**
	 * 该策略,提供的命令有:
	 * 		dist	-p A-B-C		求从A到B然后到C的路程
	 * 		num		-s limited -m 3 -M 5 -b A -e B
	 * 								求从A到B的路径数量,路径跨度约束在3(包括)到5(包括)之间.
	 * 		dist	-s shortest -b A -e B
	 * 								求从A到B的最短路径长度
	 * 		num		-s far -m 31 -nM 50 -b A -e B
	 * 								求从A到B的路径数量,路程约束在31(包括)到50(不包括)之间.
	 */
	@Override
	public Map<String, Command> getCommands() {
		Map<String, Command> result = new HashMap<>();
		
		result.put("dist", cd -> {
			String arg = cd.getOptions().get("p");
			if(arg == null || "".equals(arg)) {
				throw new RuntimeException("请在-p后附上路径信息.命令格式:dist -p A-B-C 求从A到B然后到C的路程");
			}
			List<Vertex> vertexs = Stream.of(arg.split("-"))
					.map(map::getVertex)
					.collect(Collectors.toList());
			IndividualPath individualPath = new IndividualPath(vertexs);
			return individualPath.getTotalWeight(Weight.DIST.name());
		});
		
		result.put("num", cd -> {
			
			
			
			return null;
		});
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see yuanliangding.interview.YRailroadInfo.graph.MapPolicy#man()
	 */
	@Override
	public String man() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/** 权重名 */
	public static enum Weight {
		DIST,	//距离
		STOP	//跨越站数
	}
	
}
