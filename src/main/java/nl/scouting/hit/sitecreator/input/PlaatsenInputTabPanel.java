package nl.scouting.hit.sitecreator.input;

import java.awt.BorderLayout;

import nl.scouting.hit.sitecreator.ApplicationLabels;
import nl.scouting.hit.sitecreator.input.module.FileImportModel;
import nl.scouting.hit.sitecreator.input.module.csv.CsvPlaatsInputModule;

public class PlaatsenInputTabPanel extends AbstractInputTabPanel {

	private static final long serialVersionUID = 1L;

	public PlaatsenInputTabPanel(final FileImportModel plaatsCsvModel) {
		super(ApplicationLabels.getLabel("panel.input.plaatsen"));
		initComponents(plaatsCsvModel);
	}

	private void initComponents(final FileImportModel plaatsCsvModel) {
		add(createTabPanel(createCsvImportPanel(plaatsCsvModel)),
				BorderLayout.CENTER);
		add(createButtonPanel(), BorderLayout.SOUTH);
	}

	private static FileImportPanel createCsvImportPanel(
			final FileImportModel csvModel) {
		return new FileImportPanel(csvModel, new CsvPlaatsInputModule());
	}
}