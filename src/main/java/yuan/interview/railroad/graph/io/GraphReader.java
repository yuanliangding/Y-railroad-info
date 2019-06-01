package yuan.interview.railroad.graph.io;

import java.util.List;

import yuan.interview.railroad.graph.base.Graph;
import yuan.interview.railroad.graph.base.Vertex;

/** 
 * @ClassName: GraphReader
 * @Description:  地图数据读取器，从文件中或者外界其他的流式数据中。读取图数据的有向边信息。并以普通JSON对象WeightInfo表示
 * 
 * @see Graph
 * @see Vertex
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-下午11:50:08
 */
public interface GraphReader {
	
	List<WeightInfo> read();

}
