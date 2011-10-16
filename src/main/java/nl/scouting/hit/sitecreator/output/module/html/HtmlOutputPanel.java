package nl.scouting.hit.sitecreator.output.module.html;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nl.scouting.hit.sitecreator.components.TextChangedDocumentListener;
import nl.scouting.hit.sitecreator.output.OutputModule;
import nl.scouting.hit.sitecreator.output.OutputModuleUI;
import nl.scouting.hit.sitecreator.util.UIUtil;

public class HtmlOutputPanel extends JPanel implements OutputModuleUI {
	private static final long serialVersionUID = 1L;

	public HtmlOutputPanel() {
		initComponents();
	}

	private void initComponents() {
		setName("Html");

		JLabel outDirLabel = new JLabel("Output directory");
		JTextField outDirField = new JTextField();
		outDirField.getDocument().addDocumentListener(
				new TextChangeNotifierListener("outDir"));

		GroupLayout layout = UIUtil.createGroupLayout(this);

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

	private HtmlOutputModule outputModule;

	public OutputModule getProcessor() {
		if (outputModule == null) {
			outputModule = new HtmlOutputModule();
			addPropertyChangeListener("save", outputModule);
		}
		return outputModule;
	}

	private final class TextChangeNotifierListener extends
			TextChangedDocumentListener {

		private TextChangeNotifierListener(String propertyName) {
			super(propertyName);
		}

		@Override
		protected void textChanged(String propertyName, String oldValue,
				String newValue) {
			firePropertyChange(propertyName, oldValue, newValue);
		}
	}
}