package nl.scouting.hit.sitecreator;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Parameters {

	private final String[] arguments;

	public Parameters(final String[] arguments) {
		this.arguments = arguments;
	}

	public Map<String, String> getConfiguration() {
		final Map<String, String> configuration = new HashMap<String, String>();

		final CommandLineParser parser = new GnuParser();
		final Options options = createOptions();
		try {
			final CommandLine cli = parser.parse(options, arguments);
			for (final Option o : cli.getOptions()) {
				configuration.put(o.getOpt(), o.getValue());
			}
		} catch (final ParseException e) {
			throw new RuntimeException(e);
		}
		return configuration;
	}

	private static Options createOptions() {
		final Options options = new Options();
		options.addOption("csv", true, "Naam van het CSV bestand");
		options.addOption("jaar", true, "Jaar (nodig voor mapping)");
		options.addOption("enc", true, "Encoding van bestand");
		options.addOption("htmlout", true, "Directory voor html uitvoer");

		return options;
	}
}
