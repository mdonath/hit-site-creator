package nl.scouting.hit.sitecreator.input.module.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import nl.scouting.hit.sitecreator.input.module.AbstractFileImportInputModule;
import nl.scouting.hit.sitecreator.input.module.csv.ColumnMapperHelperFactory.FactoryException;
import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitPlaats;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class CsvInputModule extends AbstractFileImportInputModule {

	public HeaderColumnNameTranslateMappingStrategy<HitKamp> getStrategy()
			throws FactoryException {
		final HeaderColumnNameTranslateMappingStrategy<HitKamp> strat = new HeaderColumnNameTranslateMappingStrategy<HitKamp>();
		strat.setType(HitKamp.class);

		final ColumnMapperHelper helper = ColumnMapperHelperFactory
				.getColumnMapperHelperForYear(getJaar());
		final Map<String, String> columnMapping = helper.getColumnMapping();
		strat.setColumnMapping(columnMapping);
		return strat;
	}

	@Override
	public Hit load() throws InputModuleException {
		System.out.println("inlezen met " + getEncoding() + " van " + getFile()
				+ " van het jaar " + getJaar());
		return loadCsv();
	}

	protected Hit loadCsv() throws InputModuleException {
		try {
			final CSVReader reader = new CSVReader(new InputStreamReader(
					new FileInputStream(getFile()), getEncoding()));
			final CsvToBean<HitKamp> csv = new CsvToBean<HitKamp>();
			final List<HitKamp> list = csv.parse(getStrategy(), reader);

			return maakStructuur(list);
		} catch (final FileNotFoundException e) {
			throw new InputModuleException(e);
		} catch (final FactoryException e) {
			throw new InputModuleException(e);
		} catch (final UnsupportedEncodingException e) {
			throw new InputModuleException(e);
		}
	}

	public Hit maakStructuur(final List<HitKamp> list) {
		// Maakt de structuur aan tussen de model-entiteiten:
		// [Hit] --< [Plaats] --< [Kamp]
		final Hit hit = new Hit(getJaar());

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

}
