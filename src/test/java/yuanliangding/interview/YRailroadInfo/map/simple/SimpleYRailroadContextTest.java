package yuanliangding.interview.YRailroadInfo.map.simple;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/** 
 * @ClassName: SimpleYRailroadContextTest
 * @Description:  集成测试
 *
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午7:56:23
 */
@RunWith(Parameterized.class)
public class SimpleYRailroadContextTest {
	
	private static String textPath = null;
	
	protected static SimpleYRailroadContext simpleYRailroadContext = null;
	
	private InputStream in = null;
	private PrintStream out = null;
	private PrintStream err = null;
	
	private String command = null;
	
	private String result = null;

	public SimpleYRailroadContextTest(String command, String result) {
		this.command = command;
		this.result = result;
	}

	@Parameters
	public static List<List<String>> data() {
		List<List<String>> dataSet = new ArrayList<>();
		dataSet.add(Arrays.asList("dist -p A-B-C","9"));
		dataSet.add(Arrays.asList("dist -p A-D","5"));
		dataSet.add(Arrays.asList("dist -p A-D-C","13"));
		dataSet.add(Arrays.asList("dist -p A-E-B-C-D","22"));
		dataSet.add(Arrays.asList("dist -p A-E-D","NO SUCH ROUTE"));
		dataSet.add(Arrays.asList("count -f s -b C -e C -M 3","2"));
		dataSet.add(Arrays.asList("count -f s -b A -e C -m 4 -M 4","3"));
		dataSet.add(Arrays.asList("dist -f md -b A -e C","9"));
		dataSet.add(Arrays.asList("dist -f md -b B -e B","9"));
		dataSet.add(Arrays.asList("count -f d -b C -e C -M n30","7"));
		dataSet.add(Arrays.asList("exit",""));
		
		return dataSet;
	}
	
	@Before
	public void before() throws IOException {
		in = System.in;
		out = System.out;
		err = System.err;
		
		ByteArrayInputStream input = new ByteArrayInputStream(command.getBytes());
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PrintStream output = new PrintStream(byteArrayOutputStream);
		System.setIn(input);
		System.setOut(output);
		System.setErr(output);
	}

	@After
	public void after() {
		System.setIn(in);
		System.setOut(out);
		System.setErr(err);
	}
	
	@Test
	public void test() {

		System.out.println("test ------- ");
	}
	
	@BeforeClass
	public static void beforeClass() throws IOException {
		File mapTextFile = File.createTempFile("y_railroad_info_map_plain_text", ".txt");
		textPath = mapTextFile.getCanonicalPath();
		try (FileWriter fileWriter = new FileWriter(mapTextFile)) {
			fileWriter.write("AB5\nBC4\nCD8\nDC8\nDE6\nAD5\nCE2\nEB3\nAE7\n");
		}
		
		simpleYRailroadContext = new SimpleYRailroadContext();
		simpleYRailroadContext.start(textPath, null);
	}

	@AfterClass
	public static void afterClass() {
		File mapTextFile = new File(textPath);
		mapTextFile.deleteOnExit();
	}

}
