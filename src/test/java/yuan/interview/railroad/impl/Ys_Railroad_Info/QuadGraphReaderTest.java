package yuan.interview.railroad.impl.Ys_Railroad_Info;

import java.io.IOException;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import yuan.interview.railroad.graph.io.GeneralGraphReader;
import yuan.interview.railroad.graph.io.WeightInfo;

/**
 * @ClassName: QuadGraphReaderTest
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午12:26:56
 */
public class QuadGraphReaderTest {
	
	private GeneralGraphReader quadGraphReader = null;
	
	@Before
	public void before() throws IOException {
		quadGraphReader = new QuadGraphReader(null);
	}

	@Test
	public void testRead() {
		List<WeightInfo> results = quadGraphReader.read();
		
		int count = results.size();
		Assert.assertThat("总共读取了27条数据才对", count, CoreMatchers.equalTo(27));
		
		WeightInfo graphEdge0 = results.get(0);
		Assert.assertThat("验证A->B DIST:5出错",graphEdge0.getStart(), CoreMatchers.equalTo("A"));
		Assert.assertThat("验证A->B DIST:5出错",graphEdge0.getEnd(), CoreMatchers.equalTo("B"));
		Assert.assertThat("验证A->B DIST:5出错",graphEdge0.getWeight(), CoreMatchers.equalTo(5));
		Assert.assertThat("验证A->B DIST:5出错",graphEdge0.getDim(), CoreMatchers.equalTo("DIST"));
		
		WeightInfo graphEdge1 = results.get(1);
		Assert.assertThat("验证A->B TIME:9出错",graphEdge1.getStart(), CoreMatchers.equalTo("A"));
		Assert.assertThat("验证A->B TIME:9出错",graphEdge1.getEnd(), CoreMatchers.equalTo("B"));
		Assert.assertThat("验证A->B TIME:9出错",graphEdge1.getWeight(), CoreMatchers.equalTo(9));
		Assert.assertThat("验证A->B TIME:9出错",graphEdge1.getDim(), CoreMatchers.equalTo("TIME"));
		
		WeightInfo graphEdge2 = results.get(2);
		Assert.assertThat("验证A->B STOP:1出错",graphEdge2.getStart(), CoreMatchers.equalTo("A"));
		Assert.assertThat("验证A->B STOP:1出错",graphEdge2.getEnd(), CoreMatchers.equalTo("B"));
		Assert.assertThat("验证A->B STOP:1出错",graphEdge2.getWeight(), CoreMatchers.equalTo(1));
		Assert.assertThat("验证A->B STOP:1出错",graphEdge2.getDim(), CoreMatchers.equalTo("STOP"));
		
		// 后面另的验证省略了.
	}

}
