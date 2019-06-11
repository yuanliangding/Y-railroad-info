package yuan.interview.railroad.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import yuan.interview.railroad.exception.YRailroadException;
import yuan.interview.railroad.graph.algorithm.BoundedPath;
import yuan.interview.railroad.graph.algorithm.IndividualPath;
import yuan.interview.railroad.graph.algorithm.MinPath;
import yuan.interview.railroad.graph.base.Graph;
import yuan.interview.railroad.graph.base.Vertex;
import yuan.interview.railroad.graph.policy.GraphPolicy;
import yuan.interview.railroad.interactive.Command;

/** 
 * @ClassName: GeneralGraphPolicy
 * @Description:  图策略基础实现
 * 
 *  					提供三个维度的权重。1 DIST 2 STOP 3 TIME 由{@link Weight枚举提供}。
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-上午6:28:46
 */
public abstract class GeneralGraphPolicy implements GraphPolicy<Command, GeneralGraphPolicy.Weight> {
	
	protected Graph map = new Graph();
	
	/**
	 * 该图策略提供的命令，具体信息见下面的getMan方法提供的命令操作手册
	 */
	@Override
	public Map<String, Command> getCommands() {
		Map<String, Command> result = new HashMap<>();
		
		result.put("help", cd -> {
			return getMan();
		});
		
		result.put("dist", cd -> {
			List<IndividualPath> paths = getResultSet(cd.getOptions());
			if (paths == null || paths.size() == 0) {
				throw new YRailroadException("NO SUCH ROUTE");
			}
			return paths.get(0).getTotalWeight(Weight.DIST.name());
		});
		
		result.put("count", cd -> {
			List<IndividualPath> paths = getResultSet(cd.getOptions());
			return paths.size();
		});
		
		return result;
	}
	
	public List<IndividualPath> getResultSet(Map<String, String> options) {
		if (options == null || options.isEmpty()) {
			throw new YRailroadException("执行错误,请输入必要的选项参考");
		}
		
		String path = options.get("p");
		if (path != null) {
			return createIndividualWithP(path);
		} else {
			String focus = options.get("f");
			if (focus == null || "".equals(focus)) {
				throw new YRailroadException("执行错误。请在-p或者-f参数，必须指定一个.");
			}
			
			Vertex beginVertex = getVertexByBorE(options.get("b"));
			if (beginVertex == null) {
				throw new YRailroadException("执行错误。指定了-f参数，必须指定一个-b路径起点参数.");
			}
			
			Vertex endVertex = getVertexByBorE(options.get("e"));
			if (endVertex == null) {
				// XXX: 这里可以不报错,计算到所有可达点的路径(去掉这里的else就可以)
				throw new YRailroadException("执行错误。指定了-f参数，必须指定一个-e路径终点参数.");
			}
			
			final Pattern focusPattern = Pattern.compile("(m?)(s|d|t)");
			Matcher focusMatcher = focusPattern.matcher(focus);
			if(!focusMatcher.matches()) {
				throw new YRailroadException("执行错误。请在-f参数的有效值可以是：d、s、t、md、ms、mt");
			}
			
			String isMin = focusMatcher.group(1);
			String weightTag = focusMatcher.group(2);
			
			GeneralGraphPolicy.Weight weight = null;
			if ("d".equals(weightTag)) {
				weight = GeneralGraphPolicy.Weight.DIST;
			} else if ("s".equals(weightTag)) {
				weight = GeneralGraphPolicy.Weight.STOP;
			} else {
				weight = GeneralGraphPolicy.Weight.TIME;
			}
			
			if ("".equals(isMin)) {
				return createBoundedPathByOptions(beginVertex, endVertex, weight.name(), options)
						.search();
			} else {
				return createMinPathByOptions(beginVertex, endVertex, weight.name())
						.search();
			}
		}
	}
	
	private List<IndividualPath> createIndividualWithP(String path) {
		if("".equals(path)) {
			throw new YRailroadException("执行错误，请在-p后附上合法的路径信息。比如 A-B-C 求从A经停B最终到C");
		}
		List<Vertex> vertexs = Stream.of(path.split("-"))
				.map(map::getVertex)
				.collect(Collectors.toList());
		IndividualPath individualPath = new IndividualPath(vertexs);
		
		return Arrays.asList(individualPath);
	}
	
	private Vertex getVertexByBorE(String vName) {
		if (vName != null && !"".equals(vName)) {
			return map.getVertex(vName);
		} else {
			return null;
		}
	}
	
	private BoundedPath createBoundedPathByOptions(Vertex begin, Vertex end, String dim, Map<String, String> otherOptions) {
		String max = otherOptions.get("M");
		if (max == null || "".equals(max)) {
			throw new YRailroadException("执行错误。指定了-f s或者-f d参数，必须指定-M参数");
		}
		
		boolean maxContainsEq = true;
		if (max.startsWith("n")) {
			maxContainsEq = false;
			max = max.substring(1);
		}
		
		int maxV = Integer.parseInt(max);
		
		String min = otherOptions.get("m");
		int minV = 0;
		boolean minContainsEq = true;
		if (min != null && !"".equals(min)) {
			if (min.startsWith("n")) {
				minContainsEq = false;
				min = min.substring(1);
			}
			
			minV = Integer.parseInt(min);
		}
		
		return new BoundedPath(begin, end, dim, minV, maxV, minContainsEq, maxContainsEq);
	}
	
	private MinPath createMinPathByOptions(Vertex begin, Vertex end, String dim) {
		return new MinPath(begin, end, dim);
	}
	
	/**该策略提供给用户的命令操作手册(命令格式采用unix风格)*/
	private String getMan() {
		String man = 
				"您可以使用的。总共有3条命令：help、dist、count\n" + 
				"\n" + 
				"help	显示该参考手册\n" + 
				"dist	显示里程数值\n" + 
				"count	显示路线数量\n" + 
				"\n" + 
				"以上3条命令,共用一些参数选项,具体参数说明如下。\n" + 
				"\n" + 
				"-p		path，指定具体路径.比如命令 dist -p A-B-C-D,显示从A经过B，再经过C,最终到D的总路程。指定具体路径必须是这样的格式：A-B-C-D\n" + 
				"-f		focus，如果不能确定具体路径.也可能只指定路径特点需要关注的是什么.这个选项的值有：\n" + 
				"			d	distance，路程\n" + 
				"			s	stop，经停次数\n" + 
				"			t	time，路程耗时\n" + 
				"			md	min distance，最短路径\n" + 
				"			ms	min stop，最少经停\n" + 
				"			mt	min time，最少耗时\n" + 
				"-m		min，对于以上的-f选项。如果指定的是d(路程)，则还需要通过-m选项指定路径最小值。\n" + 
				"		比如-m 3表示路程至少3(包括3)，-m n3表示路程至少3(不包括3)\n" + 
				"		如果以上的-f选项。指定的是s(经停次数)，则也需要通过-m选项指定路径最小值。\n" + 
				"		比如-m 3表示经停次数至少3次(包括3次)，-m n3表示经停次数至少3次(不包括3次)\n" + 
				"		如果以上的-f选项。指定的是t(耗时)，则也需要通过-m选项指定最节约耗时。\n" + 
				"		比如-m 30表示用时至少30(包括30)，-m n3表示耗时至少30(不包括30)\n" + 
				"		如果没指定该-m选项，则系统帮您指定为0\n" + 
				"-M		max，对于以上的-f选项。如果指定的是d(路程)，则还需要通过-M选项指定路径最小值。\n" + 
				"		比如-M 8表示路程至少8(包括8)。-M n8表示路程至少8(不包括8)\n" + 
				"		如果以上的-f选项。指定的是s(经停次数)，则也需要通过-M选项指定路径最小值。\n" + 
				"		比如-M 8表示经停次数至少8次(包括8次),-M n8表示经停次数至少8次(不包括8次)\n" + 
				"		如果以上的-f选项。指定的是t(耗时)，则也需要通过-M选项指定耗时最小值。\n" + 
				"		比如-M 8表示耗时至少8(包括8),-M n8表示耗时至少8(不包括8)\n" + 
				"-b		begin，对于以上的-f选项。指定了路径的特征要求，还需要通过-b选项，指定路径的起点.\n" + 
				"		比如 -b A 表示路径以A为起点。\n" + 
				"-e		end，对于以上的-f选项。指定了路径的特征要求,还需要通过-e选项，指定路径的终点.\n" + 
				"		比如 -e B 表示路径以B为终点\n" + 
				"\n" + 
				"例子：\n" + 
				"	dist -p A-B-C\n" + 
				"	求从A到B然后到C的路程\n" + 
				"\n" + 
				"	count -f s -m 3 -M 5 -b A -e B\n" + 
				"	求从A到B的路径数量,路径跨度约束在3(包括)到5(包括)之间\n" + 
				"\n" + 
				"	dist -f md -b A -e B\n" + 
				"	求从A到B的最短路径长度\n" + 
				"\n" + 
				"	count -f d -m 31 -M n50 -b A -e B\n" + 
				"	求从A到B的路径数量,路程约束在31(包括)到50(不包括)之间";
		
		return man;
	}
	
	/** 权重维度 */
	public static enum Weight {
		DIST,	//距离
		STOP,	//跨越站数
		TIME	//耗时
	}
	
}
