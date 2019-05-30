package yuanliangding.interview.YRailroadInfo.map.simple;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import yuanliangding.interview.YRailroadInfo.graph.base.GraphReader.EdgeData;

/**
 * @ClassName: PlainTextGraphReaderTest
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午12:26:56
 */
public class PlainTextGraphReaderTest extends TWDataProvider{

	@Test
	public void testRead() {
		List<EdgeData> results = graphReader.read();
		
		int count = results.size();
		Assert.assertThat("总共读取了9条数据才对", count, CoreMatchers.equalTo(9));
		
		EdgeData graphEdge0 = results.get(0);
		Assert.assertThat("验证AB5出错",graphEdge0.getStart(), CoreMatchers.equalTo("A"));
		Assert.assertThat("验证AB5出错",graphEdge0.getEnd(), CoreMatchers.equalTo("B"));
		Assert.assertThat("验证AB5出错",graphEdge0.getWeight(), CoreMatchers.equalTo(5));
		Assert.assertThat("验证AB5出错",graphEdge0.getLayer(), CoreMatchers.nullValue());
		
		EdgeData graphEdge1 = results.get(1);
		Assert.assertThat("验证BC4出错",graphEdge1.getStart(), CoreMatchers.equalTo("B"));
		Assert.assertThat("验证BC4出错",graphEdge1.getEnd(), CoreMatchers.equalTo("C"));
		Assert.assertThat("验证BC4出错",graphEdge1.getWeight(), CoreMatchers.equalTo(4));
		Assert.assertThat("验证BC4出错",graphEdge1.getLayer(), CoreMatchers.nullValue());
		
		EdgeData graphEdge2 = results.get(2);
		Assert.assertThat("验证CD8出错",graphEdge2.getStart(), CoreMatchers.equalTo("C"));
		Assert.assertThat("验证CD8出错",graphEdge2.getEnd(), CoreMatchers.equalTo("D"));
		Assert.assertThat("验证CD8出错",graphEdge2.getWeight(), CoreMatchers.equalTo(8));
		Assert.assertThat("验证CD8出错",graphEdge2.getLayer(), CoreMatchers.nullValue());
	}

}
