package nl.scouting.hit.sitecreator.input.module.csv;

import java.util.List;

import nl.scouting.hit.sitecreator.input.module.AbstractCsvFileImportInputModule;
import nl.scouting.hit.sitecreator.model.Hit;

public class CsvProjectInputModule extends
		AbstractCsvFileImportInputModule<Hit> {

	protected CsvProjectInputModule() {
		super(Hit.class);
	}

	@Override
	protected Hit maakStructuur(final List<Hit> projecten) {
		return projecten.get(0);
	}

}
