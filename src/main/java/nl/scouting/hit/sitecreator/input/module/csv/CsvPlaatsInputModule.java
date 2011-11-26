package nl.scouting.hit.sitecreator.input.module.csv;

import java.util.List;

import javax.swing.filechooser.FileNameExtensionFilter;

import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.HitPlaats;

public class CsvPlaatsInputModule extends
		AbstractCsvFileImportInputModule<HitPlaats> {

	public CsvPlaatsInputModule() {
		super(HitPlaats.class);
	}

	@Override
	protected Hit maakStructuur(final List<HitPlaats> list) {
		return new Hit(getJaar(), list);
	}

	@Override
	public FileNameExtensionFilter getFilter() {
		return createCsvFilter("Bestand met alle kampplaatsgegevens");
	}

}
