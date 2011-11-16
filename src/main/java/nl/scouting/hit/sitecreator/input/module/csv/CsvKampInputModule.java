package nl.scouting.hit.sitecreator.input.module.csv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import nl.scouting.hit.sitecreator.input.module.AbstractCsvFileImportInputModule;
import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitPlaats;

public class CsvKampInputModule extends
		AbstractCsvFileImportInputModule<HitKamp> {

	protected CsvKampInputModule() {
		super(HitKamp.class);
	}

	@Override
	protected Hit maakStructuur(final List<HitKamp> kampen) {
		// Maakt de structuur aan tussen de model-entiteiten:
		// [Hit] --< [Plaats] --< [Kamp]
		final Hit hit = new Hit(getJaar());
		final Map<String, HitPlaats> plaatsCache = createPlaatsCache(kampen,
				hit);

		for (final HitKamp kamp : kampen) {
			final HitPlaats plaats = plaatsCache.get(kamp.getPlaatsNaam());
			plaats.addHitKamp(kamp);
		}
		hit.linkKampenAanElkaar();

		return hit;
	}

	protected Map<String, HitPlaats> createPlaatsCache(
			final List<HitKamp> kampen, final Hit hit) {
		final Map<String, HitPlaats> result = new HashMap<String, HitPlaats>();
		for (final String plaatsNaam : createUniekeGesorteerdeSetHitPlaatsNamen(kampen)) {
			final HitPlaats hitPlaats = new HitPlaats(plaatsNaam);
			result.put(plaatsNaam, hitPlaats);
			hit.addHitPlaats(hitPlaats);
		}
		return result;
	}

	protected Set<String> createUniekeGesorteerdeSetHitPlaatsNamen(
			final List<HitKamp> kampen) {
		final Set<String> result = new TreeSet<String>();
		for (final HitKamp kamp : kampen) {
			result.add(kamp.getPlaatsNaam());
		}
		return result;
	}

}
