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

		add(this.text = validatingFileField, BorderLayout.CENTER);
		add(this.button = new JButton(new BladerAction()), BorderLayout.EAST);

		this.text.setColumns(columns);
		this.text.addPropertyChangeListener("file",
				new PropertyChangeListener() {
					@Override
					public void propertyChange(final PropertyChangeEvent evt) {
						firePropertyChange(evt.getPropertyName(),
								evt.getOldValue(), evt.getNewValue());
					}
				});
	}

	public final File getFile() {
		return this.text.getFile();
	}

	private class BladerAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public BladerAction() {
			super("...");
		}

		@Override
		public void actionPerformed(final ActionEvent e) {
			final JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(AbstractFileInput.this.filter);
			chooser.setFileSelectionMode(AbstractFileInput.this.fileSelectionMode);

			if (JFileChooser.APPROVE_OPTION == chooser
					.showOpenDialog(AbstractFileInput.this.button)) {
				final File newFile = chooser.getSelectedFile();
				AbstractFileInput.this.text.setFile(newFile);
			}
		}
	}
}