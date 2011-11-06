package nl.scouting.hit.sitecreator.input.module;

import java.beans.PropertyChangeEvent;
import java.io.File;

import nl.scouting.hit.sitecreator.input.InputModule;

public abstract class AbstractFileImportInputModule implements InputModule {

	private File file;
	private Integer jaar;
	private String encoding;

	public void setFile(final File file) {
		this.file = file;
	}

	public void setJaar(final Integer jaar) {
		this.jaar = jaar;
	}

	public void setEncoding(final String encoding) {
		this.encoding = encoding;
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		final String propertyName = evt.getPropertyName();
		if ("file".equals(propertyName)) {
			file = (File) evt.getNewValue();
		} else if ("jaar".equals(propertyName)) {
			jaar = (Integer) evt.getNewValue();
		} else if ("encoding".equals(propertyName)) {
			encoding = (String) evt.getNewValue();
		}
	}

	public File getFile() {
		return file;
	}

	public Integer getJaar() {
		return jaar;
	}

	public String getEncoding() {
		return encoding;
	}
}
