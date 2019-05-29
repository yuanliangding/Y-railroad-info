package yuanliangding.interview.YRailroadInfo.map.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/** 
 * @ClassName: SimpleYRailroadContextTest
 * @Description:  集成测试
 *
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月28日-下午7:56:23
 */
@RunWith(Parameterized.class)
public class SimpleYRailroadContextTest  extends TWDataProvider {
	
	private String command = null;
	
	private String result = null;

	public SimpleYRailroadContextTest(String command, String result) {
		this.command = command;
		this.result = result;
	}

	@Parameters
	public static List<List<String>> data() {
		List<List<String>> dataSet = new ArrayList<>();
		dataSet.add(Arrays.asList("dist -p A-B-C","9"));
		dataSet.add(Arrays.asList("dist -p A-D","5"));
		dataSet.add(Arrays.asList("dist -p A-D-C","13"));
		dataSet.add(Arrays.asList("dist -p A-E-B-C-D","22"));
		dataSet.add(Arrays.asList("dist -p A-E-D","NO SUCH ROUTE"));
		dataSet.add(Arrays.asList("count -f s -b C -e C -M 3","2"));
		dataSet.add(Arrays.asList("count -f s -b A -e C -m 4 -M 4","3"));
		dataSet.add(Arrays.asList("dist -f md -b A -e C","9"));
		dataSet.add(Arrays.asList("dist -f md -b B -e B","9"));
		dataSet.add(Arrays.asList("count -f d -b C -e C -M n30","7"));
		
		return dataSet;
	}
	
	

}
