package nl.scouting.hit.sitecreator.input.module.csv;

import javax.swing.filechooser.FileNameExtensionFilter;

import nl.scouting.hit.sitecreator.input.InputModule;
import nl.scouting.hit.sitecreator.input.module.AbstractFileImportPanel;

public class CsvFileImportPanel extends AbstractFileImportPanel {
	private static final long serialVersionUID = 1L;

	private CsvInputModule inputModule;

	public CsvFileImportPanel() {
		super("CSV", new FileNameExtensionFilter(
				"Bestand met alle kampgegevens", "csv", "txt"));
	}

	@Override
	public InputModule getProcessor() {
		if (this.inputModule == null) {
			this.inputModule = new CsvInputModule();
			addPropertyChangeListener("file", this.inputModule);
			addPropertyChangeListener("jaar", this.inputModule);
		}
		return this.inputModule;
	}
}