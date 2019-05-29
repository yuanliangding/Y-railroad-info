package yuanliangding.interview.YRailroadInfo.map.simple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import yuanliangding.interview.YRailroadInfo.graph.BoundedPath;
import yuanliangding.interview.YRailroadInfo.graph.GraphDatum;
import yuanliangding.interview.YRailroadInfo.graph.GraphDatum.Vertex;
import yuanliangding.interview.YRailroadInfo.graph.GraphReader;
import yuanliangding.interview.YRailroadInfo.graph.IndividualPath;
import yuanliangding.interview.YRailroadInfo.graph.MinPath;
import yuanliangding.interview.YRailroadInfo.graph.SpecifiedPath;
import yuanliangding.interview.YRailroadInfo.interactive.Command;
import yuanliangding.interview.YRailroadInfo.interactive.CommandParser.CommandData;
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
		graphReader.read().forEach(e -> {
				Vertex startStop = map.getVertex(e.getStart());
				Vertex endStop = map.getVertex(e.getEnd());
				map.addEdge(startStop, endStop, Weight.DIST.name(), e.getWeightValue());
				map.addEdge(startStop, endStop, Weight.STOP.name(), 1);
			}
		);
	}
	
	/**
	 * 该策略,提供的命令有:
	 * 		dist	-p A-B-C		求从A到B然后到C的路程
	 * 		count	-f s -m 3 -M 5 -b A -e B
	 * 								求从A到B的路径数量,路径跨度约束在3(包括)到5(包括)之间.
	 * 		dist	-f md -b A -e B
	 * 								求从A到B的最短路径长度
	 * 		count	-f d -m 31 -nM 50 -b A -e B
	 * 								求从A到B的路径数量,路程约束在31(包括)到50(不包括)之间.
	 */
	@Override
	public Map<String, Command> getCommands() {
		Map<String, Command> result = new HashMap<>();
		
		result.put("help", cd -> {
			return getMan();
		});
		
		result.put("dist", cd -> {
			List<IndividualPath> paths = calcPath(cd);
			if (paths == null || paths.size() == 0) {
				throw new RuntimeException("NO SUCH ROUTE");
			}
			return String.valueOf(paths.get(0).getTotalWeight(Weight.DIST.name()));
		});
		
		result.put("count", cd -> {
			List<IndividualPath> paths = calcPath(cd);
			return paths.size();
		});
		
		return result;
	}
	
	protected List<IndividualPath> calcPath(CommandData commandData) {
		Map<String, String> options = commandData.getOptions();
		if (options == null || options.isEmpty()) {
			throw new RuntimeException("执行错误,请输入必要的选项参考.");
		}
		
		String path = options.get("p");
		if (path != null) {
			if("".equals(path)) {
				throw new RuntimeException("执行错误.请在-p后附上合法的路径信息.比如 A-B-C 求从A经停B最终到C");
			}
			List<Vertex> vertexs = Stream.of(path.split("-"))
					.map(map::getVertex)
					.collect(Collectors.toList());
			IndividualPath individualPath = new IndividualPath(vertexs);
			
			return Arrays.asList(individualPath);
		} else {
			String focus = options.get("f");
			if (focus == null || "".equals(focus)) {
				throw new RuntimeException("执行错误.请在-p或者-f参数,必须指定一个.");
			}
			
			String begin = options.get("b");
			if (begin == null || "".equals(begin)) {
				throw new RuntimeException("执行错误.指定了-f参数,必须指定一个-b路径起点参数.");
			}
			Vertex beginVertex = map.getVertex(begin);
			
			Vertex endVertex = null;
			String end = options.get("e");
			if (end != null && !"".equals(end)) {
				endVertex = map.getVertex(end);
			} else {
				// TODO 这里可以不报错,计算到所有可达点的路径(去掉这里的else就可以)
				throw new RuntimeException("执行错误.指定了-f参数,必须指定一个-e路径终点参数.");
			}
			
			SpecifiedPath specifiedPath = null;
			
			SimpleMapPolicy.Weight weight = null;
			
			switch(focus) {
				case "d":
					weight = SimpleMapPolicy.Weight.DIST;
				case "s":
					if (weight == null) {
						weight = SimpleMapPolicy.Weight.STOP;
					}
					
					String max = options.get("M");
					if (max == null || "".equals(max)) {
						throw new RuntimeException("执行错误.指定了-f s或者-f d参数,必须指定-M参数.");
					}
					
					boolean maxContainsEq = true;
					if (max.startsWith("n")) {
						maxContainsEq = false;
						max = max.substring(1);
					}
					
					int maxV = Integer.parseInt(max);
					
					String min = options.get("m");
					int minV = 0;
					boolean minContainsEq = true;
					if (min != null && !"".equals(min)) {
						if (min.startsWith("n")) {
							minContainsEq = false;
							min = min.substring(1);
						}
						
						minV = Integer.parseInt(min);
					}
					
					specifiedPath = new BoundedPath(beginVertex, endVertex, weight.name(), minV, maxV, minContainsEq, maxContainsEq);
					break;
				case "md":
					weight = SimpleMapPolicy.Weight.DIST;
				case "ms":
					if (weight == null) {
						weight = SimpleMapPolicy.Weight.STOP;
					}
					
					specifiedPath = new MinPath(beginVertex, endVertex, weight.name());
					break;
				default:
					throw new RuntimeException("执行错误.请在-f参数的有效值可以是:d,s,md,ms.");
				}
			
				return specifiedPath.concrete();
		}
	}
	
	/**该策略提供给用户的命令操作手册*/
	private String getMan() {
		String man = 
				"您可以使用的,总共有3条命令:help,dist,count.\n" + 
				"\n" + 
				"help	显示该参考手册\n" + 
				"dist	显示里程数值\n" + 
				"count	显示路线数量\n" + 
				"\n" + 
				"以上3条命令,共用一些参数选项,具体参数说明如下.\n" + 
				"\n" + 
				"-p		path,指定具体路径.比如命令 dist -p A-B-C-D,显示从A经过B,再经过C,最终到D的总路程.指定具体路径必须是这样的格式:A-B-C-D\n" + 
				"-f		focus,如果不能确定具体路径.也可能只指定路径特点需要关注的是什么.这个选项的值有:\n" + 
				"			d	distance,路程;\n" + 
				"			s	stop,经停次数;\n" + 
				"			md	min distance,最短路径\n" + 
				"			ms	min stop,最少经停\n" + 
				"-m		min,对于以上的-f选项,如果指定的是d(路程),则还需要通过-m选项指定路径最小值.\n" + 
				"		比如-m 3表示路程至少3(包括3),-m n3表示路程至少3(不包括3)\n" + 
				"		如果以上的-f选项,指定的是s(经停次数),则也需要通过-m选项指定路径最小值.\n" + 
				"		比如-m 3表示经停次数至少3次(包括3次),-m n3表示经停次数至少3次(不包括3次)\n" + 
				"		如果没指定该-m选项,则系统帮您指定为0\n" + 
				"-M		max,对于以上的-f选项,如果指定的是d(路程),则还需要通过-M选项指定路径最小值.\n" + 
				"		比如-M 8表示路程至少8(包括8),-M n8表示路程至少8(不包括8)\n" + 
				"		如果以上的-f选项,指定的是s(经停次数),则也需要通过-M选项指定路径最小值.\n" + 
				"		比如-M 8表示经停次数至少8次(包括8次),-M n8表示经停次数至少8次(不包括8次)\n" + 
				"-b		begin,对于以上的-f选项,指定了路径的特征要求,还需要通过-b选项,指定路径的起点.\n" + 
				"		比如 -b A 表示路径以A为起点.\n" + 
				"-e		end,对于以上的-f选项,指定了路径的特征要求,还需要通过-e选项,指定路径的终点.\n" + 
				"		比如 -e B 表示路径以B为终点.\n" + 
				"\n" + 
				"例子:\n" + 
				"	dist	-p A-B-C\n" + 
				"	求从A到B然后到C的路程\n" + 
				"\n" + 
				"	count	-f s -m 3 -M 5 -b A -e B\n" + 
				"	求从A到B的路径数量,路径跨度约束在3(包括)到5(包括)之间.\n" + 
				"\n" + 
				"	dist	-f md -b A -e B\n" + 
				"	求从A到B的最短路径长度\n" + 
				"\n" + 
				"	count	-f d -m 31 -M n50 -b A -e B\n" + 
				"	求从A到B的路径数量,路程约束在31(包括)到50(不包括)之间.";
		return man;
	}
	
	/** 权重名 */
	public static enum Weight {
		DIST,	//距离
		STOP	//跨越站数
	}
	
}
