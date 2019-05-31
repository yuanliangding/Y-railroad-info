package yuan.interview.railroad.interactive;

import yuan.interview.railroad.interactive.CommandParser.CommandData;

/** 
 * @ClassName: Command
 * @Description:  命令.外界注册给命令接收器的可执行命令.用于解析命令后继续实际的执行.
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午6:16:45
 */
public interface Command {
	
	public Object execute(CommandData commandData);
}
