package nl.scouting.hit.sitecreator.input.module.csv;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Test {@link ColumnMapperFactoryHelper}.
 * 
 * @author Martijn Donath
 */
public class ColumnMapperHelperFactoryTest {
	@Test
	public void load_2012_moet_goed_gaan() throws Exception {
		final ColumnMapperHelper helper = ColumnMapperHelperFactory
				.getColumnMapperHelperForYear(2012);
		assertNotNull(helper);
	}

}
