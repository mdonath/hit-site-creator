package nl.scouting.hit.sitecreator.input.module.csv;

import java.io.File;

import javax.swing.filechooser.FileNameExtensionFilter;

import nl.scouting.hit.sitecreator.Application;
import nl.scouting.hit.sitecreator.ConfigKey;
import nl.scouting.hit.sitecreator.ConfigKey.FileConfigKey;
import nl.scouting.hit.sitecreator.ConfigKey.StringConfigKey;
import nl.scouting.hit.sitecreator.input.module.AbstractFileImportPanel;

public class CsvPlaatsFileImportPanel extends AbstractFileImportPanel {
	private static final long serialVersionUID = 1L;

	public static final ConfigKey<File> CONFIG_CSV = new FileConfigKey(
			"plaatscsv");
	public static final ConfigKey<String> CONFIG_ENCODING = new StringConfigKey(
			"plaatsenc");

	public CsvPlaatsFileImportPanel(final Application application) {
		super(application, "CSV", new FileNameExtensionFilter(
				"Bestand met alle kampplaatsgegevens", "csv", "txt"));
	}

	@Override
	protected final CsvPlaatsInputModule createInputModule() {
		return new CsvPlaatsInputModule();
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