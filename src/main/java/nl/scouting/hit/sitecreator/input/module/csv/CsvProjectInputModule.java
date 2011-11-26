package nl.scouting.hit.sitecreator.input.module.csv;

import java.util.List;

import javax.swing.filechooser.FileNameExtensionFilter;

import nl.scouting.hit.sitecreator.model.Hit;

public class CsvProjectInputModule extends
		AbstractCsvFileImportInputModule<Hit> {

	public CsvProjectInputModule() {
		super(Hit.class);
	}

	@Override
	protected Hit maakStructuur(final List<Hit> projecten) {
		return projecten.get(0);
	}

	@Override
	public FileNameExtensionFilter getFilter() {
		return createCsvFilter("Bestand met de HIT Project gegevens");
	}

}
