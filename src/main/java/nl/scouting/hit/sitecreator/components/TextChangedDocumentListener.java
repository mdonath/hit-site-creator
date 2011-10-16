package nl.scouting.hit.sitecreator.components;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public abstract class TextChangedDocumentListener extends DocumentAdapter {
	private final String propertyName;

	public TextChangedDocumentListener(String propertyName) {
		this.propertyName = propertyName;
	}

	@Override
	protected void eventUpdate(DocumentEvent e) {
		Document document = e.getDocument();
		try {
			String text = document.getText(0, document.getLength());
			textChanged(propertyName, null, text);
		} catch (BadLocationException e1) {
			throw new RuntimeException("Ophalen text", e1);
		}
	}

	protected abstract void textChanged(String propertyName, String oldValue,
			String newValue);
}