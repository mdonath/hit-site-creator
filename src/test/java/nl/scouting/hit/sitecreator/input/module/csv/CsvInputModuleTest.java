package nl.scouting.hit.sitecreator.input.module.csv;

import static org.junit.Assert.*;

import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import nl.scouting.hit.sitecreator.input.InputModule;
import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.HitPlaats;
import nl.scouting.hit.sitecreator.model.ModelUtil;
import au.com.bytecode.opencsv.CSVReader;

public class CsvInputModuleTest {

	@Test
	public void testname() throws Exception {
		String temp = "C:/Projects/hit/data/formuliergegevens_5687.csv";
		CsvInputModule input = new CsvInputModule();
		input.propertyChange(new PropertyChangeEvent(this, "file", null,
				new File(temp)));
		Hit result = input.loadCsv();
		assertNotNull(result);
		assertEquals(6, result.getHitPlaatsen());
	}
}
