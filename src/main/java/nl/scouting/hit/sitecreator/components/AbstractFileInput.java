package nl.scouting.hit.sitecreator.components;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

public class AbstractFileInput extends JPanel {
	private static final long serialVersionUID = 1L;

	private final JFileField text;
	private final JButton button;
	private final FileFilter filter;

	public int fileSelectionMode;

	/**
	 * Constructor.
	 * 
	 * @param columns
	 * @param filter
	 * @param validatingFileField
	 * 
	 */
	public AbstractFileInput(final int columns, final FileFilter filter,
			final JValidatingField validatingFileField,
			final int fileSelectionMode) {
		super(new BorderLayout(5, 5));
		this.filter = filter;
		this.fileSelectionMode = fileSelectionMode;

		add(text = validatingFileField, BorderLayout.CENTER);
		add(button = new JButton(new BladerAction()), BorderLayout.EAST);

		text.setColumns(columns);
		text.addPropertyChangeListener("file", new PropertyChangeListener() {
			@Override
			public void propertyChange(final PropertyChangeEvent evt) {
				firePropertyChange(evt.getPropertyName(), evt.getOldValue(),
						evt.getNewValue());
			}
		});
	}

	public final File getFile() {
		return text.getFile();
	}

	public final void setFile(final File file) {
		text.setFile(file);
	}

	private class BladerAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public BladerAction() {
			super("...");
		}

		@Override
		public void actionPerformed(final ActionEvent e) {
			final JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(filter);
			chooser.setFileSelectionMode(fileSelectionMode);

			if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(button)) {
				final File newFile = chooser.getSelectedFile();
				text.setFile(newFile);
			}
		}
	}
}