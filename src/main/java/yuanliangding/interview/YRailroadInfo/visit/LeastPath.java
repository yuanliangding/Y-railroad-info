package yuanliangding.interview.YRailroadInfo.visit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import yuanliangding.interview.YRailroadInfo.map.Stop;

/** 
 * @ClassName: LeastPath
 * @Description:  权重总值最小的路径.如果选择的维度是路程dist,则就是最短路径.
 * 						最短路径,其实是一个特殊的LimitedPath,
 * 						只是在具体化路径时,向后续节点遍历,
 * 						如果对于同一个结点,之前遍历过的路径是更优的,就中断当前分支的遍历
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-下午2:00:18
 */
public class LeastPath extends SpecifiedPath {
	
	private Map<Stop, List<TempPath>> tempPaths = new HashMap<>();

	/**
	 * @param begin	路线起点
	 * @param end	路线终点
	 * @param dim	描述针对具体维度的权重
	 */
	protected LeastPath(Stop begin, Stop end, String dim) {
		super(begin, end, dim);
	}
	
	@Override
	protected void clear() {
		tempPaths.clear();
	}
	
	/**
	 * 最具体化遍历地图时.如果目的是寻找最短路径(或者别的维度,找权重总值最小的路径)
	 * 在每个节点,都只记录到达当前结点的最短路径
	 * 这里假设路线中不会存在0环路或者负环路.当遍历到终点,就不再继续.
	 * */
	@Override
	protected boolean toBeContinue(TempPath tempPath) {
		boolean result = true;
		
		Stop curr = tempPath.getCurr();
		if (tempPaths.containsKey(curr)) {
			List<TempPath> lastTempPaths = tempPaths.get(curr);
			
			int lastTotalWeight = lastTempPaths.get(0).getTotalWeight();
			if (lastTotalWeight < tempPath.getTotalWeight()) {
				result = false;
			} else if (lastTotalWeight == tempPath.getTotalWeight()) {
				lastTempPaths.add(tempPath);
				result = false;				
			} else {
				lastTempPaths.clear();
				lastTempPaths.add(tempPath);
			}
		} else {
			List<TempPath> lastTempPaths = new ArrayList<>();
			lastTempPaths.add(tempPath);
			tempPaths.put(curr, lastTempPaths);
		}
		
		// TODO 对于权重不会为负值,遍历已经到最终结点,就没有必要继续下去了.再绕一圈,总权重不可能变小.
		if (tempPath.getCurr().equals(end)) {
			result = false;
		}
		
		return result;
	}
	
	/**
	 * 整理结果
	 * */
	@Override
	protected List<IndividualPath> getResult() {
		
		List<List<Stop>> tempResult = new ArrayList<>();
		
		tempPaths.remove(end).stream().forEach(tempPath -> {
			List<Stop> tempList = new LinkedList<>();
			tempResult.add(tempList);
			
			concatResult(tempList, tempPath, tempResult);
		});
		
		return tempResult.stream()
				.map(stopList -> new IndividualPath(stopList))
				.collect(Collectors.toList());
	}
	
	/**
	 * TODO 这里代码流程不够干净简洁
	 * */
	private void concatResult(List<Stop> resultList, TempPath tempPath, List<List<Stop>> tempResult) {
		Stop curr = tempPath.getCurr();
		List<TempPath> currTempPaths = tempPaths.get(curr);
		if (currTempPaths == null || currTempPaths.size() == 1) {
			resultList.add(0, curr);
			if (tempPath.getPrevious() != null) {
				concatResult(resultList, tempPath.getPrevious(), tempResult);
			}
		} else {
			tempResult.remove(resultList);
			currTempPaths.forEach(currTempPath -> {
				List<Stop> copyResultList = new ArrayList<>();
				Collections.copy(copyResultList, resultList);
				
				tempPaths.remove(curr);
				
				concatResult(copyResultList, currTempPath, tempResult);
			});
		}
	}

}
