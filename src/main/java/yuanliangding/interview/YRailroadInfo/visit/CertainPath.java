package yuanliangding.interview.YRailroadInfo.visit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import yuanliangding.interview.YRailroadInfo.map.Stop;

/** 
 * @ClassName: CertainPath
 * @Description:  具体路线,起点,终点,途中经停的点都是已经确定的.
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午2:22:00
 */
public class CertainPath extends AbsPath {
	
	private List<Stop> via = new ArrayList<>();

	/**
	 * @param begin 路线起点
	 * @param end	路线终点
	 *   		,.. 	路线中间途经的点(按顺序)
	 */
	public CertainPath(Stop begin, Stop ...others) {
		super(begin, others.length==0?begin:others[others.length-1]);
		
		for (int index=0;index<others.length-1;++index) {
			via.add(others[index]);
		}
	}
	
	public CertainPath(List<Stop> stops) {
		super(
				stops!=null && stops.size()>0?stops.get(0):null,
				stops!=null && stops.size()>0?stops.get(stops.size()-1):null
				);
		
		for (int i=1;i<stops.size()-1;++i) {
			via.add(stops.get(i));
		}
	}
	
	@Override
	public int getTotalWeight(String dim) {
		int result = 0;
		
		Stop curr = begin;
		
		for (Stop next:via) {
			result += getWeight(curr, next, dim);
			curr = next;
		}
		
		result += getWeight(curr, end, dim);
	
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
		return begin.getName() + "-" +  via.stream().map(s -> s.getName()+"-").collect(Collectors.joining("")) + end.getName();
	}

	/**
	 *  具体路现不需要再具体化了,具体化结果就是它自身.
	 * */
	@Override
	public List<CertainPath> concrete() {
		return Arrays.asList(this);
	}
	
}
