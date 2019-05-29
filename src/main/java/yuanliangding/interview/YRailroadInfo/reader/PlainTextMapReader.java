package yuanliangding.interview.YRailroadInfo.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import yuanliangding.interview.YRailroadInfo.map.MapPolicy;
import yuanliangding.interview.YRailroadInfo.map.SimpleMapPolicy;
import yuanliangding.interview.YRailroadInfo.map.Stop;
import yuanliangding.interview.YRailroadInfo.map.MapDatum;

/** 
 * @ClassName: PlainTextMapReader
 * @Description:  普通文本格式的地图加载器.采用{@link SimpleMapPolicy}策略进行存储.
 * 
 * 		文本内容的格式规则为:
 * 			每一行为一条路线距离信息.其格式为:AB3,代表站点A到站点B距离为3.站点名称为一个字符的字符串
 * 			存储到存储中心,采用dist stop两个维度信息记录,以上记录A到B dist=3,stop=1
 *
 *	@see MapDatum
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-下午11:57:10
 */
public class PlainTextMapReader implements MapReader {
	
	private MapPolicy<?,?> mapPolicy = SimpleMapPolicy.getInstance();
	
	private static PlainTextMapReader instance = new PlainTextMapReader();
	
	public static PlainTextMapReader getInstance() {
		return instance;
	}
	
	private PlainTextMapReader() {}

	@Override
	public void from(MapDatum map, String path) {
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
				
				mapPolicy.addRoute(map, beginStop, endStop, null, distV);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
