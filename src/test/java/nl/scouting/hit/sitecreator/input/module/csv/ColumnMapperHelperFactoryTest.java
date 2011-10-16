package nl.scouting.hit.sitecreator.input.module.csv;

import static org.junit.Assert.*;

import nl.scouting.hit.sitecreator.input.module.csv.ColumnMapperHelperFactory.FactoryException;

import org.junit.Test;

/**
 * Test {@link ColumnMapperFactoryHelper}.
 * 
 * @author Martijn Donath
 */
public class ColumnMapperHelperFactoryTest {
	@Test
	public void load_2011_moet_goed_gaan() throws Exception {
		ColumnMapperHelper helper = ColumnMapperHelperFactory
				.getColumnMapperHelperForYear(2011);
		assertNotNull(helper);
	}

	@Test(expected = FactoryException.class)
	public void load_2000_moet_fout_gaan() throws Exception {
		ColumnMapperHelperFactory.getColumnMapperHelperForYear(2000);
	}
}
