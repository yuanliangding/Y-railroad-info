package yuanliangding.interview.YRailroadInfo.visit;

import yuanliangding.interview.YRailroadInfo.map.Stop;

/** 
 * @ClassName: AbsPath
 * @Description:  抽像路径(只指定一个起点),路径是指地图上某个点到另一个点的路线.
 * 						可以表示具体的路线,也可以只是对路线特点进行描述.
 * 						比如只指定起点和终点,要求其某个维度总权重值,是最小值的,或者居于某些数轴区间,比如在3到7之间.
 * 						关于路线的维度和权重,请参考{@link Stop}
 * 						
 * @see Stop
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午2:01:43
 */
public abstract class AbsPath {
	
	protected final Stop begin;
	
	/**
	 * @param begin	路线起点
	 * */
	protected AbsPath(Stop begin) {
		if (begin == null) {
			throw new RuntimeException("起点不能为空");
		}
		
		this.begin = begin;
	}

	/**
	 * 计算具体维度的总权重值.
	 * 
	 * @param dim 维度
	 * */
	public int getTotalWeight(String dim) {
		throw new RuntimeException("该类型路径不支持计算总权重.");
	}
	
}
