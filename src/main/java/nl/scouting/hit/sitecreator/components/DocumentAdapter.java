package nl.scouting.hit.sitecreator.components;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public abstract class DocumentAdapter implements DocumentListener {
	public void removeUpdate(DocumentEvent e) {
		eventUpdate(e);
	}

	public void insertUpdate(DocumentEvent e) {
		eventUpdate(e);
	}

	public void changedUpdate(DocumentEvent e) {
		eventUpdate(e);
	}

	protected abstract void eventUpdate(DocumentEvent e);
}