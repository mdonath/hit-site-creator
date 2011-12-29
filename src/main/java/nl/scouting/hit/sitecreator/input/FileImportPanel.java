package nl.scouting.hit.sitecreator.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.scouting.hit.sitecreator.ApplicationLabels;
import nl.scouting.hit.sitecreator.components.JEncodingComboBox;
import nl.scouting.hit.sitecreator.components.JFileInput;
import nl.scouting.hit.sitecreator.input.module.FileImportModel;
import nl.scouting.hit.sitecreator.input.module.FileImportModel.FileImportModelListener;
import nl.scouting.hit.sitecreator.input.module.FileInputModule;
import nl.scouting.hit.sitecreator.input.module.InputModule;
import nl.scouting.hit.sitecreator.util.UIUtil;

public class FileImportPanel extends JPanel implements InputModuleUI {
	private static final long serialVersionUID = 2387300085409121180L;

	private final FileImportModel model;
	private final FileInputModule inputModule;

	public FileImportPanel(final FileImportModel model,
			final FileInputModule inputModule) {
		setName(inputModule.getType());
		this.model = model;
		this.inputModule = inputModule;
		initComponents();
	}

	@Override
	public InputModule getProcessor() {
		return inputModule;
	}

	public void initComponents() {
		model.addModelListener(new FileImportModelListener() {

			@Override
			public void modelChanged(final PropertyChangeEvent event) {
				getProcessor().propertyChange(event);
			}
		});
		final JLabel locLabel = new JLabel(
				ApplicationLabels.getLabel("panel.input.import.outdir"));
		final JFileInput locField = new JFileInput(20, inputModule.getFilter());
		locField.addPropertyChangeListener("file",
				new PropertyChangeListener() {
					@Override
					public void propertyChange(final PropertyChangeEvent evt) {
						final File file = (File) evt.getNewValue();
						model.setFile(file);
					}
				});
		if (model.getFile() != null) {
			locField.setFile(model.getFile());
		}

		final JLabel encodingLabel = new JLabel(
				ApplicationLabels.getLabel("panel.input.import.encoding"));
		final JComboBox encodingField = new JEncodingComboBox(
				new ActionListener() {
					@Override
					public void actionPerformed(final ActionEvent e) {
						final String encoding = (String) ((JComboBox) e
								.getSource()).getSelectedItem();
						model.setEncoding(encoding);
					}
				});
		if (model.getEncoding() != null) {
			encodingField.setSelectedItem(model.getEncoding());
		}

		final GroupLayout layout = UIUtil.createGroupLayout(this);
		layout.setHorizontalGroup(layout.createSequentialGroup() //
				.addGroup(layout.createParallelGroup(Alignment.LEADING) //
						.addComponent(locLabel) //
						.addComponent(encodingLabel) //
				) //
				.addGroup(layout.createParallelGroup(Alignment.LEADING) //
						.addComponent(locField) //
						.addComponent(encodingField) //
				) //
		);
		layout.setVerticalGroup(layout.createSequentialGroup() //
				.addGroup(layout.createParallelGroup(Alignment.CENTER) //
						.addComponent(locLabel) //
						.addComponent(locField) //
				) //
				.addGroup(layout.createParallelGroup(Alignment.CENTER) //
						.addComponent(encodingLabel) //
						.addComponent(encodingField) //
				) //
		);
	}
}
