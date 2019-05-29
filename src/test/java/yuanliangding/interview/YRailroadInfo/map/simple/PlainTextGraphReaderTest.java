package yuanliangding.interview.YRailroadInfo.map.simple;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import yuanliangding.interview.YRailroadInfo.graph.GraphReader.GraphEdge;

/**
 * @ClassName: PlainTextGraphReaderTest
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午12:26:56
 */
public class PlainTextGraphReaderTest {

	private String textPath = null;
	
	private PlainTextGraphReader plainTextGraphReader = null;

	@Before
	public void before() throws IOException {
		File mapTextFile = File.createTempFile("y_railroad_info_map_plain_text", ".txt");
		textPath = mapTextFile.getCanonicalPath();
		try (FileWriter fileWriter = new FileWriter(mapTextFile)) {
			fileWriter.write("AB5\nBC4\nCD8\n");
		}
		
		plainTextGraphReader = new PlainTextGraphReader(textPath);
	}

	@After
	public void after() {
		File mapTextFile = new File(textPath);
		mapTextFile.deleteOnExit();
	}

	@Test
	public void testRead() {
		List<GraphEdge> results = plainTextGraphReader.read();
		
		int count = results.size();
		Assert.assertThat("总共读取了3条数据才对", count, CoreMatchers.equalTo(3));
		
		GraphEdge graphEdge0 = results.get(0);
		Assert.assertThat("验证AB5出错",graphEdge0.getStart(), CoreMatchers.equalTo("A"));
		Assert.assertThat("验证AB5出错",graphEdge0.getEnd(), CoreMatchers.equalTo("B"));
		Assert.assertThat("验证AB5出错",graphEdge0.getWeightValue(), CoreMatchers.equalTo(5));
		Assert.assertThat("验证AB5出错",graphEdge0.getWeightName(), CoreMatchers.nullValue());
		
		GraphEdge graphEdge1 = results.get(1);
		Assert.assertThat("验证BC4出错",graphEdge1.getStart(), CoreMatchers.equalTo("B"));
		Assert.assertThat("验证BC4出错",graphEdge1.getEnd(), CoreMatchers.equalTo("C"));
		Assert.assertThat("验证BC4出错",graphEdge1.getWeightValue(), CoreMatchers.equalTo(4));
		Assert.assertThat("验证BC4出错",graphEdge1.getWeightName(), CoreMatchers.nullValue());
		
		GraphEdge graphEdge2 = results.get(2);
		Assert.assertThat("验证CD8出错",graphEdge2.getStart(), CoreMatchers.equalTo("C"));
		Assert.assertThat("验证CD8出错",graphEdge2.getEnd(), CoreMatchers.equalTo("D"));
		Assert.assertThat("验证CD8出错",graphEdge2.getWeightValue(), CoreMatchers.equalTo(8));
		Assert.assertThat("验证CD8出错",graphEdge2.getWeightName(), CoreMatchers.nullValue());
	}

}
