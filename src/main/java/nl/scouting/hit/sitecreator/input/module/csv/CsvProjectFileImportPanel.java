package nl.scouting.hit.sitecreator.input.module.csv;

import java.io.File;

import javax.swing.filechooser.FileNameExtensionFilter;

import nl.scouting.hit.sitecreator.Application;
import nl.scouting.hit.sitecreator.ConfigKey;
import nl.scouting.hit.sitecreator.ConfigKey.FileConfigKey;
import nl.scouting.hit.sitecreator.ConfigKey.StringConfigKey;
import nl.scouting.hit.sitecreator.input.module.AbstractFileImportPanel;

public class CsvProjectFileImportPanel extends AbstractFileImportPanel {
	private static final long serialVersionUID = 1L;

	public static final ConfigKey<File> CONFIG_CSV = new FileConfigKey(
			"projectcsv");
	public static final ConfigKey<String> CONFIG_ENCODING = new StringConfigKey(
			"projectenc");

	public CsvProjectFileImportPanel(final Application application) {
		super(application, "CSV", new FileNameExtensionFilter(
				"Bestand met de HIT Project gegevens", "csv", "txt"));
	}

	@Override
	protected final CsvProjectInputModule createInputModule() {
		return new CsvProjectInputModule();
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
