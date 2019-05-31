package yuan.interview.railroad.graph.algorithm;

import yuan.interview.railroad.exception.GraphException;
import yuan.interview.railroad.graph.base.Vertex;

/** 
 * @ClassName: BoundedPath
 * @Description:  权重总值处于某个数值区间,权重总值被指定了最大值和最小值.
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午9:24:12
 */
public class BoundedPath extends SpecifiedPath {
	
	private int min;
	private int max;
	private boolean minContainsEq = true;
	private boolean maxContainsEq = true;
	
	/**
	 * @param begin	路线起点
	 * @param end	路线终点(传null为不指定终点)
	 * @param dim	在具体维度上进行权重计算
	 * @param min	权重总值最小值(包含该值)
	 * @param max	权重总值最大值(包含该值)
	 */
	public BoundedPath(Vertex begin, Vertex end, String dim, int min, int max) {
		super(begin, end, dim);
		
		if (min > max) {
			throw new GraphException("最小值不能超过最大值");
		}
		
		this.min = min;
		this.max = max;
	}
	
	/**
	 * @param begin				路线起点
	 * @param end				路线终点(传null为不指定终点)
	 * @param dim				在具体维度上进行权重计算
	 * @param min				权重总值最小值
	 * @param max				权重总值最大值
	 * @param minContainsEq	权重总值最小值端是否闭区间
	 * @param maxContainsEq	权重总值最大值端是否闭区间
	 */
	public BoundedPath(Vertex begin, Vertex end, String dim, int min, int max, boolean minContainsEq, boolean maxContainsEq) {
		this(begin, end, dim, min, max);
		
		this.minContainsEq = minContainsEq;
		this.maxContainsEq = maxContainsEq;
	}
	
	// TODO 边的权重如果有负值,需要让返回值永远为true才能保证不会漏掉结果.
	@Override
	protected boolean toBeContinue(Step step) {
		return maxContainsEq? step.getTotalWeight() <= max: step.getTotalWeight() < max;
	}
	
	@Override
	protected void asResult(Step step) {
		if ((maxContainsEq?step.getTotalWeight() <= max:step.getTotalWeight() < max) 
			&& 
			(minContainsEq? step.getTotalWeight() >= min: step.getTotalWeight() > min)) {
			if (end == null || step.getCurrent().equals(end)) {
				results.add(step);
			}
		}
	}

}
