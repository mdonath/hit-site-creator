package nl.scouting.hit.sitecreator.output.module.html;

import java.util.Locale;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.stringtemplate.v4.AttributeRenderer;

public class LocalDateTimeRenderer implements AttributeRenderer {

	@Override
	public String toString(final Object o, final String formatString,
			final Locale locale) {
		DateTimeFormatter f;
		if ((formatString == null) || formatString.equals("")) {
			f = DateTimeFormat.forPattern("yyyy-MM-dd hh:mm:ss").withLocale(
					locale);
		} else {
			f = DateTimeFormat.forPattern(formatString).withLocale(locale);
		}
		return f.print((LocalDateTime) o);
	}
}
