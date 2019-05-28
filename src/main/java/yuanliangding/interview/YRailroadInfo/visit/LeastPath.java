package yuanliangding.interview.YRailroadInfo.visit;

import java.util.HashMap;
import java.util.Map;

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
	
	private Map<Stop, Integer> tempPaths = new HashMap<>();

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
		super.clear();
		tempPaths.clear();
	}
	
	@Override
	protected boolean toBeContinue(TempPath tempPath) {
		boolean result = true;
		
		Stop curr = tempPath.getCurr();
		if (tempPaths.containsKey(curr)) {
			int lastTotalWeight = tempPaths.get(curr);
			
			if (lastTotalWeight < tempPath.getTotalWeight()) {
				result = false;
			} else {
				// TODO 这里可以优化:当在遍历到某个节点时,有两条线路到这里的总权重是一样的,假设继续往后遍历就可以得到最优解.则后续的遍历操作,只进行一次就行.不需要两条路径都继续遍历
				tempPaths.put(curr, tempPath.getTotalWeight());
			}
		} else {
			tempPaths.put(curr, tempPath.getTotalWeight());
		}
		
		// TODO 对于权重不会为负值,遍历已经到最终结点,就没有必要继续下去了.再绕一圈,总权重不可能变小.
		if (tempPath.getCurr().equals(end)) {
			result = false;
		}
		
		return result;
	}
	
	@Override
	protected void asResult(TempPath tempPath) {
		if (tempPath.getCurr().equals(end)) {
			if (results.isEmpty()) {
				results.add(tempPath);
			} else {
				if (results.get(0).getTotalWeight() > tempPath.getTotalWeight()) {
					results.clear();
					results.add(tempPath);
				} else if (results.get(0).getTotalWeight() == tempPath.getTotalWeight()) {
					results.add(tempPath);
				}
			}
		}
	}

}
