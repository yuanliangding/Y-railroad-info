package yuanliangding.interview.YRailroadInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yuanliangding.interview.YRailroadInfo.core.SimpleYRailroadContext;
import yuanliangding.interview.YRailroadInfo.core.YRailroadContext;

/** 
 * @ClassName: Application
 * @Description:  TODO
 *
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月26日-下午8:52:43
 */
public class Application {

	public static void main(String[] args) {
		
		String commandStr = " cmd  -a  s -b xx -cc XX ";
		commandStr = commandStr.trim();
		
		Pattern legalPattern = Pattern.compile("^(\\S+)(( +-\\S+ +\\S+)*)$");
		Pattern optionPattern = Pattern.compile(" +-(\\S+) +(\\S+)");
		
		Matcher legalMatcher = legalPattern.matcher(commandStr);
		if(!legalMatcher.matches()) {
			throw new RuntimeException("命令格式错误.命令需要遵守这样的格式:cmd -a 1 -b x");
		}
		String commandCode = legalMatcher.group(1);
		System.out.println(commandCode);
		String commandOptions = legalMatcher.group(2);
		System.out.println(commandOptions);
		
		Matcher optionMatcher = optionPattern.matcher(commandOptions);
		while(optionMatcher.find()) {
			System.out.println("-------");
			String argName = optionMatcher.group(1);
			System.out.println(argName);
			String argValue = optionMatcher.group(2);
			System.out.println(argValue);
		}
		
		
		
		
		
		
		YRailroadContext yRailroadContext = new SimpleYRailroadContext();
//		yRailroadContext.start(mapUrl, exit);
	}

}
