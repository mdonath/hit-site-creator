package nl.scouting.hit.sitecreator.output.module.html;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;

import nl.scouting.hit.sitecreator.Application;
import nl.scouting.hit.sitecreator.ConfigKey;
import nl.scouting.hit.sitecreator.components.JDirectoryInput;
import nl.scouting.hit.sitecreator.components.JEncodingComboBox;
import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.output.OutputModule;
import nl.scouting.hit.sitecreator.output.module.AbstractOutputPanel;
import nl.scouting.hit.sitecreator.util.UIUtil;

public class HtmlOutputPanel extends AbstractOutputPanel {
	private static final long serialVersionUID = 1L;

	private HtmlOutputModule outputModule;

	private final Application<Hit> application;

	public static final ConfigKey<File> CONF_OUTDIR = new ConfigKey.FileConfigKey(
			"htmlout");

	public HtmlOutputPanel(final Application<Hit> application) {
		this.application = application;
		getProcessor();
		initComponents();
	}

	private void initComponents() {
		setName("Html");

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
		if (application.hasConfigurationValue(CONF_OUTDIR)) {
			outDirField.setFile(application.getConfigurationValue(CONF_OUTDIR));
		}

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

		// horizontaal gezien heb ik van links naar rechts twee paralelle
		// groepen. In de eerste groep zitten labels, in de tweede velden
		layout.setHorizontalGroup(layout.createSequentialGroup() //
				.addGroup(layout.createParallelGroup() //
						.addComponent(outDirLabel) //
						.addComponent(encodingLabel) //
				) //
				.addGroup(layout.createParallelGroup() //
						.addComponent(outDirField) //
						.addComponent(encodingField) //
				) //
		);
		// Verticaal gezien heb ik van boven naar beneden gezien twee
		// paralelle groepen. Die groepen zijn de regels met de label|field
		// combinatie.
		layout.setVerticalGroup(layout.createSequentialGroup() //
				.addGroup(layout.createParallelGroup() //
						.addComponent(outDirLabel) //
						.addComponent(outDirField) //
				) //
				.addGroup(layout.createParallelGroup() //
						.addComponent(encodingLabel) //
						.addComponent(encodingField) //
				) //
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
		}
		return outputModule;
	}

}