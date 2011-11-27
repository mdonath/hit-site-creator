package nl.scouting.hit.sitecreator.input.module.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;

import nl.scouting.hit.sitecreator.model.HitProject;

import org.junit.Test;

public class CsvProjectInputModuleTest {
	@Test
	public void testName() throws Exception {
		// Haal het import bestand uit het test package
		final CsvPlaatsInputModule module = new CsvPlaatsInputModule() {
			@Override
			protected InputStream getInputStream() throws IOException {
				return getClass()
						.getResourceAsStream(
								"/nl/scouting/hit/sitecreator/input/module/csv/hit-algemeen.csv");
			}
		};
		module.setJaar(2012);
		module.setEncoding("UTF-8");
		final HitProject hit = module.load();
		assertNotNull(hit);
		assertEquals(0, hit.getAantalKampen());
		assertEquals("jaar", Integer.valueOf(2012), hit.getJaar());
	}
}
