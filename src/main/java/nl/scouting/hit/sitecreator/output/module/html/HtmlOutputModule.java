package nl.scouting.hit.sitecreator.output.module.html;

import java.beans.PropertyChangeEvent;
import java.io.File;

import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.output.OutputModule;

public class HtmlOutputModule implements OutputModule {

	private File outDir;

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		final String propertyName = evt.getPropertyName();
		if ("outDir".equals(propertyName)) {
			this.outDir = (File) evt.getNewValue();
		}
	}

	@Override
	public void save(final Hit hit) {
		System.out.println("Save de inhoud van " + hit.getJaar() + " naar "
				+ this.outDir);
	}
}
