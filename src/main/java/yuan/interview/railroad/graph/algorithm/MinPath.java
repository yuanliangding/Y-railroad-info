package yuan.interview.railroad.graph.algorithm;

import java.util.HashMap;
import java.util.Map;

import yuan.interview.railroad.graph.base.Vertex;

/** 
 * @ClassName: MinPath
 * @Description:  权重总值最小的路径(在某个维度上)
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-下午2:00:18
 */
public class MinPath extends CriterionPath {
	
	private Map<Vertex, Integer> totalWeights = new HashMap<>();

	/**
	 * @param begin	路线起点
	 * @param end	路线终点
	 * @param dim	权重计算所在的维度
	 */
	public MinPath(Vertex begin, Vertex end, String dim) {
		super(begin, end, dim);
	}
	
	@Override
	protected void clear() {
		super.clear();
		totalWeights.clear();
	}
	
	@Override
	protected boolean toBeContinue(Step step) {
		boolean result = true;
		
		Vertex curr = step.getCurrent();
		if (totalWeights.containsKey(curr)) {
			int lastTotalWeight = totalWeights.get(curr);
			
			if (lastTotalWeight < step.getTotalWeight()) {
				result = false;
			} else {
				// XXX: 这里可以优化：当在遍历到某个节点时，有两条线路到这里的总权重是一样的。假设继续往后遍历就可以得到最优解。则后续的遍历操作，只进行一次就行。不需要两条路径都继续遍历
				totalWeights.put(curr, step.getTotalWeight());
			}
		} else {
			totalWeights.put(curr, step.getTotalWeight());
		}
		
		// XXX: 对于权重不会为负值。遍历已经到最终结点。就没有必要继续下去了.再绕一圈,总权重不可能变小
		if (step.getCurrent().equals(end)) {
			result = false;
		}
		
		return result;
	}
	
	@Override
	protected void forResult(Step step) {
		if (step.getCurrent().equals(end)) {
			if (results.isEmpty()) {
				results.add(step);
			} else {
				if (results.get(0).getTotalWeight() > step.getTotalWeight()) {
					results.clear();
					results.add(step);
				} else if (results.get(0).getTotalWeight() == step.getTotalWeight()) {
					results.add(step);
				}
			}
		}
	}

}
