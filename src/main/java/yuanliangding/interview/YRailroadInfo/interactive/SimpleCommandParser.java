package yuanliangding.interview.YRailroadInfo.interactive;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * @ClassName: SimpleCommandParser
 * @Description:  简单的命令解析器.只解析这样格式的命令:
 * 							cmd -a 1 -b x
 * 						执行cmd命令,并传两个参数a=1,b=x,其中空格可以任意个.
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午3:59:51
 */
public class SimpleCommandParser implements CommandParser {
	
	private final Pattern legalPattern = Pattern.compile("^(\\S+)(( +-\\S+ +\\S+)*)$");
	private final Pattern optionPattern = Pattern.compile(" +-(\\S+) +(\\S+)");
	
	private final static SimpleCommandParser instance = new SimpleCommandParser();
	
	public static SimpleCommandParser getInstance() {
		return instance;
	}

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
