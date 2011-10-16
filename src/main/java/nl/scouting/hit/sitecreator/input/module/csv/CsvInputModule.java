package nl.scouting.hit.sitecreator.input.module.csv;

import java.beans.PropertyChangeEvent;
import java.io.File;

import nl.scouting.hit.sitecreator.input.InputModule;
import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.ModelUtil;

public class CsvInputModule implements InputModule {

	private File file;
	private Integer jaar;

	public Hit load() {
		System.out.println("inlezen " + file + " van het jaar " + jaar);
		return loadDummy();
	}

	private Hit loadDummy() {
		return ModelUtil.createTestStructure();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if ("file".equals(propertyName)) {
			file = (File) evt.getNewValue();
		} else if ("jaar".equals(propertyName)) {
			jaar = (Integer) evt.getNewValue();
		}
	}
}
