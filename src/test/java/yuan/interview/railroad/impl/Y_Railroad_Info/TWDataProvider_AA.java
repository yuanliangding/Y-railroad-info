package yuan.interview.railroad.impl.Y_Railroad_Info;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;

import yuan.interview.railroad.graph.base.GraphReader;
import yuan.interview.railroad.impl.Y_Railroad_Info.TWGraphReader;
import yuan.interview.railroad.impl.Y_Railroad_Info.YRailroadGraphPolicy;

/** 
 * @ClassName: TWDataProvider_AA
 * @Description:  为测试准备数据,TW题目要求里的数据
 *
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-下午5:20:38
 */
public class TWDataProvider_AA {
	
	private String textPath = null;
	
	protected GraphReader graphReader = null;
	
	protected YRailroadGraphPolicy yRailroadGraphPolicy = null;
	
	@Before
	public void before() throws IOException {
		File mapTextFile = File.createTempFile("y_railroad_info_map_plain_text", ".txt");
		textPath = mapTextFile.getCanonicalPath();
		try (FileWriter fileWriter = new FileWriter(mapTextFile)) {
			fileWriter.write("AB5\nBC4\nCD8\nDC8\nDE6\nAD5\nCE2\nEB3\nAE7\n");
		}
		
		graphReader = new TWGraphReader(textPath);
		
		yRailroadGraphPolicy = YRailroadGraphPolicy.getInstance();
		yRailroadGraphPolicy.setGraphReader(graphReader);
	}

	@After
	public void after() {
		File mapTextFile = new File(textPath);
		mapTextFile.deleteOnExit();
	}

}
