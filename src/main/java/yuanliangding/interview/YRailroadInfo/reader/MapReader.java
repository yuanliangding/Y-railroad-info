package yuanliangding.interview.YRailroadInfo.reader;

import yuanliangding.interview.YRailroadInfo.map.MapPolicy;

/** 
 * @ClassName: MapReader
 * @Description:  地图加载器,通过指定一个文件路径进行地图数据的加载
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-下午11:50:08
 */
public interface MapReader {
	
	public void from(MapPolicy<?,?> mapPolicy, String path);

}
