package nl.scouting.hit.sitecreator.output.module.html;

import java.util.Locale;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.stringtemplate.v4.AttributeRenderer;

public class LocalDateTimeRenderer implements AttributeRenderer {

	private static final String DEFAULT_PATTERN = "yyyy-MM-dd hh:mm:ss";

	@Override
	public String toString(final Object o, final String formatString,
			final Locale locale) {
		final DateTimeFormatter formatter = getFormatter(formatString, locale);
		return formatter.print((LocalDateTime) o);
	}

	private DateTimeFormatter getFormatter(final String formatString,
			final Locale locale) {
		final String pattern = getPattern(formatString);
		return DateTimeFormat //
				.forPattern(pattern) //
				.withLocale(locale);
	}

	private String getPattern(final String formatString) {
		String pattern;
		if (isEmpty(formatString)) {
			pattern = DEFAULT_PATTERN;
		} else {
			pattern = formatString;
		}
		return pattern;
	}

	private boolean isEmpty(final String formatString) {
		return (formatString == null) || formatString.equals("");
	}
}
