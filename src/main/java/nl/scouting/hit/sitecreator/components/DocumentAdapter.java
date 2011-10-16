package nl.scouting.hit.sitecreator.components;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Adapter voor velden met een Document Model.
 * 
 * @author Martijn Donath
 */
public abstract class DocumentAdapter implements DocumentListener {
	@Override
	public void changedUpdate(final DocumentEvent e) {
		eventUpdate(e);
	}

	@Override
	public void insertUpdate(final DocumentEvent e) {
		eventUpdate(e);
	}

	@Override
	public void removeUpdate(final DocumentEvent e) {
		eventUpdate(e);
	}

	protected abstract void eventUpdate(DocumentEvent e);

}