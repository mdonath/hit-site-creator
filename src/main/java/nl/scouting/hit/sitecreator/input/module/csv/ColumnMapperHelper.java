package nl.scouting.hit.sitecreator.input.module.csv;

import java.beans.PropertyEditorManager;
import java.beans.PropertyEditorSupport;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import nl.scouting.hit.sitecreator.model.ImageUrl;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public interface ColumnMapperHelper {

	Map<String, String> getColumnMapping() throws MappingException;

	/**
	 * Maakt een Integer van een String.
	 */
	public static class IntegerPropertyEditor extends PropertyEditorSupport {
		public static void register() {
			PropertyEditorManager.registerEditor(Integer.class,
					IntegerPropertyEditor.class);
		}

		public static void unregister() {
			PropertyEditorManager.registerEditor(Integer.class, null);
		}

		@Override
		public String getAsText() {
			final Integer integer = (Integer) getValue();
			return integer.toString();
		}

		@Override
		public void setAsText(final String text)
				throws IllegalArgumentException {
			if ((text == null) || "".equals(text)) {
				setValue(null);
			} else {
				setValue(Integer.valueOf(text));
			}
		}
	}

	/**
	 * Maakt een LocalDate van een String.
	 */
	public static class LocalDatePropertyEditor extends PropertyEditorSupport {

		private static final DateTimeFormatter PATTERN = DateTimeFormat
				.forPattern("dd-MM-yyyy");

		public static void register() {
			PropertyEditorManager.registerEditor(LocalDate.class,
					LocalDatePropertyEditor.class);
		}

		public static void unregister() {
			PropertyEditorManager.registerEditor(LocalDate.class, null);
		}

		@Override
		public String getAsText() {
			final LocalDate localDate = (LocalDate) getValue();
			return localDate.toString(PATTERN);
		}

		@Override
		public void setAsText(final String text)
				throws IllegalArgumentException {
			if ((text == null) || "".equals(text)) {
				setValue(null);
			} else {
				setValue(PATTERN.parseLocalDate(text));
			}
		}
	}

	/**
	 * Maakt een LocalTime van een String.
	 */
	public static class LocalTimePropertyEditor extends PropertyEditorSupport {

		private static final DateTimeFormatter PATTERN = DateTimeFormat
				.forPattern("HH:mm");

		public static void register() {
			PropertyEditorManager.registerEditor(LocalTime.class,
					LocalTimePropertyEditor.class);
		}

		public static void unregister() {
			PropertyEditorManager.registerEditor(LocalTime.class, null);
		}

		@Override
		public String getAsText() {
			final LocalTime localTime = (LocalTime) getValue();
			return localTime.toString(PATTERN);
		}

		@Override
		public void setAsText(final String text)
				throws IllegalArgumentException {
			if ((text == null) || "".equals(text)) {
				setValue(null);
			} else {
				setValue(PATTERN.parseLocalTime(text));
			}
		}
	}

	public static class ImageUrlPropertyEditor extends PropertyEditorSupport {
		public static void register() {
			PropertyEditorManager.registerEditor(ImageUrl.class,
					ImageUrlPropertyEditor.class);
		}

		public static void unregister() {
			PropertyEditorManager.registerEditor(URL.class, null);
		}

		@Override
		public String getAsText() {
			final URL url = ((ImageUrl) getValue()).getUrl();
			return url.toString();
		}

		@Override
		public void setAsText(final String text)
				throws IllegalArgumentException {
			try {
				String checked = text;
				if ((checked != null) && !"".equals(checked)) {
					if (!"http://".equals(checked)) {
						if (!checked.startsWith("http")) {
							checked = "http://" + checked;
						}
						final ImageUrl url = new ImageUrl(checked);
						setValue(url);
					}
				}
			} catch (final MalformedURLException e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	/**
	 * Maakt een url van een String (en zet er desnoods een 'http://' voor).
	 */
	public static class UrlPropertyEditor extends PropertyEditorSupport {

		public static void register() {
			PropertyEditorManager.registerEditor(URL.class,
					UrlPropertyEditor.class);
		}

		public static void unregister() {
			PropertyEditorManager.registerEditor(URL.class, null);
		}

		@Override
		public String getAsText() {
			final URL url = (URL) getValue();
			return url.toString();
		}

		@Override
		public void setAsText(final String text)
				throws IllegalArgumentException {
			try {
				String checked = text;
				if ((checked != null) && !"".equals(checked)) {
					if (!"http://".equals(checked)) {
						if (!checked.startsWith("http")) {
							checked = "http://" + checked;
						}
						final URL url = new URL(checked);
						setValue(url);
					}
				}
			} catch (final MalformedURLException e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	/**
	 * Maakt een Boolean van een String. Hij hoeft alleen maar niet-null en
	 * niet-leeg te zijn, dan is het al <code>true</code>. Dus een null of empty
	 * betekent <code>false</code> .
	 */
	public static class BooleanNonEmptyStringPropertyEditor extends
			PropertyEditorSupport {

		public static void register() {
			PropertyEditorManager.registerEditor(Boolean.class,
					BooleanNonEmptyStringPropertyEditor.class);
		}

		public static void unregister() {
			PropertyEditorManager.registerEditor(Boolean.class, null);
		}

		@Override
		public String getAsText() {
			final Boolean value = (Boolean) getValue();
			return value.toString();
		}

		@Override
		public void setAsText(final String text)
				throws IllegalArgumentException {

			final Boolean result = Boolean.valueOf((text != null)
					&& (text.length() > 0));
			setValue(result);
		}
	}

}
