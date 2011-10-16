package nl.scouting.hit.sitecreator.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JFileInput extends JPanel {
	private static final long serialVersionUID = 1L;

	private final JFileField text;
	private final JButton button;
	private final FileNameExtensionFilter filter;

	public JFileInput(int columns, FileNameExtensionFilter filter) {
		super(new BorderLayout(5, 5));
		this.filter = filter;
		add(this.text = new JValidatingFileField(), BorderLayout.CENTER);
		add(this.button = new JButton(new BladerAction()), BorderLayout.EAST);

		this.text.setColumns(columns);
		this.text.addPropertyChangeListener("file",
				new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						firePropertyChange(evt.getPropertyName(),
								evt.getOldValue(), evt.getNewValue());
					}
				});
	}

	private class BladerAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public BladerAction() {
			super("...");
		}

		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();

			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(JFileInput.this.button);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File newFile = chooser.getSelectedFile();
				JFileInput.this.text.setFile(newFile);
			}
		}
	}

	public final File getFile() {
		return this.text.getFile();
	}

	public static class JFileField extends JTextField {
		private static final long serialVersionUID = 1L;

		public JFileField() {
			getDocument().addDocumentListener(new DocumentAdapter() {
				@Override
				protected void eventUpdate(DocumentEvent e) {
					final File file = getFile();
					firePropertyChange("file", null, file);
				}
			});
		}

		public void setFile(File file) {
			setText(file.getAbsolutePath());
		}

		public File getFile() {
			return new File(getText());
		}
	}

	public static class JValidatingFileField extends JFileField {
		private static final long serialVersionUID = 1L;

		private final Border wrong = new LineBorder(Color.RED);
		private final Border normal = getBorder();

		public JValidatingFileField() {
			getDocument().addDocumentListener(new DocumentAdapter() {
				@Override
				protected void eventUpdate(DocumentEvent e) {
					final File file = getFile();
					final boolean ok = "".equals(file.getName())
							|| (file.exists() && file.isFile());
					Border newBorder = ok ? normal : wrong;
					if (!newBorder.equals(getBorder())) {
						setBorder(newBorder);
					}
				}
			});
		}
	}
}