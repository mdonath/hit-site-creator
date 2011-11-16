package nl.scouting.hit.sitecreator;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

public class ParametersTest {

	@Test
	public void testName() throws Exception {
		final String[] arguments = { "-jaar", "2012", "-kampenc", "UTF-8" };
		final Parameters p = new Parameters(arguments);
		final Map<String, String> configuration = p.getConfiguration();
		assertEquals("2012", configuration.get("jaar"));
		assertEquals("UTF-8", configuration.get("kampenc"));
	}
}
