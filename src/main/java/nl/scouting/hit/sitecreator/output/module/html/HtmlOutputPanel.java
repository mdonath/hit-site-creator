package nl.scouting.hit.sitecreator.output.module.html;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;

import nl.scouting.hit.sitecreator.components.JDirectoryInput;
import nl.scouting.hit.sitecreator.output.OutputModule;
import nl.scouting.hit.sitecreator.output.module.AbstractOutputPanel;
import nl.scouting.hit.sitecreator.util.UIUtil;

public class HtmlOutputPanel extends AbstractOutputPanel {
	private static final long serialVersionUID = 1L;

	private HtmlOutputModule outputModule;

	public HtmlOutputPanel() {
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

		final GroupLayout layout = UIUtil.createGroupLayout(this);

		// horizontaal gezien heb ik van links naar rechts twee paralelle
		// groepen. In de eerste groep zitten labels, in de tweede velden
		layout.setHorizontalGroup(layout.createSequentialGroup() //
				.addGroup(layout.createParallelGroup() //
						.addComponent(outDirLabel) //
				) //
				.addGroup(layout.createParallelGroup() //
						.addComponent(outDirField) //
				) //
		);
		// Verticaal gezien heb ik van boven naar beneden gezien drie
		// paralelle
		// groepen. Die groepen zijn de regels met de label|field
		// combinatie.
		layout.setVerticalGroup(layout.createSequentialGroup() //
				.addGroup(layout.createParallelGroup() //
						.addComponent(outDirLabel) //
						.addComponent(outDirField) //
				) //
		);
	}

	/** {@inheritDoc	 */
	@Override
	public OutputModule getProcessor() {
		if (this.outputModule == null) {
			this.outputModule = new HtmlOutputModule();
			addPropertyChangeListener("save", this.outputModule);
		}
		return this.outputModule;
	}

}