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
	 * @param dim	规约描述针对具体维度的权重
	 * */
	protected SpecifiedPath(Stop begin, Stop end, String dim) {
		super(begin);
		this.dim = dim;
		this.end = end;
	}
	
	/**
	 * 具体化操作.
	 * 根据规约描述,从地图中找到满足条件的具体路线,结果往往不只一条.
	 * 在指定维度计算路线具体权重的累加总值.最终筛选出满足条件的路线.
	 * 根据具体子类的实现,可以得到最短路径,耗时最少路径.或者经停一定次数的路线(途中可能会有环路)
	 * 
	 * TODO 由于遍历的时候,临时数据放在了实例变量中,所以该类及其所有子类具体化遍历操作不是线程安全的.
	 * 
	 * */
	public List<IndividualPath> concrete() {
		
		// 1 清理工作
		clear();
		
		// 2 进行遍历寻找
		nextStop(begin, new TempPath(0, begin, null));
		
		// 3 整理结果集
		return getResult();
	}
	
	/**
	 * TODO	1	对权重只是做简单的类加操作,对于路途中有负环路,或0环路.该递归会死循环.
	 * 			2	由于是采用递归操作,对于规模大的地图有可能会导致内存不足的问题.
	 * */
	private void nextStop(Stop curr, TempPath currTempPath) {
		curr.getNexts(dim).forEach((Stop stop,Integer weight) -> {
			TempPath tempPath = new TempPath(currTempPath.getTotalWeight()+weight, stop, currTempPath);
			if (toBeContinue(tempPath)) {
				nextStop(stop, tempPath);
			}
		});
	}
	
	protected abstract void clear();
	
	protected abstract List<IndividualPath> getResult();
	
	/**
	 * 遍历过程中,判断是否继续下去.子类中往往是在这个方法中搜集满足条件的结果
	 * */
	protected abstract boolean toBeContinue(TempPath tempPath);
	
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
