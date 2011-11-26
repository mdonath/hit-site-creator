package nl.scouting.hit.sitecreator;

import java.util.ResourceBundle;

public final class ApplicationLabels {
	private static ResourceBundle labels;

	public static String getLabel(final String key) {
		if (labels == null) {
			labels = ResourceBundle.getBundle("Application");
		}
		return labels.getString(key);
	}
}
