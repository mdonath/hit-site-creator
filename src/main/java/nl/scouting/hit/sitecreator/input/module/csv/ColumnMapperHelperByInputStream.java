package nl.scouting.hit.sitecreator.input.module.csv;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ColumnMapperHelperByInputStream implements ColumnMapperHelper {

	private final InputStream resource;

	public ColumnMapperHelperByInputStream(final InputStream resource)
			throws MappingException {
		this.resource = resource;
		if (resource == null) {
			throw new MappingException("Mapping configuratie niet gevonden!");
		}
	}

	/** {@inheritDoc} */
	@Override
	public Map<String, String> getColumnMapping() throws MappingException {
		IntegerPropertyEditor.register();
		UrlPropertyEditor.register();
		BooleanNonEmptyStringPropertyEditor.register();
		LocalDatePropertyEditor.register();
		LocalTimePropertyEditor.register();
		ImageUrlPropertyEditor.register();

		final Map<String, String> columnMapping = new HashMap<String, String>();
		fillWithPropertyFile(columnMapping);
		return columnMapping;
	}

	private void fillWithPropertyFile(final Map<String, String> columnMapping)
			throws MappingException {
		try {
			final Properties p = new Properties();
			p.load(resource);
			for (final Object keyObject : p.keySet()) {
				final String key = (String) keyObject;
				columnMapping.put(key, p.getProperty(key));
			}
		} catch (final IOException e) {
			throw new MappingException(e);
		} finally {
			if (resource != null) {
				try {
					resource.close();
				} catch (final IOException ignore) {
					// ignored
				}
			}
		}
	}
}
