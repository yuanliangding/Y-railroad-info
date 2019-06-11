package yuan.interview.railroad.impl.Ys_Railroad_Info;

import yuan.interview.railroad.graph.base.Vertex;
import yuan.interview.railroad.graph.io.GraphReader;
import yuan.interview.railroad.impl.GeneralGraphPolicy;

/** 
 * @ClassName: YSRailroadGraphPolicy
 * @Description:  YSRailroadGraph 图策略
 * 
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-上午6:28:46
 */
public class YSRailroadGraphPolicy extends GeneralGraphPolicy {
	
	@Override
	public void setGraphReader(GraphReader graphReader) {
		graphReader.read().forEach(e -> {
				Vertex startStop = map.getVertex(e.getStart());
				Vertex endStop = map.getVertex(e.getEnd());
				map.setWeight(startStop, endStop, e.getDim(), e.getWeight());
			}
		);
	}
	
}
