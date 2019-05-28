package yuanliangding.interview.YRailroadInfo.interactive;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


/** 
 * @ClassName: CommandReceiver
 * @Description:  命令接收器基类.通过指定具体的标准输入,标准输出,错误输出都.形成实际并且独特的命令接收器子类.
 * 						通过通过指定标准输入,标准输出,标准错误分别为System.in,System.out,System.err形成一个命令行工具,
 * 						也可以指定成硬盘文件,成为可以用于批处理的工具.或者指定到网络流量,形成一个分布式计算网络节点.
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午3:02:56
 */
public abstract class CommandReceiver {
	
	private String exitCommand = "exit";
	
	/** 进入工作状态 */
	public void work () {
		Scanner scanner = new Scanner(getStandardIn());
		
		String commandStr = null;
		do {
			commandStr = scanner.nextLine();
			System.out.println(commandStr);
			
			
			
		}while(!exitCommand.equals(commandStr));
		
		destroy();
	}
	
	/**
	 * 指定"退出命令",当接收到该命令时,命令接收器停止工作.若没有指定,默认为 "exit"
	 * */
	public void setExitCommand(String exit) {
		if (exit != null && !"".equals(exit)) {
			this.exitCommand = exit;
		}
	}
	
	/**获得标准输入*/
	protected abstract InputStream getStandardIn();
	
	/**获得标准输出*/
	protected abstract PrintStream getStandardOut();
	
	/**获得错误输出*/
	protected abstract PrintStream getStandardError();
	
	/**退出程序,清理释放资源*/
	protected abstract void destroy();

}
