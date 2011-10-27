package nl.scouting.hit.sitecreator.components;

import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class JEncodingComboBox extends JComboBox {
	private static final long serialVersionUID = -6585041764764582876L;

	public static final String[] encodingItems = new String[] { "UTF-8",
			"ISO-8859-1" };

	public JEncodingComboBox() {
		super(encodingItems);
	}

	public JEncodingComboBox(final ActionListener actionListener) {
		this();
		addActionListener(actionListener);
	}

	@Override
	public void addActionListener(final ActionListener l) {
		super.addActionListener(l);
		setSelectedItem(JEncodingComboBox.encodingItems[0]);
	}
}
