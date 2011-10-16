package nl.scouting.hit.sitecreator.input.module.csv;

import javax.swing.filechooser.FileNameExtensionFilter;

import nl.scouting.hit.sitecreator.input.InputModule;
import nl.scouting.hit.sitecreator.input.module.AbstractFileImportPanel;

public class CsvFileImportPanel extends AbstractFileImportPanel {
	private static final long serialVersionUID = 1L;

	public CsvFileImportPanel() {
		super("CSV", new FileNameExtensionFilter(
				"Bestand met alle kampgegevens", "csv", "txt"));
	}

	private CsvInputModule inputModule;

	public InputModule getProcessor() {
		if (inputModule == null) {
			inputModule = new CsvInputModule();
			addPropertyChangeListener("file", inputModule);
			addPropertyChangeListener("jaar", inputModule);
		}
		return inputModule;
	}
}