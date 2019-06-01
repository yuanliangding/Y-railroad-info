package yuan.interview.railroad.graph.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import yuan.interview.railroad.exception.GraphException;
import yuan.interview.railroad.graph.base.Path;
import yuan.interview.railroad.graph.base.Vertex;

/** 
 * @ClassName: SpecifiedPath
 * @Description:  规约描述的路径.既具有某种特征的路径.
 * 						可以进行搜索操作(search),根据规约描述,从图中搜索出满足规约的所有路径.
 * 						受规约的路径可以再细分成更具体的规约,
 * 						比如最短路径,权重累加和满足一定数值范围的路径等,
 * 
 * 		注意:	SpecifiedPath类及其子类,在路线的计算过程中,会将中间结果保存在实例变量中,所以不是线程安全的.
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-上午9:04:04
 */
public abstract class SpecifiedPath extends Path {
	
	// 路径终点
	protected Vertex end;
	
	// 该规约路径在某个维度上进行计算(最短路径,或权重总值在某个区间)
	protected String dim;
	
	// 遍历过程产生的结果数据,搜索路径的中间结果.
	protected List<Step> results = new ArrayList<>();
	
	// 遍历路径到断头路,对于没有指定终点的路线,可以从这里处理结果
	private List<Step> terminates = new ArrayList<>();
	
	/**
	 * @param begin	路径起点
	 * @param end	路径终点(传null为不指定)
	 * @param dim	规约描述针对具体维度的权重
	 * */
	protected SpecifiedPath(Vertex begin, Vertex end, String dim) {
		super(begin);
		this.dim = dim;
		this.end = end;
	}
	
	/**
	 * 搜索操作.
	 * 根据规约描述,从地图中找到满足条件的具体路线,结果往往不只一条.
	 * 在指定维度计算权重分量的累加总值.最终筛选出满足条件的路线.
	 * 根据具体子类的实现,可以得到最短路径,耗时最少路径.或者权重累加和满足一定数值范围(途中可能会有环路)
	 * 
	 * TODO 由于遍历的时候,临时数据放在了实例变量中,所以该类及其所有子类搜索遍历操作不是线程安全的.
	 * 
	 * */
	public List<IndividualPath> search() {
		
		// 1 清理工作
		clear();
		
		// 2 进行遍历寻找
		traverse(new Step(0, begin, null));
		
		// 3 整理结果集
		return getResult();
	}
	
	protected void clear() {
		results.clear();
		terminates.clear();
	}
	
	/**
	 * TODO	1	对于路途中有环路,要注意退出条件,以免死循环.
	 * 			2	由于是采用递归操作,对于规模大的图有可能会导致内存不足.
	 * */
	private void traverse(Step currTempPath) {
		Map<Vertex, Map<String, Integer>> edges = currTempPath.getCurrent().getEdges();
		if (edges.isEmpty()) {
			terminates.add(currTempPath);
		}
		
		edges.forEach((Vertex vertex,Map<String, Integer> weights) -> {
			if (!weights.containsKey(dim)) {
				throw new GraphException("没有" + dim + "维度的权重值.");
			}
			
			Step step = new Step(currTempPath.getTotalWeight()+weights.get(dim), vertex, currTempPath);
			asResult(step);
			if (toBeContinue(step)) {
				traverse(step);
			}
		});
	}
	
	private List<IndividualPath> getResult() {
		
		List<Step> date = null;
		if (end == null) {
			date = terminates;
		} else {
			date = results;
		}
		
		return
				date.stream().map(tempPath -> {
					List<Vertex> tempList = Stream
							.iterate(tempPath, t -> t != null, t -> t.getPrevious())
							.map(Step::getCurrent)
							.collect(Collectors.toList());
					Collections.reverse(tempList);
					return tempList;
				}).map(stopList -> new IndividualPath(stopList))
				.collect(Collectors.toList());
	}
	
	/**
	 * 遍历每一条边时,判断是否继续下去.
	 * */
	protected abstract boolean toBeContinue(Step step);
	
	/**
	 * 遍历到每一个顶点,判读是否要做为结果存起来.
	 * */
	protected abstract void asResult(Step step);
	
	/** 
	 * @ClassName: Step
	 * @Description:  遍历到某个结点的相关信息.针对某个结点,记录当前节点,以及到当前节点为止在某个权重维度上的权重累加值.以及前一个节点的信息.
	 */
	protected static class Step {
		private final int totalWeight;
		private final Vertex current;
		private final Step previous;
		
		protected Step(int totalWeight, Vertex current, Step previous) {
			this.totalWeight = totalWeight;
			this.current = current;
			this.previous = previous;
		}

		/** @return 到当前结点为止,累积的总权重值. */
		protected int getTotalWeight() {
			return totalWeight;
		}
		
		/** @return 当前的节点. */
		protected Vertex getCurrent() {
			return current;
		}

		/** @return 当前结点的前续结点信息. */
		protected Step getPrevious() {
			return previous;
		}
	}

}
