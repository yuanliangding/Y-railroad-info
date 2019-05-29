package yuanliangding.interview.YRailroadInfo.reader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import yuanliangding.interview.YRailroadInfo.graph.GraphDatum;
import yuanliangding.interview.YRailroadInfo.graph.MapPolicy;
import yuanliangding.interview.YRailroadInfo.graph.SimpleMapPolicy;
import yuanliangding.interview.YRailroadInfo.graph.GraphDatum.Vertex;

/**
 * @ClassName: PlainTextMapReaderTest
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午12:26:56
 */
public class PlainTextMapReaderTest {

	private PlainTextMapReader plainTextMapReader = null;

	private MapPolicy<?,?> mapPolicy = null;
	
	private String textPath = null;

	@Before
	public void before() throws IOException {
		plainTextMapReader = PlainTextMapReader.getInstance();

		mapPolicy = SimpleMapPolicy.getInstance();

		File mapTextFile = File.createTempFile("y_railroad_info_map_plain_text", ".txt");
		textPath = mapTextFile.getCanonicalPath();
		try (FileWriter fileWriter = new FileWriter(mapTextFile)) {
			fileWriter.write("AB5\n");
			fileWriter.write("BC4\n");
			fileWriter.write("CD8\n");
			fileWriter.write("DC8\n");
			fileWriter.write("DE6\n");
			fileWriter.write("AD5\n");
			fileWriter.write("CE2\n");
			fileWriter.write("EB3\n");
			fileWriter.write("AE7\n");
		}
	}

	@After
	public void after() {
		File mapTextFile = new File(textPath);
		mapTextFile.deleteOnExit();
	}

	@Test
	public void testFrom() {
		plainTextMapReader.from(mapPolicy, textPath);
		
		Vertex a = graphDatum.getVertex("A");
		Vertex b = graphDatum.getVertex("B");
		Vertex c = graphDatum.getVertex("C");
		Vertex d = graphDatum.getVertex("D");
		Vertex e = graphDatum.getVertex("E");
		
		Assert.assertThat("验证AB5出错", a.getEdges(SimpleMapPolicy.Weight.DIST.name()).get(b),CoreMatchers.equalTo(5));
		Assert.assertThat("验证AB5出错", a.getEdges(SimpleMapPolicy.Weight.STOP.name()).get(b),CoreMatchers.equalTo(1));
		
		Assert.assertThat("验证BC4出错", b.getEdges(SimpleMapPolicy.Weight.DIST.name()).get(c),CoreMatchers.equalTo(4));
		Assert.assertThat("验证BC4出错", b.getEdges(SimpleMapPolicy.Weight.STOP.name()).get(c),CoreMatchers.equalTo(1));
		
		Assert.assertThat("验证CD8出错", c.getEdges(SimpleMapPolicy.Weight.DIST.name()).get(d),CoreMatchers.equalTo(8));
		Assert.assertThat("验证CD8出错", c.getEdges(SimpleMapPolicy.Weight.STOP.name()).get(d),CoreMatchers.equalTo(1));
		
		Assert.assertThat("验证DC8出错", d.getEdges(SimpleMapPolicy.Weight.DIST.name()).get(c),CoreMatchers.equalTo(8));
		Assert.assertThat("验证DC8出错", d.getEdges(SimpleMapPolicy.Weight.STOP.name()).get(c),CoreMatchers.equalTo(1));
		
		Assert.assertThat("验证DE6出错", d.getEdges(SimpleMapPolicy.Weight.DIST.name()).get(e),CoreMatchers.equalTo(6));
		Assert.assertThat("验证DE6出错", d.getEdges(SimpleMapPolicy.Weight.STOP.name()).get(e),CoreMatchers.equalTo(1));
		
		Assert.assertThat("验证AD5出错", a.getEdges(SimpleMapPolicy.Weight.DIST.name()).get(d),CoreMatchers.equalTo(5));
		Assert.assertThat("验证AD5出错", a.getEdges(SimpleMapPolicy.Weight.STOP.name()).get(d),CoreMatchers.equalTo(1));
		
		Assert.assertThat("验证CE2出错", c.getEdges(SimpleMapPolicy.Weight.DIST.name()).get(e),CoreMatchers.equalTo(2));
		Assert.assertThat("验证CE2出错", c.getEdges(SimpleMapPolicy.Weight.STOP.name()).get(e),CoreMatchers.equalTo(1));
		
		Assert.assertThat("验证EB3出错", e.getEdges(SimpleMapPolicy.Weight.DIST.name()).get(b),CoreMatchers.equalTo(3));
		Assert.assertThat("验证EB3出错", e.getEdges(SimpleMapPolicy.Weight.STOP.name()).get(b),CoreMatchers.equalTo(1));
		
		Assert.assertThat("验证AE7出错", a.getEdges(SimpleMapPolicy.Weight.DIST.name()).get(e),CoreMatchers.equalTo(7));
		Assert.assertThat("验证AE7出错", a.getEdges(SimpleMapPolicy.Weight.STOP.name()).get(e),CoreMatchers.equalTo(1));
	}

}
