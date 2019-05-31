package yuan.interview.railroad.graph.base;

import yuan.interview.railroad.exception.GraphException;
import yuan.interview.railroad.graph.algorithm.BoundedPath;
import yuan.interview.railroad.graph.algorithm.IndividualPath;
import yuan.interview.railroad.graph.algorithm.MinPath;
import yuan.interview.railroad.graph.algorithm.SpecifiedPath;

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
	 * @param begin	路径起点
	 * */
	protected Path(Vertex begin) {
		if (begin == null) {
			throw new GraphException("起点不能为空");
		}
		
		this.begin = begin;
	}

	/**
	 * 基于某个权重维度,为当前路径的所有有向边,在该维度上的权重分量,计算权重分量的值的累加和.
	 * 
	 * @param dim 权重维度
	 * */
	public int getTotalWeight(String dim) {
		throw new GraphException("该类型路径不支持计算总权重.");
	}
	
}
