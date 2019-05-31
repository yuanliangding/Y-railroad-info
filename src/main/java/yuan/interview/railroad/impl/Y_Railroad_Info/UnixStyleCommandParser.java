package yuan.interview.railroad.impl.Y_Railroad_Info;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yuan.interview.railroad.interactive.CommandParser;

/** 
 * @ClassName: UnixStyleCommandParser
 * @Description: 	unix风格的命令解析器.简易版选项和参数之前要有空格.
 * 						只解析这样格式的命令:
 * 							cmd -a 1 -b x
 * 						执行cmd命令,并传两个参数a=1,b=x,其中空格可以任意个.
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午3:59:51
 */
public class UnixStyleCommandParser implements CommandParser {
	
	// 用来匹配整个命令字符串是不是符合cmd -a 1 -b x格式.
	private final Pattern legalPattern = Pattern.compile("^(\\S+)(( +-\\S+ +\\S+)*)$");
	// 从命令中提取参数信息
	private final Pattern optionPattern = Pattern.compile(" +-(\\S+) +(\\S+)");
	
	private final static UnixStyleCommandParser instance = new UnixStyleCommandParser();
	
	public static UnixStyleCommandParser getInstance() {
		return instance;
	}
	
	private UnixStyleCommandParser(){}

	@Override
	public CommandData parser(String commandStr) {
		commandStr = commandStr.trim();
		
		Matcher legalMatcher = legalPattern.matcher(commandStr);
		if(!legalMatcher.matches()) {
			throw new RuntimeException("命令格式错误.命令需要遵守这样的格式:cmd -a 1 -b x");
		}
		
		String commandName = legalMatcher.group(1);
		String commandOptions = legalMatcher.group(2);
		
		Map<String, String> options = new HashMap<>();
		
		Matcher optionMatcher = optionPattern.matcher(commandOptions);
		while(optionMatcher.find()) {
			options.put(optionMatcher.group(1), optionMatcher.group(2));
		}
		
		CommandData commandData = new CommandData(commandName, options);
		
		return commandData;
	}

}
