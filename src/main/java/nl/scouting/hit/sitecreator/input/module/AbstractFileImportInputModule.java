package nl.scouting.hit.sitecreator.input.module;

import java.beans.PropertyChangeEvent;
import java.io.File;

import nl.scouting.hit.sitecreator.input.InputModule;

public abstract class AbstractFileImportInputModule implements InputModule {

	private File file;
	private Integer jaar;
	private String encoding = "UTF-8";

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		final String propertyName = evt.getPropertyName();
		if ("file".equals(propertyName)) {
			this.file = (File) evt.getNewValue();
		} else if ("jaar".equals(propertyName)) {
			this.jaar = (Integer) evt.getNewValue();
		} else if ("encoding".equals(propertyName)) {
			this.encoding = (String) evt.getNewValue();
		}
	}

	public File getFile() {
		return this.file;
	}

	public Integer getJaar() {
		return this.jaar;
	}

	public String getEncoding() {
		return this.encoding;
	}
}
