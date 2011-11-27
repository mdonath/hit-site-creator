package nl.scouting.hit.sitecreator.input.module.csv;

import java.util.List;

import javax.swing.filechooser.FileNameExtensionFilter;

import nl.scouting.hit.sitecreator.model.HitDeelnemer;
import nl.scouting.hit.sitecreator.model.HitEntiteit;
import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitPlaats;
import nl.scouting.hit.sitecreator.model.HitProject;

public class CsvDeelnemerInputModule extends
		AbstractCsvFileImportInputModule<HitDeelnemer> {

	public CsvDeelnemerInputModule() {
		super(HitDeelnemer.class);
	}

	@Override
	protected HitProject maakStructuur(final List<HitDeelnemer> deelnemers) {
		System.out.println("maak: " + getJaar());
		return new HitProject(getJaar() //
				, new HitPlaats("onbekend" //
						, new HitKamp("onbekend" //
								, deelnemers)));
	}

	@Override
	public FileNameExtensionFilter getFilter() {
		return createCsvFilter("Bestand met alle deelnemergegevens");
	}

	@Override
	public HitEntiteit getEntityType() {
		return HitEntiteit.Deelnemer;
	}
}
