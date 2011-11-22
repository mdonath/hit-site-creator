package nl.scouting.hit.sitecreator.output.module.html;

import static org.junit.Assert.assertEquals;

import org.joda.time.LocalDate;
import org.junit.Test;

public class LocalDateRendererTest {
	@Test
	public void gewone_default_datum() throws Exception {
		final String result = new LocalDateRenderer().toString(new LocalDate(
				2011, 11, 22), null, null);
		assertEquals("2011-11-22", result);
	}
}
