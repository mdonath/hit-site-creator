package nl.scouting.hit.sitecreator.input.module.csv;

import java.util.List;

import nl.scouting.hit.sitecreator.input.module.AbstractCsvFileImportInputModule;
import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.HitDeelnemer;
import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitPlaats;

public class CsvDeelnemerInputModule extends
		AbstractCsvFileImportInputModule<HitDeelnemer> {

	protected CsvDeelnemerInputModule() {
		super(HitDeelnemer.class);
	}

	@Override
	protected Hit maakStructuur(final List<HitDeelnemer> deelnemers) {
		return new Hit(getJaar() //
				, new HitPlaats("onbekend" //
						, new HitKamp("onbekend" //
								, deelnemers)));
	}
}
