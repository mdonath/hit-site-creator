package nl.scouting.hit.sitecreator.input.module.csv;

import java.util.List;

import javax.swing.filechooser.FileNameExtensionFilter;

import nl.scouting.hit.sitecreator.model.HitEntiteit;
import nl.scouting.hit.sitecreator.model.HitPlaats;
import nl.scouting.hit.sitecreator.model.HitProject;

public class CsvPlaatsInputModule extends
		AbstractCsvFileImportInputModule<HitPlaats> {

	public CsvPlaatsInputModule() {
		super(HitPlaats.class);
	}

	@Override
	protected HitProject maakStructuur(final List<HitPlaats> list) {
		return new HitProject(getJaar(), list);
	}

	@Override
	public FileNameExtensionFilter getFilter() {
		return createCsvFilter("Bestand met alle kampplaatsgegevens");
	}

	@Override
	public HitEntiteit getEntityType() {
		return HitEntiteit.Plaats;
	}
}
