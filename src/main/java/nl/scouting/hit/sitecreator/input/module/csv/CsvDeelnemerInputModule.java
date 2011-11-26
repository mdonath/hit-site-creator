package nl.scouting.hit.sitecreator.input.module.csv;

import java.util.List;

import javax.swing.filechooser.FileNameExtensionFilter;

import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.HitDeelnemer;
import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitPlaats;

public class CsvDeelnemerInputModule extends
		AbstractCsvFileImportInputModule<HitDeelnemer> {

	public CsvDeelnemerInputModule() {
		super(HitDeelnemer.class);
	}

	@Override
	protected Hit maakStructuur(final List<HitDeelnemer> deelnemers) {
		System.out.println("maak: " + getJaar());
		return new Hit(getJaar() //
				, new HitPlaats("onbekend" //
						, new HitKamp("onbekend" //
								, deelnemers)));
	}

	@Override
	public FileNameExtensionFilter getFilter() {
		return createCsvFilter("Bestand met alle deelnemergegevens");
	}
}
