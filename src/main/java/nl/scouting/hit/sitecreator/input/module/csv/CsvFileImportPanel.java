package nl.scouting.hit.sitecreator.input.module.csv;

import javax.swing.filechooser.FileNameExtensionFilter;

import nl.scouting.hit.sitecreator.Application;
import nl.scouting.hit.sitecreator.input.module.AbstractFileImportPanel;

public class CsvFileImportPanel extends AbstractFileImportPanel {
	private static final long serialVersionUID = 1L;

	public CsvFileImportPanel(final Application application) {
		super(application, "CSV", new FileNameExtensionFilter(
				"Bestand met alle kampgegevens", "csv", "txt"));
	}

	@Override
	protected final CsvInputModule createInputModule() {
		return new CsvInputModule();
	}
}