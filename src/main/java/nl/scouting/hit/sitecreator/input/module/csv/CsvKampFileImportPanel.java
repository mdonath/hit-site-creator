package nl.scouting.hit.sitecreator.input.module.csv;

import java.io.File;

import javax.swing.filechooser.FileNameExtensionFilter;

import nl.scouting.hit.sitecreator.Application;
import nl.scouting.hit.sitecreator.ConfigKey;
import nl.scouting.hit.sitecreator.ConfigKey.FileConfigKey;
import nl.scouting.hit.sitecreator.ConfigKey.StringConfigKey;
import nl.scouting.hit.sitecreator.input.module.AbstractFileImportPanel;

public class CsvKampFileImportPanel extends AbstractFileImportPanel {
	private static final long serialVersionUID = 1L;

	public static final ConfigKey<File> CONFIG_CSV = new FileConfigKey(
			"kampcsv");
	public static final ConfigKey<String> CONFIG_ENCODING = new StringConfigKey(
			"kampenc");

	public CsvKampFileImportPanel(final Application application) {
		super(application, "CSV", new FileNameExtensionFilter(
				"Bestand met alle kampgegevens", "csv", "txt"));
	}

	@Override
	protected final CsvKampInputModule createInputModule() {
		return new CsvKampInputModule();
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