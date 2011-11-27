package nl.scouting.hit.sitecreator.input.module.csv;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import nl.scouting.hit.sitecreator.input.module.csv.ColumnMapperHelperFactory.FactoryException;
import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitPlaats;
import nl.scouting.hit.sitecreator.model.HitProject;

import org.junit.Test;

/**
 * Test {@link ColumnMapperFactoryHelper}.
 * 
 * @author Martijn Donath
 */
public class ColumnMapperHelperFactoryTest {
	@Test
	public void load_2012_moet_goed_gaan_voor_kamp() throws Exception {
		final ColumnMapperHelper helper = ColumnMapperHelperFactory
				.getKampColumnMapperHelperForYear(2012);
		assertNotNull(helper);
	}

	@Test
	public void load_2012_moet_goed_gaan_voor_plaats() throws Exception {
		final ColumnMapperHelper helper = ColumnMapperHelperFactory
				.getPlaatsColumnMapperHelperForYear(2012);
		assertNotNull(helper);
	}

	@Test
	public void generieke_method_met_class_voor_kamp() throws Exception {
		final ColumnMapperHelper helper = ColumnMapperHelperFactory
				.getColumnMapperHelperForYear(2012, HitKamp.class);
		assertNotNull(helper);
	}

	@Test
	public void generieke_method_met_class_voor_plaats() throws Exception {
		final ColumnMapperHelper helper = ColumnMapperHelperFactory
				.getColumnMapperHelperForYear(2012, HitPlaats.class);
		assertNotNull(helper);
	}

	@Test
	public void generieke_method_met_class_voor_project() throws Exception {
		final ColumnMapperHelper helper = ColumnMapperHelperFactory
				.getColumnMapperHelperForYear(2012, HitProject.class);
		assertNotNull(helper);
		assertTrue(helper.getColumnMapping().containsKey("jaar"));
	}

	@Test(expected = FactoryException.class)
	public void generieke_method_met_verkeerde_class() throws Exception {
		ColumnMapperHelperFactory.getColumnMapperHelperForYear(2012,
				String.class);
	}
}
