package nl.scouting.hit.sitecreator.components;

import java.awt.Color;
import java.io.File;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;

public abstract class JValidatingField extends JFileField {
	private static final long serialVersionUID = 1L;

	private final Border wrong = new LineBorder(Color.RED);
	private final Border normal = getBorder();

	/**
	 * @return maakt een validating field aan die controleert of het BESTAND
	 *         bestaat.
	 */
	public static JValidatingField getValidatingFileField() {
		return new JValidatingField() {
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean isCorrectType(final File file) {
				return file.isFile();
			}
		};
	}

	/**
	 * @return maakt een validating field aan die controleert of de DIRECTORY
	 *         bestaat.
	 */
	public static JValidatingField getValidatingDirectoryField() {
		return new JValidatingField() {
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean isCorrectType(final File file) {
				return file.isDirectory();
			}
		};
	}

	/**
	 * Constructor.
	 */
	public JValidatingField() {
		getDocument().addDocumentListener(new DocumentAdapter() {
			@Override
			protected void eventUpdate(final DocumentEvent e) {
				final File file = getFile();
				final boolean ok = (file.exists() && isCorrectType(file));
				final Border newBorder = ok ? JValidatingField.this.normal
						: JValidatingField.this.wrong;
				if (!newBorder.equals(getBorder())) {
					setBorder(newBorder);
				}
			}
		});
	}

	protected abstract boolean isCorrectType(final File file);
}