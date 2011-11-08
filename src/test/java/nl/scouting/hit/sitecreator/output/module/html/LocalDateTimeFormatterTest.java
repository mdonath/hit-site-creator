package nl.scouting.hit.sitecreator.output.module.html;

import static org.junit.Assert.assertEquals;

import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class LocalDateTimeFormatterTest {

	@Test
	public void testName() throws Exception {
		final STGroup group = new STGroupFile(
				"nl/scouting/hit/sitecreator/output/module/html/jodatime_localdatetime.stg",
				'$', '$');
		group.registerRenderer(LocalDateTime.class, new LocalDateTimeRenderer());
		final ST st = group.getInstanceOf("local_datetime");
		st.add("ldt", new LocalDateTime("2011-11-08T13:37:00"));
		final String result = st.render();
		final String[] dates = result.split("\\n");
		assertEquals("2011", dates[0]);
		assertEquals("08-11-2011", dates[1]);
		assertEquals("8 november 2011", dates[2]);
		assertEquals("13:37", dates[3]);
	}
}
