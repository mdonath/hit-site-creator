package nl.scouting.hit.sitecreator.input.module.csv;

import java.beans.PropertyChangeEvent;
import java.io.File;

import nl.scouting.hit.sitecreator.input.module.FileImportModel;

public class CsvFileImportModel extends FileImportModel {

	private Integer jaar;

	public CsvFileImportModel(final File file, final String encoding,
			final Integer jaar) {
		super(file, encoding);
		this.setJaar(jaar);
	}

	public Integer getJaar() {
		return jaar;
	}

	public void setJaar(final Integer jaar) {
		final Integer oldValue = this.jaar;
		notifyModelEvent(new PropertyChangeEvent(this, "jaar", oldValue, jaar));
		this.jaar = jaar;
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		super.propertyChange(evt);
		if ("jaar".equals(evt.getPropertyName())) {
			setJaar((Integer) evt.getNewValue());
		}
	}

}
