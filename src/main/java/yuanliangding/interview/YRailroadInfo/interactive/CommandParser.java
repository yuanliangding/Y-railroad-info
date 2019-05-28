package yuanliangding.interview.YRailroadInfo.interactive;

import java.util.Map;

/** 
 * @ClassName: CommandParser
 * @Description:  命令解析器
 *
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午3:54:40
 */
public interface CommandParser {
	
	public CommandData parser(String command);
	
	/** 命令解析结果 */
	public static class CommandData {
		
		private final String name;
		private final Map<String,String> options;
		
		public CommandData(String name, Map<String,String> options) {
			this.name = name;
			this.options = options;
		}
		public String getName() {
			return name;
		}
		public Map<String, String> getOptions() {
			return options;
		}
	}

}
