package nl.scouting.hit.sitecreator.components;

import java.io.File;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;

public class JFileField extends JTextField {
	private static final long serialVersionUID = 1L;

	public JFileField() {
		getDocument().addDocumentListener(new DocumentAdapter() {
			@Override
			protected void eventUpdate(final DocumentEvent e) {
				final File file = getFile();
				firePropertyChange("file", null, file);
			}
		});
	}

	public final File getFile() {
		return new File(getText());
	}

	public final void setFile(final File file) {
		setText(file.getAbsolutePath());
	}
}