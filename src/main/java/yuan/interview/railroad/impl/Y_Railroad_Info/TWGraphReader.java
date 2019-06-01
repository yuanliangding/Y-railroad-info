package yuan.interview.railroad.impl.Y_Railroad_Info;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yuan.interview.railroad.exception.YRailroadException;
import yuan.interview.railroad.graph.base.GraphReader;

/** 
 * @ClassName: TWGraphReader
 * @Description:  根据ThoughtWorks Home Test的Problem one: Trains所采用的通勤线路数据格式,读取数据.
 * 
 * 						可以通过path参数,指定数据文件的本地路径.如果没指定文件数据,则默认加载/default.txt(main/resources目录中)为默认数据.
 * 
 * 						文本内容的格式规则为:
 * 							每一行为一条权重信息.其格式为:AB3,代表顶点A到顶点B权重为3.顶点名称为一个单字符的字符串
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-下午11:57:10
 */
public class TWGraphReader implements GraphReader {
	
	private String path = null;
	
	public TWGraphReader(String path) {
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
		
		final Pattern routePattern = Pattern.compile("(\\S)(\\S)(\\d+)");
		
		String route;
		while ((route = bufferedReader.readLine()) != null) {
			route = route.trim();
			
			Matcher routeMatcher = routePattern.matcher(route);
			if(!routeMatcher.matches()) {
				throw new YRailroadException("命令格式错误.命令需要遵守这样的格式:cmd -a 1 -b x");
			}
			
			String begin = routeMatcher.group(1);
			String end = routeMatcher.group(2);
			String dist = routeMatcher.group(3);
			
			int distV = Integer.parseInt(dist);
			
			results.add(new WeightInfo(begin,end,null,distV));
		}
		
		return results;
	}

}
