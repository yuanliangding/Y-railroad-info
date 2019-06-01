package yuan.interview.railroad.impl.Y_Railroad_Info;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import yuan.interview.railroad.impl.Y_Railroad_Info.XStyleCommandParser;
import yuan.interview.railroad.interactive.CommandData;

/**
 * @ClassName: XStyleCommandParserTest
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午4:26:33
 */
public class XStyleCommandParserTest {

	private XStyleCommandParser xStyleCommandParser = null;

	@Before
	public void before() {
		xStyleCommandParser = new XStyleCommandParser();
	}

	@Test
	public void testParser() {
		CommandData commandData = xStyleCommandParser.parser("cmd -a 1 -b x");
		
		Assert.assertThat("命令名称为 cmd", commandData.getName(), CoreMatchers.equalTo("cmd"));
		Assert.assertThat("命令含有参数a，且值为1", commandData.getOptions().get("a"), CoreMatchers.equalTo("1"));
		Assert.assertThat("命令含有参数b，且值为x", commandData.getOptions().get("b"), CoreMatchers.equalTo("x"));
	}

}
