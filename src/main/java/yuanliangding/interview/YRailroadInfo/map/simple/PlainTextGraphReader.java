package yuanliangding.interview.YRailroadInfo.map.simple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import yuanliangding.interview.YRailroadInfo.graph.base.Graph;
import yuanliangding.interview.YRailroadInfo.graph.base.GraphReader;

/** 
 * @ClassName: PlainTextGraphReader
 * @Description:  普通文本格式的地图加载器.采用{@link SimpleMapPolicy}策略进行存储.
 * 
 * 		文本内容的格式规则为:
 * 			每一行为一条路线距离信息.其格式为:AB3,代表站点A到站点B距离为3.站点名称为一个字符的字符串
 * 			存储到存储中心,采用dist stop两个维度信息记录,以上记录A到B dist=3,stop=1
 *
 *	@see Graph
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-下午11:57:10
 */
public class PlainTextGraphReader implements GraphReader {
	
	private String path = null;
	
	public PlainTextGraphReader(String path) {
		this.path = path;
	}

	@Override
	public List<WeightInfo> read() {
		if (path == null) {
			try(
					InputStream defaultInputStream = this.getClass().getResourceAsStream("/default.txt");
					InputStreamReader defaultinputStreamReader = new InputStreamReader(defaultInputStream);
					BufferedReader mapBufferedReader = new BufferedReader(defaultinputStreamReader)
							){
				return read(mapBufferedReader);
			} catch (IOException e) {
				throw new RuntimeException("程序启动失败,没找到默认的地图数据文件.");
			}
		} else {
			try (FileReader mapFileReader = new FileReader(path);
					BufferedReader mapBufferedReader = new BufferedReader(mapFileReader)) {
				return read(mapBufferedReader);
			} catch (IOException e) {
				throw new RuntimeException("程序启动失败,没找到指定的地图数据文件.");
			}
		}
	}
	
	private List<WeightInfo> read(BufferedReader bufferedReader) throws NumberFormatException, IOException {
		List<WeightInfo> results = new ArrayList<>();
		
		String route;
		while ((route = bufferedReader.readLine()) != null) {
			String begin = route.substring(0, 1);
			String end = route.substring(1, 2);
			String dist = route.substring(2, 3);
			
			int distV = Integer.parseInt(dist);
			
			results.add(new WeightInfo(begin,end,null,distV));
		}
		
		return results;
	}

}
