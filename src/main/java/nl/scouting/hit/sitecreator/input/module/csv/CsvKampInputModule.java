package nl.scouting.hit.sitecreator.input.module.csv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.filechooser.FileNameExtensionFilter;

import nl.scouting.hit.sitecreator.model.HitEntiteit;
import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitPlaats;
import nl.scouting.hit.sitecreator.model.HitProject;

public class CsvKampInputModule extends
		AbstractCsvFileImportInputModule<HitKamp> {

	public CsvKampInputModule() {
		super(HitKamp.class);
	}

	@Override
	protected HitProject maakStructuur(final List<HitKamp> kampen) {
		// Maakt de structuur aan tussen de model-entiteiten:
		// [Hit] --< [Plaats] --< [Kamp]
		final HitProject hit = new HitProject(getJaar());
		final Map<String, HitPlaats> plaatsCache = createPlaatsCache(kampen,
				hit);

		for (final HitKamp kamp : kampen) {
			final HitPlaats plaats = plaatsCache.get(kamp.getPlaatsNaam());
			plaats.addHitKamp(kamp);
		}
		hit.linkKampenAanElkaar();

		return hit;
	}

	protected static Map<String, HitPlaats> createPlaatsCache(
			final List<HitKamp> kampen, final HitProject hit) {
		final Map<String, HitPlaats> result = new HashMap<String, HitPlaats>();
		for (final String plaatsNaam : createUniekeGesorteerdeSetHitPlaatsNamen(kampen)) {
			final HitPlaats hitPlaats = new HitPlaats(plaatsNaam);
			result.put(plaatsNaam, hitPlaats);
			hit.addHitPlaats(hitPlaats);
		}
		return result;
	}

	protected static Set<String> createUniekeGesorteerdeSetHitPlaatsNamen(
			final List<HitKamp> kampen) {
		final Set<String> result = new TreeSet<String>();
		for (final HitKamp kamp : kampen) {
			result.add(kamp.getPlaatsNaam());
		}
		return result;
	}

	@Override
	public FileNameExtensionFilter getFilter() {
		return createCsvFilter("Bestand met alle kampgegevens");
	}

	@Override
	public HitEntiteit getEntityType() {
		return HitEntiteit.Kamp;
	}

}
