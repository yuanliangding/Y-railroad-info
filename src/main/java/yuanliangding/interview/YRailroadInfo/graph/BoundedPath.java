package yuanliangding.interview.YRailroadInfo.graph;

import yuanliangding.interview.YRailroadInfo.graph.GraphDatum.Vertex;

/** 
 * @ClassName: BoundedPath
 * @Description:  权重总值受限的路线.权重总值被指定了最大值和最小值.
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
	 * @param dim	描述针对具体维度的权重
	 * @param min	权重总值最小值(包含该值)
	 * @param max	权重总值最大值(包含该值)
	 */
	public BoundedPath(Vertex begin, Vertex end, String dim, int min, int max) {
		super(begin, end, dim);
		
		if (min > max) {
			throw new RuntimeException("最小值大小不能超过最大值");
		}
		
		this.min = min;
		this.max = max;
	}
	
	/**
	 * @param begin				路线起点
	 * @param end				路线终点(传null为不指定终点)
	 * @param dim				描述针对具体维度的权重
	 * @param min				权重总值最小值
	 * @param max				权重总值最大值
	 * @param minContainsEq	权重总值最小值是否包含该值
	 * @param maxContainsEq	权重总值最大值是否包含该值
	 */
	public BoundedPath(Vertex begin, Vertex end, String dim, int min, int max, boolean minContainsEq, boolean maxContainsEq) {
		this(begin, end, dim, min, max);
		
		this.minContainsEq = minContainsEq;
		this.maxContainsEq = maxContainsEq;
	}
	
	// TODO 对于权重有负值,或者遍历过程中,权重总值不是单调的.让该方法永远返回true既可
	@Override
	protected boolean toBeContinue(Step step) {
		return maxContainsEq?step.getTotalWeight() <= max:step.getTotalWeight() < max;
	}
	
	@Override
	protected void asResult(Step step) {
		if (
				(maxContainsEq?step.getTotalWeight() <= max:step.getTotalWeight() < max) 
				&& 
				(minContainsEq? step.getTotalWeight() >= min: step.getTotalWeight() > min)) {
			if (end == null || step.getCurr().equals(end)) {
				results.add(step);
			}
		}
	}

}
