package yuanliangding.interview.YRailroadInfo.visit;

import java.util.List;

import yuanliangding.interview.YRailroadInfo.map.Stop;

/** 
 * @ClassName: Path
 * @Description:  路径,地图上某个点到另一个点的路线.
 * 						可以表示具体的路线,也可以只是对路线特点进行描述.
 * 						比如只指定起点和终点,要求其某个维度总权重值,是最小值的,或者居于某些数轴区间,比如在3到7之间.
 * 						关于路线的维度和权重,请参考{@link Stop}
 * 						
 * @see Stop
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午2:01:43
 */
public abstract class Path {
	
	protected final Stop begin;
	
	protected final Stop end;
	
	/**
	 * @param begin	路线起点
	 * @param end	路线终点
	 * */
	protected Path(Stop begin, Stop end) {
		if (begin == null) {
			throw new RuntimeException("起点不能为空");
		}
		if (end == null) {
			throw new RuntimeException("终点不能为空");
		}
		this.begin = begin;
		this.end = end;
	}

	/**
	 * 计算具体维度的总权重值.
	 * 
	 * @param dim 维度
	 * */
	public int getTotalWeight(String dim) {
		throw new RuntimeException("该类型路径不支持计算总权重.");
	}
	
	/**
	 * 具体化操作.
	 * 根据特点描述,从地图中找到满足条件的具体路线,结果往往不只一条.
	 * 在某维度计算路线具体权重的总值.最终筛选出满足条件的路线.
	 * 比如权重总值最小的,可以是路程最短路径,耗时最短路径.或者路程,经停数在某个范围的路线(途中可能会有环路)
	 * */
	public abstract List<CertainPath> concrete();
	
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
