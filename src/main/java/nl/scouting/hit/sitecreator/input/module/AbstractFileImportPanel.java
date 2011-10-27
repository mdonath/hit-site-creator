package nl.scouting.hit.sitecreator.input.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import nl.scouting.hit.sitecreator.components.JEncodingComboBox;
import nl.scouting.hit.sitecreator.components.JFileInput;
import nl.scouting.hit.sitecreator.input.AbstractInputPanel;
import nl.scouting.hit.sitecreator.input.InputModule;
import nl.scouting.hit.sitecreator.util.UIUtil;

public abstract class AbstractFileImportPanel extends AbstractInputPanel {
	private static final long serialVersionUID = 1L;

	private InputModule inputModule;

	public AbstractFileImportPanel(final String name,
			final FileNameExtensionFilter filter) {
		super();
		setName(name);
		initComponents(filter);
	}

	public void initComponents(final FileNameExtensionFilter filter) {

		final JLabel locLabel = new JLabel("Locatie bestand");
		final JFileInput locField = new JFileInput(20, filter);
		locField.addPropertyChangeListener("file",
				new PropertyChangeListener() {
					@Override
					public void propertyChange(final PropertyChangeEvent evt) {
						firePropertyChange("file", evt.getOldValue(),
								evt.getNewValue());
					}
				});

		final JLabel yearLabel = new JLabel("Jaar");

		final int huidigeJaar = Calendar.getInstance().get(Calendar.YEAR);
		final JComboBox yearField = new JComboBox(createJaarItems(huidigeJaar));
		yearField.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(final ItemEvent e) {
				final Object[] selected = e.getItemSelectable()
						.getSelectedObjects();
				if (selected != null) {
					firePropertyChange("jaar", null, selected[0]);
				}
			}
		});
		yearField.setSelectedItem(huidigeJaar);

		final JLabel encodingLabel = new JLabel("Encoding");
		final JComboBox encodingField = new JEncodingComboBox(
				new ActionListener() {
					@Override
					public void actionPerformed(final ActionEvent e) {
						firePropertyChange("encoding", null,
								((JComboBox) e.getSource()).getSelectedItem());
					}
				});

		final GroupLayout layout = UIUtil.createGroupLayout(this);
		layout.setHorizontalGroup(layout.createSequentialGroup() //
				.addGroup(layout.createParallelGroup(Alignment.LEADING) //
						.addComponent(locLabel) //
						.addComponent(yearLabel) //
						.addComponent(encodingLabel) //
				) //
				.addGroup(layout.createParallelGroup(Alignment.LEADING) //
						.addComponent(locField) //
						.addComponent(yearField) //
						.addComponent(encodingField) //
				) //
		);
		layout.setVerticalGroup(layout.createSequentialGroup() //
				.addGroup(layout.createParallelGroup(Alignment.CENTER) //
						.addComponent(locLabel) //
						.addComponent(locField) //
				) //
				.addGroup(layout.createParallelGroup(Alignment.CENTER) //
						.addComponent(yearLabel) //
						.addComponent(yearField) //
				) //
				.addGroup(layout.createParallelGroup(Alignment.CENTER) //
						.addComponent(encodingLabel) //
						.addComponent(encodingField) //
				) //
		);
	}

	public Integer[] createJaarItems(final int huidigeJaar) {
		final int startJaar = 2010;
		final int aantal = huidigeJaar - startJaar;
		final Integer[] items = new Integer[aantal + 2];
		for (int i = 0; i < items.length; i++) {
			items[i] = Integer.valueOf(startJaar + i);
		}
		return items;
	}

	@Override
	public final InputModule getProcessor() {
		if (inputModule == null) {
			inputModule = createInputModule();
			addPropertyChangeListener("file", inputModule);
			addPropertyChangeListener("jaar", inputModule);
			addPropertyChangeListener("encoding", inputModule);
		}
		return inputModule;
	}

	protected abstract InputModule createInputModule();
}