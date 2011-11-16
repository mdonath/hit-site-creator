package nl.scouting.hit.sitecreator.input.module.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.HitPlaats;

import org.junit.Test;

public class CsvPlaatsInputModuleTest {
	@Test
	public void testName() throws Exception {
		// Haal het import bestand uit het test package
		final CsvPlaatsInputModule module = new CsvPlaatsInputModule() {
			@Override
			protected InputStream getInputStream() throws IOException {
				return getClass()
						.getResourceAsStream(
								"/nl/scouting/hit/sitecreator/input/module/csv/formuliergegevens_5686_anon.csv");
			}
		};
		module.setJaar(2012);
		module.setEncoding("UTF-8");
		final Hit hit = module.load();
		assertNotNull(hit);
		final Iterator<HitPlaats> iterator = hit.getHitPlaatsen().iterator();
		assertEquals("Alphen", iterator.next().getNaam());
		assertEquals("Dwingeloo", iterator.next().getNaam());
		assertEquals("Harderwijk", iterator.next().getNaam());
		assertEquals("Hilversum", iterator.next().getNaam());
		assertEquals("Mook", iterator.next().getNaam());
		assertEquals("Zeeland", iterator.next().getNaam());
	}
}
