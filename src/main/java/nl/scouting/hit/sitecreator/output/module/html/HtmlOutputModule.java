package nl.scouting.hit.sitecreator.output.module.html;

import java.beans.PropertyChangeEvent;
import java.io.File;

import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.output.OutputModule;

public class HtmlOutputModule implements OutputModule {

	private File outDir;

	public void save(Hit hit) {
		System.out.println("Save de inhoud van " + hit.getJaar() + " naar "
				+ outDir);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if ("outDir".equals(propertyName)) {
			outDir = (File) evt.getNewValue();
		}
	}

}
