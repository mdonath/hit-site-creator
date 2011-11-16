package nl.scouting.hit.sitecreator.input.module.csv;

import java.util.List;

import nl.scouting.hit.sitecreator.input.module.AbstractCsvFileImportInputModule;
import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.HitPlaats;

public class CsvPlaatsInputModule extends
		AbstractCsvFileImportInputModule<HitPlaats> {

	protected CsvPlaatsInputModule() {
		super(HitPlaats.class);
	}

	@Override
	protected Hit maakStructuur(final List<HitPlaats> list) {
		return new Hit(getJaar(), list);
	}

}
