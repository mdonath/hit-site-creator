package nl.scouting.hit.sitecreator.input.module;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import nl.scouting.hit.sitecreator.components.AbstractEventFirer;
import nl.scouting.hit.sitecreator.input.module.FileImportModel.FileImportModelListener;

public class FileImportModel extends
		AbstractEventFirer<PropertyChangeEvent, FileImportModelListener>
		implements PropertyChangeListener {

	public static interface FileImportModelListener extends
			AbstractEventFirer.ModelListener<PropertyChangeEvent> {
		// empty
	}

	private File file;
	private String encoding;

	public FileImportModel(final File file, final String encoding) {
		super(FileImportModelListener.class);
		this.file = file;
		this.encoding = encoding;
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		if ("file".equals(evt.getPropertyName())) {
			setFile((File) evt.getNewValue());
		} else if ("encoding".equals(evt.getPropertyName())) {
			setEncoding((String) evt.getNewValue());
		}
	}

	public void setFile(final File file) {
		final File oldValue = this.file;
		this.file = file;
		notifyModelEvent(new PropertyChangeEvent(this, "file", oldValue, file));
	}

	public void setEncoding(final String encoding) {
		final String oldValue = this.encoding;
		this.encoding = encoding;
		notifyModelEvent(new PropertyChangeEvent(this, "encoding", oldValue,
				encoding));
	}

	public File getFile() {
		return file;
	}

	public String getEncoding() {
		return encoding;
	}

}
