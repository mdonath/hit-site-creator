package nl.scouting.hit.sitecreator.input.module.csv;

import java.util.List;

import javax.swing.filechooser.FileNameExtensionFilter;

import nl.scouting.hit.sitecreator.model.HitEntiteit;
import nl.scouting.hit.sitecreator.model.HitProject;

public class CsvProjectInputModule extends
		AbstractCsvFileImportInputModule<HitProject> {

	public CsvProjectInputModule() {
		super(HitProject.class);
	}

	@Override
	protected HitProject maakStructuur(final List<HitProject> projecten) {
		return projecten.get(0);
	}

	@Override
	public FileNameExtensionFilter getFilter() {
		return createCsvFilter("Bestand met de HIT Project gegevens");
	}

	@Override
	public HitEntiteit getEntityType() {
		return HitEntiteit.Project;
	}
}
