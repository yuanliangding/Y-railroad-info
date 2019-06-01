package yuan.interview.railroad.interactive;

import yuan.interview.railroad.interactive.CommandParser.CommandData;

/** 
 * @ClassName: Command
 * @Description:  命令规范接口.
 * 						命令执行器{@link CommandExecutor}接收该命令规范接口的具体实现类,以获得外界的具体命令运行体.
 * 						为终端或者其他输入通道输入的具体命令,运行相应的命令运行体.
 * 
 * @see CommandExecutor
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午6:16:45
 */
public interface Command {
	
	Object execute(CommandData commandData);
}
