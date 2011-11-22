package nl.scouting.hit.sitecreator.input.module.csv;

import java.io.File;

import javax.swing.filechooser.FileNameExtensionFilter;

import nl.scouting.hit.sitecreator.Application;
import nl.scouting.hit.sitecreator.ConfigKey;
import nl.scouting.hit.sitecreator.ConfigKey.FileConfigKey;
import nl.scouting.hit.sitecreator.ConfigKey.StringConfigKey;
import nl.scouting.hit.sitecreator.input.InputModule;
import nl.scouting.hit.sitecreator.input.module.AbstractFileImportPanel;
import nl.scouting.hit.sitecreator.model.Hit;

public class CsvDeelnemerFileImportPanel extends AbstractFileImportPanel {
	private static final long serialVersionUID = 1L;

	public static final ConfigKey<File> CONFIG_CSV = new FileConfigKey("dlncsv");
	public static final ConfigKey<String> CONFIG_ENCODING = new StringConfigKey(
			"dlnenc");

	public CsvDeelnemerFileImportPanel(final Application<Hit> application) {
		super(application, "CSV", new FileNameExtensionFilter(
				"Bestand met de HIT Deelnemer gegevens", "csv", "txt"));
	}

	@Override
	protected final InputModule createInputModule() {
		return new CsvDeelnemerInputModule();
	}

	@Override
	protected ConfigKey<File> getInputConfigKey() {
		return CONFIG_CSV;
	}

	@Override
	protected ConfigKey<String> getEncodingKey() {
		return CONFIG_ENCODING;
	}
}
