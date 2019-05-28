package yuanliangding.interview.YRailroadInfo.visit;

import java.util.List;

import yuanliangding.interview.YRailroadInfo.map.Stop;

/** 
 * @ClassName: SpecifiedPath
 * @Description:  受规约的路径.既具有某种特征的路径.
 * 						可以进行具体化操作(concrete),根据规约描述,从地图中搜索出满足规约的所有路径.
 * 
 * 						可以再细分成更具体的规约,比如路径最短路径,转站数在一定范围的路径等,
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-上午9:04:04
 */
public abstract class SpecifiedPath extends AbsPath {
	
	protected Stop end;
	
	protected String dim;
	
	/**
	 * @param begin	路线起点
	 * @param end	路线终点(传null为不指定)
	 * @param dim	描述针对具体维度的权重
	 * */
	protected SpecifiedPath(Stop begin, Stop end, String dim) {
		super(begin);
		this.dim = dim;
		this.end = end;
	}

	/**
	 * 具体化操作.
	 * 根据特点描述,从地图中找到满足条件的具体路线,结果往往不只一条.
	 * 在某维度计算路线具体权重的总值.最终筛选出满足条件的路线.
	 * 比如权重总值最小的,可以是路程最短路径,耗时最短路径.或者路程,经停数在某个范围的路线(途中可能会有环路)
	 * */
	public abstract List<IndividualPath> concrete();
	
	/** 
	 * @ClassName: TempPath
	 * @Description:  临时路径
	 * 						在遍历时,对各个结果记录刚才走过的线路.
	 * 						只记录当前结点的前一个结点就可以.
	 */
	protected static class TempPath {
		private final int totalWeight;
		private final Stop curr;
		private final TempPath previous;
		
		protected TempPath(int totalWeight, Stop curr, TempPath previous) {
			this.totalWeight = totalWeight;
			this.curr = curr;
			this.previous = previous;
		}

		/**
		 * @return 到当前结点为止,累积的总权重值.
		 */
		protected int getTotalWeight() {
			return totalWeight;
		}
		
		/**
		 * @return 当前结点的前续结点.
		 */
		protected Stop getCurr() {
			return curr;
		}

		/**
		 * @return 当前结点的前续结点信息.
		 */
		protected TempPath getPrevious() {
			return previous;
		}
	}

}
