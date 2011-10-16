package nl.scouting.hit.sitecreator.input.module;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import nl.scouting.hit.sitecreator.components.JFileInput;
import nl.scouting.hit.sitecreator.input.InputModuleUI;
import nl.scouting.hit.sitecreator.util.UIUtil;

public abstract class AbstractFileImportPanel extends JPanel implements
		InputModuleUI {
	private static final long serialVersionUID = 1L;

	public AbstractFileImportPanel(String name, FileNameExtensionFilter filter) {
		this.setName(name);

		JLabel locLabel = new JLabel("Locatie bestand:");
		JFileInput locField = new JFileInput(20, filter);
		locField.addPropertyChangeListener("file",
				new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						firePropertyChange("file", evt.getOldValue(),
								evt.getNewValue());
					}
				});

		JLabel yearLabel = new JLabel("Jaar");
		JComboBox yearField = new JComboBox(new Integer[] { 2010, 2011, 2012 });
		yearField.setSelectedItem(Calendar.getInstance().get(Calendar.YEAR));
		yearField.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				Object[] selected = e.getItemSelectable().getSelectedObjects();
				if (selected != null) {
					firePropertyChange("jaar", null, selected[0]);
				}
			}
		});

		GroupLayout layout = UIUtil.createGroupLayout(this);
		layout.setHorizontalGroup(layout.createSequentialGroup() //
				.addGroup(layout.createParallelGroup(Alignment.LEADING) //
						.addComponent(locLabel) //
						.addComponent(yearLabel) //
				) //
				.addGroup(layout.createParallelGroup(Alignment.LEADING) //
						.addComponent(locField) //
						.addComponent(yearField) //
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
		);
	}

}