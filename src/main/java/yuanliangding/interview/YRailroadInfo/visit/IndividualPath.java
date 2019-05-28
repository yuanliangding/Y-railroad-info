package yuanliangding.interview.YRailroadInfo.visit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import yuanliangding.interview.YRailroadInfo.map.Stop;

/** 
 * @ClassName: IndividualPath
 * @Description:  具体路线,起点,终点,途中经停的点都是已经确定的.
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午2:22:00
 */
public class IndividualPath extends AbsPath {
	
	private List<Stop> rest = new ArrayList<>();

	/**
	 * @param begin 路线起点
	 *   		,.. 	路线中间途经的点(按顺序)
	 */
	public IndividualPath(Stop begin, Stop ...others) {
		super(begin);
		
		for (int index=0;index<others.length;++index) {
			rest.add(others[index]);
		}
	}
	
	/**
	 * @param stops 途经的所有站点(按顺序)
	 */
	public IndividualPath(List<Stop> stops) {
		super(stops!=null && stops.size()>0?stops.get(0):null);
		
		for (int i=1;i<stops.size();++i) {
			rest.add(stops.get(i));
		}
	}
	
	@Override
	public int getTotalWeight(String dim) {
		int result = 0;
		
		Stop curr = begin;
		
		for (Stop next:rest) {
			result += getWeight(curr, next, dim);
			curr = next;
		}
		
		return result;
	}
	
	private int getWeight (Stop curr, Stop next, String dim) {
		Integer weight = curr.getNexts(dim).get(next);
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
