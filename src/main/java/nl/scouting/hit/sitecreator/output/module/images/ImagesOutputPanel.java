package nl.scouting.hit.sitecreator.output.module.images;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import nl.scouting.hit.sitecreator.Application;
import nl.scouting.hit.sitecreator.ConfigKey;
import nl.scouting.hit.sitecreator.components.JDirectoryInput;
import nl.scouting.hit.sitecreator.model.HitProject;
import nl.scouting.hit.sitecreator.output.OutputModule;
import nl.scouting.hit.sitecreator.output.module.AbstractProgressOutputPanel;
import nl.scouting.hit.sitecreator.util.UIUtil;

/**
 * Panel voor output naar Joomla.
 * 
 * @author Martijn Donath
 */
public final class ImagesOutputPanel extends
		AbstractProgressOutputPanel<HitProject> {
	private static final long serialVersionUID = 1L;

	private ImagesOutputModule outputModule;

	public static final ConfigKey<File> CONF_OUTDIR = new ConfigKey.FileConfigKey(
			"htmlout");

	public ImagesOutputPanel(final Application<HitProject> application) {
		super(application);
		initComponents();
	}

	private void initComponents() {
		setName("Plaatjes");

		final JLabel outDirLabel = new JLabel("Output directory");
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

		final JPanel container = new JPanel(new BorderLayout());
		add(container, BorderLayout.NORTH);
		final GroupLayout layout = UIUtil.createGroupLayout(container);

		layout.setHorizontalGroup(layout.createSequentialGroup()
		//
				.addGroup(
						layout.createParallelGroup()
								.addGroup(
										layout.createSequentialGroup()
												.addComponent(outDirLabel) //
												.addComponent(outDirField) //
								) //
								.addComponent(getProgress()) //
				) //
		//
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
		//
				.addGroup(layout.createParallelGroup(Alignment.CENTER) //
						.addComponent(outDirLabel) //
						.addComponent(outDirField) //
				) //
				.addComponent(getProgress()) //
		);
	}

	/** {@inheritDoc	 */
	@Override
	public OutputModule getProcessor() {
		if (outputModule == null) {
			outputModule = new ImagesOutputModule();
			addPropertyChangeListener("save", outputModule);
			addPropertyChangeListener("outDir", outputModule);
			outputModule.addProgressListener(this);
		}
		return outputModule;
	}

}