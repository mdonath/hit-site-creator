package nl.scouting.hit.sitecreator.input.module.xml;

import java.beans.PropertyChangeEvent;
import java.io.File;

import nl.scouting.hit.sitecreator.input.InputModule;
import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.ModelUtil;

public class XmlInputModule implements InputModule {

	private File file;
	private Integer jaar;

	@Override
	public Hit load() {
		System.out.println("inlezen " + this.file + " van het jaar "
				+ this.jaar);
		return loadDummy();
	}

	private Hit loadDummy() {
		return ModelUtil.createTestStructure();
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		final String propertyName = evt.getPropertyName();
		if ("file".equals(propertyName)) {
			this.file = (File) evt.getNewValue();
		} else if ("jaar".equals(propertyName)) {
			this.jaar = (Integer) evt.getNewValue();
		}
	}
}
