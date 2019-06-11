package yuan.interview.railroad.impl.Y_Railroad_Info;

import yuan.interview.railroad.graph.base.Vertex;
import yuan.interview.railroad.graph.io.GraphReader;
import yuan.interview.railroad.impl.GeneralGraphPolicy;

/** 
 * @ClassName: GeneralGraphPolicy
 * @Description:  Y-Railroad 图策略
 * 
 * 						提供两个维度的权重。1 DIST 2 STOP 由{@link Weight枚举提供}。
 * 						从外界数据读取到的权重存在DIST维度，表示距离，并同时加一个STOP维度，值始绐是1。表示前行一个顶点到达
 * 						遍历并在该STOP维度上计算,可以搜索出如下类似问题结果：从A途经3顶点到达C都哪些路径
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-上午6:28:46
 */
public class YRailroadGraphPolicy extends GeneralGraphPolicy {
	
	@Override
	public void setGraphReader(GraphReader graphReader) {
		graphReader.read().forEach(e -> {
				Vertex startStop = map.getVertex(e.getStart());
				Vertex endStop = map.getVertex(e.getEnd());
				map.setWeight(startStop, endStop, Weight.DIST.name(), e.getWeight());
				map.setWeight(startStop, endStop, Weight.STOP.name(), 1);
			}
		);
	}
	
}
