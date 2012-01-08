package nl.scouting.hit.sitecreator.input;

import java.awt.BorderLayout;

import nl.scouting.hit.sitecreator.ApplicationLabels;
import nl.scouting.hit.sitecreator.input.module.FileImportModel;
import nl.scouting.hit.sitecreator.input.module.csv.CsvInschrijvingInputModule;

public class InschrijvingenInputTabPanel extends AbstractInputTabPanel {

	private static final long serialVersionUID = 1L;

	public InschrijvingenInputTabPanel(final FileImportModel csvModel) {
		super(ApplicationLabels.getLabel("panel.input.inschrijvingen"));
		initComponents(csvModel);
	}

	private void initComponents(final FileImportModel csvModel) {
		add(createTabPanel(createCsvImportPanel(csvModel)), BorderLayout.CENTER);
		add(createButtonPanel(), BorderLayout.SOUTH);
	}

	private static FileImportPanel createCsvImportPanel(
			final FileImportModel csvModel) {
		return new FileImportPanel(csvModel, new CsvInschrijvingInputModule());
	}

}