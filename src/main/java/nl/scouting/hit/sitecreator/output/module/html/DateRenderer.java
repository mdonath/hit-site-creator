package nl.scouting.hit.sitecreator.output.module.html;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.stringtemplate.v4.AttributeRenderer;

public class DateRenderer implements AttributeRenderer {

	@Override
	public String toString(Object o, String formatString, Locale locale) {
		DateFormat f;
		if (formatString == null || formatString.equals("")) {
			f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", locale);
		} else {
			f = new SimpleDateFormat(formatString, locale);
		}
		return f.format((Date) o);
	}
}
