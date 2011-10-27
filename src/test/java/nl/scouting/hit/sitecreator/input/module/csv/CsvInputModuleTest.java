package nl.scouting.hit.sitecreator.input.module.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import nl.scouting.hit.sitecreator.model.Hit;

import org.junit.Test;

public class CsvInputModuleTest {

	@Test
	public void aantal_hitplaatsen_moet_compleet_zijn() throws Exception {
		final File tempFile = loadClasspathResourceToTempFile("nl/scouting/hit/sitecreator/input/module/csv/formuliergegevens_5687_anon.csv");
		final CsvInputModule input = new CsvInputModule();
		input.propertyChange(new PropertyChangeEvent(this, "file", null,
				tempFile));
		input.propertyChange(new PropertyChangeEvent(this, "jaar", null,
				Integer.valueOf(2012)));
		input.propertyChange(new PropertyChangeEvent(this, "encoding", null,
				"UTF-8"));
		final Hit result = input.loadCsv();
		assertNotNull(result);
		assertEquals(6, result.getHitPlaatsen().size());
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
