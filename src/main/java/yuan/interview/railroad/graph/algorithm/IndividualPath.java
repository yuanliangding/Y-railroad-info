package yuan.interview.railroad.graph.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import yuan.interview.railroad.exception.GraphException;
import yuan.interview.railroad.graph.base.Path;
import yuan.interview.railroad.graph.base.Vertex;

/** 
 * @ClassName: IndividualPath
 * @Description:  具体路线、起点、终点、途中经停的点都是已经确定的
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午2:22:00
 */
public class IndividualPath extends Path {
	
	private List<Vertex> rest = new ArrayList<>();

	/**
	 * @param begin 		路线起点
	 * @param others		剩下的点(按顺序)
	 */
	public IndividualPath(Vertex begin, Vertex ...others) {
		super(begin);
		
		Stream.of(others).forEach(rest::add);
	}
	
	/**
	 * @param vertexs 路径上所有的点(按顺序)
	 */
	public IndividualPath(List<Vertex> vertexs) {
		super(vertexs != null && vertexs.size() > 0? vertexs.get(0): null);
		
		vertexs.subList(1, vertexs.size()).forEach(rest::add);
	}
	
	@Override
	public int getTotalWeight(String dim) {
		List<Vertex> vertexs = Stream.concat(
													Stream.of(begin), 
													rest.stream()
												).collect(
													Collectors.toList()
												);
		
		int totalWeight = IntStream.rangeClosed(0, vertexs.size()-2).boxed()
				.map(i -> getWeight(vertexs.get(i), vertexs.get(i+1), dim))
				.reduce(Integer::sum).get();
		
		return totalWeight;
	}
	
	private int getWeight (Vertex start, Vertex end, String dim) {
		if (!start.getEdges().containsKey(end)) {
			throw new GraphException("NO SUCH ROUTE");
		}
		
		Map<String, Integer> weights = start.getEdges().get(end);
		if (!weights.containsKey(dim)) {
			throw new GraphException("没有" + dim + "维度的权重值");
		}
		return weights.get(dim);
	}

	@Override
	public String toString() {
		return begin.getName() + rest.stream().map(s -> "-" +s.getName()).collect(Collectors.joining(""));
	}
	
}
