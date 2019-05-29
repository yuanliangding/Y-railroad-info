package yuanliangding.interview.YRailroadInfo.map;

import java.util.Map;

import yuanliangding.interview.YRailroadInfo.graph.GraphReader;

/** 
 * @ClassName: MapPolicy
 * @Description:  地图策略,
 * 						地图策略将会影响两个方面的内容:
 * 						1	地图的数据怎么完整存储(存储引擎只提供存储点和边的功能,不维护点和点代表的意义).
 * 							地图中,由A到B距离多少,耗时多少,经停几个站,具体意义由地图策略来维护
 * 						2	根据1中提供的存储策略,提供一些业务命令.子类实现需要提供具体的命令模式实现
 * 						3	提供2中的命令操作手册.
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-上午6:20:42
 */
public interface MapPolicy<C, W extends Enum<W>> {
	
	/**设置图数据导入器(设置好后,自动加载)*/
	public void setGraphReader(GraphReader graphReader);
	
	/**该策略提供的命令集*/
	public Map<String, C> getCommands();

}