package yuanliangding.interview.YRailroadInfo.graph.base;

import yuanliangding.interview.YRailroadInfo.graph.GraphException;
import yuanliangding.interview.YRailroadInfo.graph.algorithm.BoundedPath;
import yuanliangding.interview.YRailroadInfo.graph.algorithm.IndividualPath;
import yuanliangding.interview.YRailroadInfo.graph.algorithm.MinPath;
import yuanliangding.interview.YRailroadInfo.graph.algorithm.SpecifiedPath;

/** 
 * @ClassName: Path
 * @Description:  路径,
 * 						这是比较泛化的路径,只指定了起点,后续的途经点根据具体路径类型确定.
 * 						具体参考该类的所有子类
 * 							{@link IndividualPath},
 * 							{@link SpecifiedPath},
 * 							{@link MinPath},
 * 							{@link BoundedPath}
 * 
 * @see Vertex
 * @see IndividualPath
 * @see SpecifiedPath
 * @see MinPath
 * @see BoundedPath
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午2:01:43
 */
public abstract class Path {
	
	protected final Vertex begin;
	
	/**
	 * @param begin	路线起点
	 * */
	protected Path(Vertex begin) {
		if (begin == null) {
			throw new GraphException("起点不能为空");
		}
		
		this.begin = begin;
	}

	/**
	 * 计算在某层上的边的总权重
	 * 
	 * @param layer 在该层上的边的总权重
	 * */
	public int getTotalWeight(String layer) {
		throw new GraphException("该类型路径不支持计算总权重.");
	}
	
}
