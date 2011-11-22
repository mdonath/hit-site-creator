package nl.scouting.hit.sitecreator.output.module.html;

import static org.junit.Assert.assertEquals;

import org.joda.time.LocalDateTime;
import org.junit.Test;

public class LocalDateTimeRendererTest {

	@Test
	public void datum_met_tijd() throws Exception {
		final String result = new LocalDateTimeRenderer().toString(
				new LocalDateTime(2011, 11, 22, 13, 37), null, null);
		assertEquals("2011-11-22 13:37:00", result);
	}
}
