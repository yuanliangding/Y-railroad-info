package yuan.interview.railroad.interactive;

import java.util.Map;

/**
 * @ClassName: CommandData
 * @Description:命令数据,命令解析结果
 */
public class CommandData {
	
	// 命令名称
	private final String name;
	// 命令选项参数,由参数名-参数值对组成
	private final Map<String, String> options;

	public CommandData(String name, Map<String, String> options) {
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