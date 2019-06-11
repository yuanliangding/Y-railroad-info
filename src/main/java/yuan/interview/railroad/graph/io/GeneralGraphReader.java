package yuan.interview.railroad.graph.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import yuan.interview.railroad.exception.YRailroadException;

/** 
 * @ClassName: GeneralGraphReader
 * @Description:  地图数据读取器通常实现。
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年6月11日-下午11:59:17
 */
public abstract class GeneralGraphReader implements GraphReader {

	protected String path = null;
	protected String defaultPath = null;

	public GeneralGraphReader(String path, String defaultPath) {
		this.path = path;
		this.defaultPath = defaultPath;
	}

	@Override
	public List<WeightInfo> read() {
		if (path == null) {
			try(
					InputStream defaultInputStream = this.getClass().getResourceAsStream(defaultPath);
					InputStreamReader defaultinputStreamReader = new InputStreamReader(defaultInputStream);
					BufferedReader mapBufferedReader = new BufferedReader(defaultinputStreamReader)
							){
				return read(mapBufferedReader);
			} catch (IOException e) {
				throw new YRailroadException("程序启动失败。没找到默认的地图数据文件");
			}
		} else {
			try (FileReader mapFileReader = new FileReader(path);
					BufferedReader mapBufferedReader = new BufferedReader(mapFileReader)) {
				return read(mapBufferedReader);
			} catch (IOException e) {
				throw new YRailroadException("程序启动失败。没找到指定的地图数据文件");
			}
		}
	}
	
	protected abstract List<WeightInfo> read(BufferedReader bufferedReader) throws NumberFormatException, IOException;

}