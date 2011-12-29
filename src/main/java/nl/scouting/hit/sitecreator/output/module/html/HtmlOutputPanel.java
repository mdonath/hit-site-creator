package nl.scouting.hit.sitecreator.output.module.html;

import java.awt.BorderLayout;
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
import javax.swing.filechooser.FileFilter;

import nl.scouting.hit.sitecreator.Application;
import nl.scouting.hit.sitecreator.ApplicationLabels;
import nl.scouting.hit.sitecreator.ConfigKey;
import nl.scouting.hit.sitecreator.components.JDirectoryInput;
import nl.scouting.hit.sitecreator.components.JEncodingComboBox;
import nl.scouting.hit.sitecreator.model.HitProject;
import nl.scouting.hit.sitecreator.output.OutputModule;
import nl.scouting.hit.sitecreator.output.module.AbstractProgressOutputPanel;
import nl.scouting.hit.sitecreator.util.UIUtil;

public class HtmlOutputPanel extends AbstractProgressOutputPanel<HitProject> {
	private static final long serialVersionUID = 1L;

	private HtmlOutputModule outputModule;

	public static final ConfigKey<File> CONF_OUTDIR = new ConfigKey.FileConfigKey(
			"htmlout");

	public HtmlOutputPanel(final Application<HitProject> application) {
		super(application);
		initComponents();
	}

	private void initComponents() {
		setName(ApplicationLabels.getLabel("panel.output.html"));

		final JLabel outDirLabel = new JLabel(
				ApplicationLabels.getLabel("panel.output.html.outdir"));
		final JDirectoryInput outDirField = new JDirectoryInput(50,
				new FileFilter() {

					@Override
					public String getDescription() {
						return "Directory";
					}

					@Override
					public boolean accept(final File f) {
						return f.isDirectory();
					}
				});
		outDirField.addPropertyChangeListener("file",
				new PropertyChangeListener() {
					@Override
					public void propertyChange(final PropertyChangeEvent evt) {
						firePropertyChange("outDir", evt.getOldValue(),
								evt.getNewValue());
					}
				});
		if (getApplication().hasConfigurationValue(CONF_OUTDIR)) {
			outDirField.setFile(getApplication().getConfigurationValue(
					CONF_OUTDIR));
		}

		final JLabel encodingLabel = new JLabel(
				ApplicationLabels.getLabel("panel.output.html.encoding"));
		final JComboBox encodingField = new JEncodingComboBox(
				new ActionListener() {
					@Override
					public void actionPerformed(final ActionEvent e) {
						firePropertyChange("encoding", null,
								((JComboBox) e.getSource()).getSelectedItem());
					}
				});

		setLayout(new BorderLayout());
		final JPanel container = new JPanel();
		add(container, BorderLayout.CENTER);

		final GroupLayout layout = UIUtil.createGroupLayout(container);
		layout.setHorizontalGroup(layout
				.createParallelGroup()
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												Alignment.LEADING) //
												.addComponent(outDirLabel) //
												.addComponent(encodingLabel) //
								) //
								.addGroup(
										layout.createParallelGroup(
												Alignment.LEADING) //
												.addComponent(outDirField) //
												.addComponent(encodingField) //
								)) //
				.addGroup(layout.createSequentialGroup() //
						.addComponent(getProgress()) //
				));

		layout.setVerticalGroup(layout.createSequentialGroup() //
				.addGroup(layout.createParallelGroup(Alignment.CENTER) //
						.addComponent(outDirLabel) //
						.addComponent(outDirField) //
				) //
				.addGroup(layout.createParallelGroup(Alignment.CENTER) //
						.addComponent(encodingLabel) //
						.addComponent(encodingField) //
				) //
				.addGroup(layout.createParallelGroup() //
						.addComponent(getProgress()) //
				)//
		);
	}

	/** {@inheritDoc	 */
	@Override
	public OutputModule getProcessor() {
		if (outputModule == null) {
			outputModule = new HtmlOutputModule();
			addPropertyChangeListener("save", outputModule);
			addPropertyChangeListener("outDir", outputModule);
			addPropertyChangeListener("encoding", outputModule);
			outputModule.addProgressListener(this);
		}
		return outputModule;
	}

}