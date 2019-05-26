package yuanliangding.interview.YRailroadInfo.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import yuanliangding.interview.YRailroadInfo.map.Stop;
import yuanliangding.interview.YRailroadInfo.map.StopMap;

/** 
 * @ClassName: PlainTextMapReader
 * @Description:  普通文本格式的地图加载器.
 * 
 * 		文本内容的格式规则为:
 * 			每一行为一条路线距离信息.其格式为AB3,代表站点A到站点B距离为3.站点名称为一个字符的字符串
 *
 *	@see StopMap
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-下午11:57:10
 */
public class PlainTextMapReader implements MapReader {
	
	/** 距离 */
	public static final String DIST = "dist";
	
	/** 跨越站数 */
	public static final String STOP = "stop";
	
	private static PlainTextMapReader instance = new PlainTextMapReader();
	
	public static PlainTextMapReader getInstance() {
		return instance;
	}
	
	private PlainTextMapReader() {}

	@Override
	public void from(StopMap map, String path) {
		try (FileReader mapFileReader = new FileReader(path);
				BufferedReader mapBufferedReader = new BufferedReader(mapFileReader)) {
			String route;
			while ((route = mapBufferedReader.readLine()) != null) {
				String begin = route.substring(0, 1);
				String end = route.substring(1, 2);
				String dist = route.substring(2, 3);
				
				Stop beginStop = map.getStop(begin);
				Stop endStop = map.getStop(end);
				int distV = Integer.parseInt(dist);
				
				map.addRoute(beginStop, endStop, DIST, distV);
				map.addRoute(beginStop, endStop, STOP, 1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
