package yuan.interview.railroad.interactive;

/** 
 * @ClassName: CommandParser
 * @Description:  命令解析器,
 * 						具体的命令解析器实现类,将字符串数据(只有一条命令)按自己的命令格式规则,进行解析成普通的JAVA类{@link CommandData}
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午3:54:40
 */
public interface CommandParser {
	
	/** 将一条命令的字符串表示,解析成命令数据类{@link CommandData} */
	CommandData parser(String command);

}
