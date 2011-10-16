package nl.scouting.hit.sitecreator.input.module.csv;

import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import nl.scouting.hit.sitecreator.input.InputModule;
import nl.scouting.hit.sitecreator.input.module.csv.ColumnMapperHelperFactory.FactoryException;
import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitPlaats;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class CsvInputModule implements InputModule {

	private File file;
	private Integer jaar;

	public HeaderColumnNameTranslateMappingStrategy<HitKamp> getStrategy()
			throws FactoryException {
		final HeaderColumnNameTranslateMappingStrategy<HitKamp> strat = new HeaderColumnNameTranslateMappingStrategy<HitKamp>();
		strat.setType(HitKamp.class);

		final Map<String, String> columnMapping = ColumnMapperHelperFactory
				.getColumnMapperHelperForYear(this.jaar).getColumnMapping();
		strat.setColumnMapping(columnMapping);
		return strat;
	}

	@Override
	public Hit load() throws InputModuleException {
		System.out.println("inlezen " + this.file + " van het jaar "
				+ this.jaar);
		return loadCsv();
	}

	protected Hit loadCsv() throws InputModuleException {
		try {
			final CSVReader reader = new CSVReader(new FileReader(this.file));
			final CsvToBean<HitKamp> csv = new CsvToBean<HitKamp>();
			final List<HitKamp> list = csv.parse(getStrategy(), reader);

			return maakStructuur(list);
		} catch (final FileNotFoundException e) {
			throw new InputModuleException(e);
		} catch (final FactoryException e) {
			throw new InputModuleException(e);
		}
	}

	public Hit maakStructuur(final List<HitKamp> list) {
		// Maakt de structuur aan tussen de model-entiteiten:
		// [Hit] --< [Plaats] --< [Kamp]
		final Hit hit = new Hit(this.jaar);

		final Set<String> plaatsNamen = new TreeSet<String>();
		for (final HitKamp kamp : list) {
			plaatsNamen.add(kamp.getPlaatsNaam());
		}
		final Map<String, HitPlaats> plaatsCache = new HashMap<String, HitPlaats>();
		for (final String plaatsNaam : plaatsNamen) {
			final HitPlaats hitPlaats = new HitPlaats(plaatsNaam);
			plaatsCache.put(plaatsNaam, hitPlaats);
			hit.addHitPlaats(hitPlaats);
		}
		for (final HitKamp kamp : list) {
			final HitPlaats plaats = plaatsCache.get(kamp.getPlaatsNaam());
			plaats.addHitKamp(kamp);
		}
		return hit;
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		final String propertyName = evt.getPropertyName();
		if ("file".equals(propertyName)) {
			this.file = (File) evt.getNewValue();
		} else if ("jaar".equals(propertyName)) {
			this.jaar = (Integer) evt.getNewValue();
		}
	}
}
