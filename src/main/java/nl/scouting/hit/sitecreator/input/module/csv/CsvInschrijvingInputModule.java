package nl.scouting.hit.sitecreator.input.module.csv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.filechooser.FileNameExtensionFilter;

import nl.scouting.hit.sitecreator.model.HitEntiteit;
import nl.scouting.hit.sitecreator.model.HitInschrijving;
import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitPlaats;
import nl.scouting.hit.sitecreator.model.HitProject;

public class CsvInschrijvingInputModule extends
		AbstractCsvFileImportInputModule<HitInschrijving> {

	public CsvInschrijvingInputModule() {
		super(HitInschrijving.class);
	}

	@Override
	protected HitProject maakStructuur(
			final List<HitInschrijving> inschrijvingen) {
		// Maakt de structuur aan tussen de model-entiteiten:
		// [Project] --< [Plaats] --< [Kamp] -- [Inschrijving]
		final HitProject hit = new HitProject(getJaar());
		final Map<String, HitPlaats> plaatsCache = createPlaatsCache(
				inschrijvingen, hit);

		for (final HitInschrijving inschrijving : inschrijvingen) {
			final String locatie = inschrijving.getLocatie();
			if (isLocatieEenHitPlaats(locatie)) {
				final HitPlaats plaats = plaatsCache.get(locatie);
				plaats.addHitKamp(new HitKamp(inschrijving));
			}
		}

		return hit;
	}

	protected static Map<String, HitPlaats> createPlaatsCache(
			final List<HitInschrijving> kampen, final HitProject hit) {
		final Map<String, HitPlaats> result = new HashMap<String, HitPlaats>();
		final Set<String> uniek = createUniekeGesorteerdeSetHitPlaatsNamen(kampen);
		for (final String plaatsNaam : uniek) {
			final HitPlaats hitPlaats = new HitPlaats(plaatsNaam);
			result.put(plaatsNaam, hitPlaats);
			hit.addHitPlaats(hitPlaats);
		}
		return result;
	}

	protected static Set<String> createUniekeGesorteerdeSetHitPlaatsNamen(
			final List<HitInschrijving> inschrijvingen) {
		final Set<String> result = new TreeSet<String>();
		for (final HitInschrijving inschrijving : inschrijvingen) {
			final String locatie = inschrijving.getLocatie();
			if (isLocatieEenHitPlaats(locatie)) {
				result.add(locatie);
			}
		}
		return result;
	}

	private static boolean isLocatieEenHitPlaats(final String locatie) {
		return (locatie != null) && !locatie.isEmpty()
				&& !locatie.startsWith("Algemeen");
	}

	@Override
	public FileNameExtensionFilter getFilter() {
		return createCsvFilter("Bestand met alle inschrijfgegevens");
	}

	@Override
	public HitEntiteit getEntityType() {
		return HitEntiteit.Inschrijving;
	}

}
