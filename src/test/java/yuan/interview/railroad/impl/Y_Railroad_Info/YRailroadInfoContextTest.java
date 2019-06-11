package yuan.interview.railroad.impl.Y_Railroad_Info;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import yuan.interview.railroad.test.util.TWDataPrepared;

/** 
 * @ClassName: YRailroadInfoContextTest
 * @Description:  批量的命令提供给YRailroadInfoContext进行测试,通过最终输出进行效验
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月31日-下午1:45:17
 */
public class YRailroadInfoContextTest extends TWDataPrepared {
	
	private YRailroadInfoContext yRailroadInfoContext = null;

	@Before
	public void before() {
		yRailroadInfoContext = new YRailroadInfoContext();
	}

	@Test
	public void testParser() {
		String commands = 
				"dist -p A-B-C\n" + 
				"dist -p A-D\n" + 
				"dist -p A-D-C\n" + 
				"dist -p A-E-B-C-D\n" + 
				"dist -p A-E-D\n" + 
				"count -f s -b C -e C -M 3\n" + 
				"count -f s -b A -e C -m 4 -M 4\n" + 
				"dist -f md -b A -e C\n" + 
				"dist -f md -b B -e B\n" + 
				"count -f d -b C -e C -M n30\n" + 
				"exit";
		
		String expectedResult = 
				">> 9\n" + 
				">> 5\n" + 
				">> 13\n" + 
				">> 22\n" + 
				">> NO SUCH ROUTE\n" + 
				">> 2\n" + 
				">> 3\n" + 
				">> 9\n" + 
				">> 9\n" + 
				">> 7";
		
		ByteArrayOutputStream resultByteArrayOutputStream = new ByteArrayOutputStream();
		
		ByteArrayInputStream input = new ByteArrayInputStream(commands.getBytes());
		PrintStream output = new PrintStream(resultByteArrayOutputStream);
		
		yRailroadInfoContext.start(input, output, output, dataPath, null);
		
		Assert.assertThat(
				"批量运行的结果期待的不一样", 
				resultByteArrayOutputStream.toString(), 
				CoreMatchers.containsString(expectedResult));
	}

}
