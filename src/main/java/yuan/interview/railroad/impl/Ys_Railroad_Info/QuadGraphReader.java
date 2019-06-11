package yuan.interview.railroad.impl.Ys_Railroad_Info;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yuan.interview.railroad.exception.YRailroadException;
import yuan.interview.railroad.graph.io.GeneralGraphReader;
import yuan.interview.railroad.graph.io.WeightInfo;

/** 
 * @ClassName: QuadGraphReader
 * @Description:  这里读取数据的格式是。A->B DIST:5 A起点，B终点，DIST权重分量名，5 权重分量值
 * 
 * 						可以通过path参数，指定数据文件的本地路径。如果没指定文件数据。则默认加载/map/quad.txt(main/resources目录中)为默认数据
 * 
 * 						文本内容的格式规则为：
 * 							每一行为一条权重信息，如A->B DIST:5
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-下午11:57:10
 */
public class QuadGraphReader extends GeneralGraphReader {
	
	public QuadGraphReader(String path) {
		super(path, "/map/quad.txt");
	}

	@Override
	protected List<WeightInfo> read(BufferedReader bufferedReader) throws NumberFormatException, IOException {
		List<WeightInfo> results = new ArrayList<>();
		
		final Pattern routePattern = Pattern.compile("(\\S+)->(\\S+) (\\S+):(\\d+)");
		
		bufferedReader.lines().forEach(route -> {
			route = route.trim();
			
			Matcher routeMatcher = routePattern.matcher(route);
			if(!routeMatcher.matches()) {
				throw new YRailroadException("地图数据 " + route + "，格式错误。应该为A->B DIST:5");
			}
			
			String begin = routeMatcher.group(1);
			String end = routeMatcher.group(2);
			String weightN = routeMatcher.group(3);
			String weightV = routeMatcher.group(4);
			
			int value = Integer.parseInt(weightV);
			
			results.add(new WeightInfo(begin, end, weightN, value));
		});
		
		return results;
	}

}
