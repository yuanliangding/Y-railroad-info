package yuanliangding.interview.YRailroadInfo.visit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import yuanliangding.interview.YRailroadInfo.map.Stop;

/** 
 * @ClassName: LimitedPath
 * @Description:  权重总值受限的路线.权重总值被指定了最大值和最小值.
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午9:24:12
 */
public class LimitedPath extends SpecifiedPath {
	
	private int min;
	private int max;
	private boolean minContainsEq = true;
	private boolean maxContainsEq = true;
	
	private Collection<TempPath> tempResult = new ArrayList<>();
	
	/**
	 * @param begin	路线起点
	 * @param end	路线终点
	 * @param dim	描述针对具体维度的权重
	 * @param min	权重总值最小值(包含该值)
	 * @param max	权重总值最大值(包含该值)
	 */
	public LimitedPath(Stop begin, Stop end, String dim, int min, int max) {
		super(begin, end, dim);
		
		if (min > max) {
			throw new RuntimeException("最小值大小不能超过最大值");
		}
		
		this.min = min;
		this.max = max;
	}
	
	/**
	 * @param begin				路线起点
	 * @param end				路线终点
	 * @param dim				描述针对具体维度的权重
	 * @param min				权重总值最小值
	 * @param max				权重总值最大值
	 * @param minContainsEq	权重总值最小值是否包含该值
	 * @param maxContainsEq	权重总值最大值是否包含该值
	 */
	public LimitedPath(Stop begin, Stop end, String dim, int min, int max, boolean minContainsEq, boolean maxContainsEq) {
		this(begin, end, dim, min, max);
		
		this.minContainsEq = minContainsEq;
		this.maxContainsEq = maxContainsEq;
	}
	
	/**
	 * 清除上次计算的结果
	 * */
	@Override
	protected void clear() {
		tempResult.clear();
	}

	/**
	 * TODO 对于路途中有负环路,或0环路.该递归会死循环.对于规模大的地图,且max也比较大.该递归可能会导致内存不足的问题.
	 * */
	@Override
	protected void nextStop(Stop curr, TempPath currTempPath) {
		curr.getNexts(dim).forEach((Stop stop,Integer weight) -> {
			TempPath tempPath = new TempPath(currTempPath.getTotalWeight()+weight, stop, currTempPath);
			if (toBeContinue(tempPath)) {
				saveAsResult(tempPath);
				nextStop(stop, tempPath);
			}
		});
	}
	
	/**
	 * 是否要继续遍历后续节点.
	 * */
	protected boolean toBeContinue(TempPath tempPath) {
		return maxContainsEq?tempPath.getTotalWeight() <= max:tempPath.getTotalWeight() < max;
	}
	
	/**
	 * 当前节点的路经,是否可以做为最终结果.
	 * */
	protected void saveAsResult(TempPath tempPath) {
		if (tempPath.getCurr().equals(end) 
				&& (minContainsEq?tempPath.getTotalWeight() >= min:tempPath.getTotalWeight() > min)) {
			tempResult.add(tempPath);
		}
	}
	
	@Override
	protected List<IndividualPath> getResult() {
		return
				tempResult.stream().map(tempPath -> {
					List<Stop> tempList = new LinkedList<>();
					
					do{
						tempList.add(0, tempPath.getCurr());
						tempPath = tempPath.getPrevious();
					}while(tempPath != null);
					
					return tempList;
				}).map(stopList -> new IndividualPath(stopList))
				.collect(Collectors.toList());
	}

}
