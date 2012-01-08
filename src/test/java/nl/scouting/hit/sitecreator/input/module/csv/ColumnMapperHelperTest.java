package nl.scouting.hit.sitecreator.input.module.csv;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import nl.scouting.hit.sitecreator.input.module.csv.ColumnMapperHelperFactory.FactoryException;
import nl.scouting.hit.sitecreator.model.HitKamp;

import org.junit.Before;
import org.junit.Test;

public class ColumnMapperHelperTest {

	private Map<String, String> mapping;

	@Before
	public final void setUp() throws Exception {
		final ColumnMapperHelper helper = ColumnMapperHelperFactory
				.getColumnMapperHelperForYear(2012, HitKamp.class);
		mapping = helper.getColumnMapping();
	}

	@Test(expected = FactoryException.class)
	public void verkeerd_jaar() throws Exception {
		final ColumnMapperHelper helper = ColumnMapperHelperFactory
				.getColumnMapperHelperForYear(2000, HitKamp.class);
		helper.getColumnMapping();
	}

	@Test
	public void key_met_spatie_moet_werken() throws Exception {
		assertTrue(mapping.containsKey("HIT-Kamp naam"));
	}

	@Test
	public void key_met_dubbele_punt_moet_werken() throws Exception {
		assertTrue(mapping.toString(),
				mapping.containsKey("De HIT Icoontjes: Staand kamp"));
	}

}
