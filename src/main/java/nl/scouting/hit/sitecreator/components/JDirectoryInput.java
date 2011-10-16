package nl.scouting.hit.sitecreator.components;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class JDirectoryInput extends AbstractFileInput {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param columns
	 * @param filter
	 */
	public JDirectoryInput(final int columns, final FileFilter filter) {
		super(columns, filter, JValidatingField.getValidatingDirectoryField(),
				JFileChooser.DIRECTORIES_ONLY);
	}

}