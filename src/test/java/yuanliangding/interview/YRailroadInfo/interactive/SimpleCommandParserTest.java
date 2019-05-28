package yuanliangding.interview.YRailroadInfo.interactive;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import yuanliangding.interview.YRailroadInfo.interactive.CommandParser.CommandData;

/**
 * @ClassName: SimpleCommandParserTest
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午4:26:33
 */
public class SimpleCommandParserTest {

	private SimpleCommandParser simpleCommandParser = null;

	@Before
	public void before() {
		simpleCommandParser = SimpleCommandParser.getInstance();
	}

	@Test
	public void testParser() {
		CommandData commandData = simpleCommandParser.parser("cmd -a 1 -b x");
		
		Assert.assertThat("命令名称为 cmd", commandData.getName(), CoreMatchers.equalTo("cmd"));
		Assert.assertThat("命令含有参数a,且值为1", commandData.getOptions().get("a"), CoreMatchers.equalTo("1"));
		Assert.assertThat("命令含有参数b,且值为x", commandData.getOptions().get("b"), CoreMatchers.equalTo("x"));
	}

}
