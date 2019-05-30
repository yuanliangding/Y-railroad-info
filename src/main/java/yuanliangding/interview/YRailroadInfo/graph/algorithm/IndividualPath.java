package yuanliangding.interview.YRailroadInfo.graph.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import yuanliangding.interview.YRailroadInfo.graph.base.Path;
import yuanliangding.interview.YRailroadInfo.graph.base.Vertex;

/** 
 * @ClassName: IndividualPath
 * @Description:  具体路线,起点,终点,途中经停的点都是已经确定的.
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午2:22:00
 */
public class IndividualPath extends Path {
	
	private List<Vertex> rest = new ArrayList<>();

	/**
	 * @param begin 路线起点
	 *   		,.. 	剩下的点(按顺序)
	 */
	public IndividualPath(Vertex begin, Vertex ...others) {
		super(begin);
		
		for (int index=0;index<others.length;++index) {
			rest.add(others[index]);
		}
	}
	
	/**
	 * @param vertexs 途经的所有站点(按顺序)
	 */
	public IndividualPath(List<Vertex> vertexs) {
		super(vertexs!=null && vertexs.size()>0?vertexs.get(0):null);
		
		for (int i=1;i<vertexs.size();++i) {
			rest.add(vertexs.get(i));
		}
	}
	
	@Override
	public int getTotalWeight(String dim) {
		int result = 0;
		
		Vertex curr = begin;
		
		for (Vertex next:rest) {
			result += getWeight(curr, next, dim);
			curr = next;
		}
		
		return result;
	}
	
	private int getWeight (Vertex curr, Vertex next, String dim) {
		Integer weight = curr.getEdges(dim).get(next);
		if (weight == null) {
			throw new RuntimeException("NO SUCH ROUTE");
		}
		return weight;
	}

	@Override
	public String toString() {
		return begin.getName() + rest.stream().map(s -> "-" +s.getName()).collect(Collectors.joining(""));
	}
	
}
