package nl.scouting.hit.sitecreator.input.module.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.HitKamp;

import org.junit.Test;

public class CsvInputModuleTest {

	@Test
	public void aantal_hitplaatsen_moet_compleet_zijn() throws Exception {
		final CsvInputModule input = new CsvInputModule();
		input.setFile(loadClasspathResourceToTempFile("nl/scouting/hit/sitecreator/input/module/csv/formuliergegevens_5687_anon.csv"));
		input.setJaar(2012);
		input.setEncoding("UTF-8");

		final Hit result = input.load();
		assertNotNull(result);
		assertEquals(6, result.getHitPlaatsen().size());
	}

	@Test
	public void startdatum() throws Exception {
		final CsvInputModule input = new CsvInputModule();
		input.setFile(loadClasspathResourceToTempFile("nl/scouting/hit/sitecreator/input/module/csv/formuliergegevens_5687_anon.csv"));
		input.setJaar(2012);
		input.setEncoding("UTF-8");
		final Hit hit = input.load();
		final HitKamp kamp = hit.getHitPlaatsen().get(0).getHitKampen().get(0);

		assertEquals(2012, kamp.getStartDatumtijd().getYear());
		assertEquals(4, kamp.getStartDatumtijd().getMonthOfYear());
		assertEquals(6, kamp.getStartDatumtijd().getDayOfMonth());

		assertEquals(19, kamp.getStartDatumtijd().getHourOfDay());
		assertEquals(0, kamp.getStartDatumtijd().getMinuteOfHour());
		assertEquals(0, kamp.getStartDatumtijd().getSecondOfMinute());

	}

	private File loadClasspathResourceToTempFile(final String resource)
			throws IOException, FileNotFoundException {
		final File tempFile = File.createTempFile(getClass().getName(), ".csv");
		final InputStream is = getClass().getClassLoader().getResourceAsStream(
				resource);
		if (is == null) {
			fail("Resource niet gevonden: '" + resource + "'");
		}
		final OutputStream os = new FileOutputStream(tempFile);
		try {
			final byte[] buf = new byte[512];
			int read = 0;
			while ((read = is.read(buf)) != -1) {
				os.write(buf, 0, read);
			}
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
		}
		tempFile.deleteOnExit();
		return tempFile;
	}
}
