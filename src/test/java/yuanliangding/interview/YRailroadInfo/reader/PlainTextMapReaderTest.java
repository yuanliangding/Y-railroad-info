package yuanliangding.interview.YRailroadInfo.reader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import yuanliangding.interview.YRailroadInfo.map.SimpleMapPolicy;
import yuanliangding.interview.YRailroadInfo.map.Stop;
import yuanliangding.interview.YRailroadInfo.map.MapDatum;

/**
 * @ClassName: PlainTextMapReaderTest
 *
 * @author 袁良锭(https://github.com/yuanliangding)
 * @date 2019年5月27日-上午12:26:56
 */
public class PlainTextMapReaderTest {

	private PlainTextMapReader plainTextMapReader = null;

	private MapDatum mapDatum = null;
	private String textPath = null;

	@Before
	public void before() throws IOException {
		plainTextMapReader = PlainTextMapReader.getInstance();

		mapDatum = MapDatum.getInstance();

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
		plainTextMapReader.from(mapDatum, textPath);
		
		Stop a = mapDatum.getStop("A");
		Stop b = mapDatum.getStop("B");
		Stop c = mapDatum.getStop("C");
		Stop d = mapDatum.getStop("D");
		Stop e = mapDatum.getStop("E");
		
		Assert.assertThat("验证AB5出错", a.getNexts(SimpleMapPolicy.Weight.DIST.name()).get(b),CoreMatchers.equalTo(5));
		Assert.assertThat("验证AB5出错", a.getNexts(SimpleMapPolicy.Weight.STOP.name()).get(b),CoreMatchers.equalTo(1));
		
		Assert.assertThat("验证BC4出错", b.getNexts(SimpleMapPolicy.Weight.DIST.name()).get(c),CoreMatchers.equalTo(4));
		Assert.assertThat("验证BC4出错", b.getNexts(SimpleMapPolicy.Weight.STOP.name()).get(c),CoreMatchers.equalTo(1));
		
		Assert.assertThat("验证CD8出错", c.getNexts(SimpleMapPolicy.Weight.DIST.name()).get(d),CoreMatchers.equalTo(8));
		Assert.assertThat("验证CD8出错", c.getNexts(SimpleMapPolicy.Weight.STOP.name()).get(d),CoreMatchers.equalTo(1));
		
		Assert.assertThat("验证DC8出错", d.getNexts(SimpleMapPolicy.Weight.DIST.name()).get(c),CoreMatchers.equalTo(8));
		Assert.assertThat("验证DC8出错", d.getNexts(SimpleMapPolicy.Weight.STOP.name()).get(c),CoreMatchers.equalTo(1));
		
		Assert.assertThat("验证DE6出错", d.getNexts(SimpleMapPolicy.Weight.DIST.name()).get(e),CoreMatchers.equalTo(6));
		Assert.assertThat("验证DE6出错", d.getNexts(SimpleMapPolicy.Weight.STOP.name()).get(e),CoreMatchers.equalTo(1));
		
		Assert.assertThat("验证AD5出错", a.getNexts(SimpleMapPolicy.Weight.DIST.name()).get(d),CoreMatchers.equalTo(5));
		Assert.assertThat("验证AD5出错", a.getNexts(SimpleMapPolicy.Weight.STOP.name()).get(d),CoreMatchers.equalTo(1));
		
		Assert.assertThat("验证CE2出错", c.getNexts(SimpleMapPolicy.Weight.DIST.name()).get(e),CoreMatchers.equalTo(2));
		Assert.assertThat("验证CE2出错", c.getNexts(SimpleMapPolicy.Weight.STOP.name()).get(e),CoreMatchers.equalTo(1));
		
		Assert.assertThat("验证EB3出错", e.getNexts(SimpleMapPolicy.Weight.DIST.name()).get(b),CoreMatchers.equalTo(3));
		Assert.assertThat("验证EB3出错", e.getNexts(SimpleMapPolicy.Weight.STOP.name()).get(b),CoreMatchers.equalTo(1));
		
		Assert.assertThat("验证AE7出错", a.getNexts(SimpleMapPolicy.Weight.DIST.name()).get(e),CoreMatchers.equalTo(7));
		Assert.assertThat("验证AE7出错", a.getNexts(SimpleMapPolicy.Weight.STOP.name()).get(e),CoreMatchers.equalTo(1));
	}

}
