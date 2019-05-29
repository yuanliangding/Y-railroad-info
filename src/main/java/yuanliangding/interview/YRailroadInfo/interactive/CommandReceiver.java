package yuanliangding.interview.YRailroadInfo.interactive;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import yuanliangding.interview.YRailroadInfo.interactive.CommandParser.CommandData;

/** 
 * @ClassName: CommandReceiver
 * @Description:  命令接收器基类.通过指定具体的标准输入,标准输出,错误输出都.形成实际并且独特的命令接收器子类.
 * 						该命令接收器不维护输入输出难道,使用后,请自行关闭
 * 						通过通过指定标准输入,标准输出,标准错误分别为System.in,System.out,System.err形成一个命令行工具,
 * 						也可以指定成硬盘文件,成为可以用于批处理的工具.或者指定到网络流量,形成一个分布式计算网络节点.
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午3:02:56
 */
public abstract class CommandReceiver {
	
	/**命令解析器*/
	private CommandParser commandParser;
	
	/**命令的实际执行体*/
	private final Map<String, Command> commands = new HashMap<>();
	
	/**标准输入*/
	private final InputStream standardIn;
	
	/**标准输出*/
	private final PrintStream standardOut;
	
	/**错误输出*/
	private final PrintStream standardError;
	
	private Scanner scanner = null;
	
	private String exitCommand = "exit";
	
	/**
	 * @param standardIn		标准输入
	 * @param standardOut		标准输出
	 * @param standardError	错误输出
	 * @param commandParser	命令解析器
	 * */
	public CommandReceiver(
			InputStream standardIn, 
			PrintStream standardOut,
			PrintStream standardError) {
		super();
		
		// TODO 参数正确性判断
		this.standardIn = standardIn;
		this.standardOut = standardOut;
		this.standardError = standardError;
	}

	/** 进入工作状态 */
	public void work () {
		scanner = new Scanner(standardIn);
		
		boolean run = true;
		
		while(run) {
			standardOut.print(">> ");
			
			String commandStr = scanner.nextLine();
			if (exitCommand.equals(commandStr)) {
				run = false;
			} else {
				try {
					CommandData commandData = commandParser.parser(commandStr);
					
					Command command = commands.get(commandData.getName());
					if (command == null) {
						throw new RuntimeException("不认识的命令");
					}
					
					Object result = command.execute(commandData);
					
					standardOut.println(result);
				} catch(Exception e) {
					standardError.println(e.getMessage());
				}
			}
		}
		
		destory();
	}
	
	private void destory() {
		scanner.close();
	}
	
	/**
	 * 设置命令解析器
	 * @param commandParser	命令解析器
	 * */
	public void setCommandParser(CommandParser commandParser) {
		this.commandParser = commandParser;
	}
	
	/**
	 * 注册若干条命令
	 * @param name		命令名称
	 * @param command	命令执行体
	 * */
	public void registeCommands(Map<String, Command> commands) {
		this.commands.putAll(commands);
	}
	
	/**
	 * 指定"退出命令",当接收到该命令时,命令接收器停止工作.若没有指定,默认为 "exit"
	 * */
	public void setExitCommand(String exit) {
		this.exitCommand = exit;
	}

}
