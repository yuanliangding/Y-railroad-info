package yuan.interview.railroad.test.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;

/** 
 * @ClassName: TWDataProvider_AA
 * @Description:  为测试准备数据,TW题目要求里的数据
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-下午5:20:38
 */
public class TWDataProvider {
	
	protected static String dataPath = null;
	
	@BeforeClass
	public static void beforeClass() throws IOException {
		File mapTextFile = File.createTempFile("y_railroad_info_map_plain_text", ".txt");
		dataPath = mapTextFile.getCanonicalPath();
		try (FileWriter fileWriter = new FileWriter(mapTextFile)) {
			fileWriter.write("AB5\nBC4\nCD8\nDC8\nDE6\nAD5\nCE2\nEB3\nAE7\n");
		}
	}

	@AfterClass
	public static void afterClass() {
		File mapTextFile = new File(dataPath);
		mapTextFile.deleteOnExit();
	}

}
