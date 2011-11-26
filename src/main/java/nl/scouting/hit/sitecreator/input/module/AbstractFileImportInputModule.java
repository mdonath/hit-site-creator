package nl.scouting.hit.sitecreator.input.module;

import java.beans.PropertyChangeEvent;
import java.io.File;

public abstract class AbstractFileImportInputModule implements FileInputModule {

	private File file;
	private String encoding;
	private Integer jaar;

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
			System.out.println(getClass() + ": " + propertyName + "=" + jaar);
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
