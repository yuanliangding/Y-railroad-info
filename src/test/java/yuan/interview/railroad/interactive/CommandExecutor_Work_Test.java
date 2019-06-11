package yuan.interview.railroad.interactive;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import yuan.interview.railroad.impl.Y_Railroad_Info.XStyleCommandParser;

/**
 * @ClassName: CommandExecutor_Work_Test
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月29日-下午5:47:09
 */
public class CommandExecutor_Work_Test extends CommandExecutorPrepared {
	
	@Before
	public void before() throws IOException {
		super.before();
		commandExecutor.setCommandParser(new XStyleCommandParser());
	}

	@Test
	public void testWork() throws IOException {
		
		String commands = 
				"cmd1 -p A-B-C\n" + 
				"cmd1 -p A-D\n" + 
				"cmd1 -p A-D-C\n" + 
				"cmd2 -p A-E-B-C-D\n" + 
				"cmd2 -p A-E-D\n" + 
				"cmdException -f s -b C -e C -M 3\n" + 
				"cmd2 -f s -b A -e C -m 4 -M 4\n" + 
				"cmd2 -f md -b A -e C\n" + 
				"cmd1 -f md -b B -e B\n" + 
				"cmd1 -f d -b C -e C -M n30\n" + 
				"exit";
		
		String expectedResult = 
				">> cmd1 -> p=A-B-C\n" + 
				">> cmd1 -> p=A-D\n" + 
				">> cmd1 -> p=A-D-C\n" + 
				">> cmd2 -> p=A-E-B-C-D\n" + 
				">> cmd2 -> p=A-E-D\n" + 
				">> CMD Exception\n" + 
				">> cmd2 -> M=4,b=A,e=C,f=s,m=4\n" + 
				">> cmd2 -> b=A,e=C,f=md\n" + 
				">> cmd1 -> b=B,e=B,f=md\n" + 
				">> cmd1 -> M=n30,b=C,e=C,f=d\n" + 
				">> ";
		
		ByteArrayOutputStream resultByteArrayOutputStream = new ByteArrayOutputStream();
		ByteArrayInputStream input = new ByteArrayInputStream(commands.getBytes());
		PrintStream output = new PrintStream(resultByteArrayOutputStream);
		commandExecutor.setIO(input, output, output);
		
		commandExecutor.work();
		
		Assert.assertThat(
				"批量运行的结果期待的不一样", 
				resultByteArrayOutputStream.toString(), 
				CoreMatchers.containsString(expectedResult));
	}

}
