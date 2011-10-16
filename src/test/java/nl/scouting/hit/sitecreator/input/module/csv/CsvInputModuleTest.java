package nl.scouting.hit.sitecreator.input.module.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.beans.PropertyChangeEvent;
import java.io.File;

import nl.scouting.hit.sitecreator.model.Hit;

import org.junit.Test;

public class CsvInputModuleTest {

	@Test
	public void testname() throws Exception {
		final String temp = "C:/Projects/hit/data/formuliergegevens_5687.csv";
		final CsvInputModule input = new CsvInputModule();
		input.propertyChange(new PropertyChangeEvent(this, "file", null,
				new File(temp)));
		input.propertyChange(new PropertyChangeEvent(this, "jaar", null,
				Integer.valueOf(2011)));

		final Hit result = input.loadCsv();
		assertNotNull(result);
		assertEquals(6, result.getHitPlaatsen().size());
	}
}
