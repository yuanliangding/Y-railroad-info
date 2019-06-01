package yuan.interview.railroad.interactive;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import yuan.interview.railroad.exception.InteractiveException;

/** 
 * @ClassName: CommandExecutor
 * @Description:  命令执行器.
 * 						命令执行器工作流程:
 * 						1	命令执行器,从为其指定的输入通道(通过setIO方法设置标准输入,标准输出,错误输出)读取命令字符串,
 * 						2	使用设置给他的命令解析器{@link CommandParser}进行命令字符串的解析.
 * 						3	解析命令字符串后得到命令数据{@link CommandData}实例后,
 * 							从设置给他的命令集(通过registeCommands方法注册给他)中选取一条相应的命令执行体执行.
 * 						4	执行结果或错误信息,通过设置给它的标准输出或者错误输出进行输出.
 * 
 * 						通过调用命令执行器实例的work方法使其进入工作状态.不断循环上述工作流程,或者处于等待接收命令状态
 * 						通过调用shutdown方法使其退出工作状态.
 * 						或者在输入通道为其输入 退出命令 (通过setExitCommand设置或者默认为"exit")
 * 
 * 						该命令接收器不维护输入输出通道IO,使用后,请自行关闭释放资源.
 * 
 * @see Command
 * @see CommandData
 * 
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午3:02:56
 */
public class CommandExecutor {
	
	/**命令解析器*/
	private CommandParser commandParser;
	
	/**命令集,命令的实际执行体*/
	private final Map<String, Command> commands = new HashMap<>();
	
	/**标准输入*/
	private InputStream standardIn;
	
	/**标准输出*/
	private PrintStream standardOut;
	
	/**错误输出*/
	private PrintStream standardError;
	
	private Scanner scanner = null;
	
	private String exitCommand = "exit";
	
	private boolean run = true;

	/** 进入工作状态 */
	public void work() {
		run = true;
		scanner = new Scanner(standardIn);
		
		while(run) {
			standardOut.print(">> ");
			
			String commandStr = scanner.nextLine();
			if (exitCommand.equals(commandStr)) {
				run = false;
			} else {
				try {
					standardOut.println(
								exec(commandStr)
							);
				} catch(Exception e) {
					standardError.println(e.getMessage());
				}
			}
		}
		
		destory();
	}
	
	/** 退出工作状态 */
	public void shutdown() {
		run = false;
	}
	
	/** 执行一条命令 */
	protected Object exec(String cmd) {
		CommandData commandData = commandParser.parser(cmd);
		
		Command command = commands.get(commandData.getName());
		if (command == null) {
			throw new InteractiveException("不认识的命令");
		}
		
		return command.execute(commandData);
	}
	
	private void destory() {
		scanner.close();
	}
	
	/**
	 * 设置命令执行器的标准IO,
	 * 当要停用命令执行器时,请自己关闭IO回收资源
	 * 
	 * @param standardIn		标准输入
	 * @param standardOut		标准输出
	 * @param standardError	错误输出
	 * */
	public void setIO(
			InputStream standardIn, 
			PrintStream standardOut,
			PrintStream standardError) {
		
		if (standardIn == null) {
			throw new InteractiveException("请指定可用的输入流做为标准输入");
		}
		if (standardOut == null) {
			throw new InteractiveException("请指定可用的输出流做为标准输出");
		}
		
		this.standardIn = standardIn;
		this.standardOut = standardOut;
		
		if (standardError == null) {
			this.standardError = standardOut;
		} else {
			this.standardError = standardError;
		}
	}
	
	public void setCommandParser(CommandParser commandParser) {
		this.commandParser = commandParser;
	}
	
	/**
	 * 注册若干条命令
	 * 用命令名称做为Map的key
	 * */
	public void registeCommands(Map<String, Command> commands) {
		this.commands.putAll(commands);
	}
	
	/**
	 * 指定"退出命令",当接收到该命令时,命令接收器停止工作.若没有指定,默认为 "exit"
	 * 通过"退出命令"停止工作的命令执行器,可以通过调用其work方法再次进入工作状态.
	 * */
	public void setExitCommand(String exit) {
		this.exitCommand = exit;
	}

}
