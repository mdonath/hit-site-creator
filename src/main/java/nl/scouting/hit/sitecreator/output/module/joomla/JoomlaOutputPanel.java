package nl.scouting.hit.sitecreator.output.module.joomla;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import nl.scouting.hit.sitecreator.components.TextChangedDocumentListener;
import nl.scouting.hit.sitecreator.output.OutputModule;
import nl.scouting.hit.sitecreator.output.OutputModuleUI;
import nl.scouting.hit.sitecreator.util.UIUtil;

public class JoomlaOutputPanel extends JPanel implements OutputModuleUI {
	private static final long serialVersionUID = 1L;

	public JoomlaOutputPanel() {
		initComponents();
	}

	private void initComponents() {
		setName("Joomla");

		JLabel urlLabel = new JLabel("Joomla URL");
		JTextField urlField = new JTextField();
		urlField.getDocument().addDocumentListener(
				new TextChangeNotifierListener("url"));
		JLabel userLabel = new JLabel("Username");
		JTextField userField = new JTextField();
		userField.getDocument().addDocumentListener(
				new TextChangeNotifierListener("user"));
		JLabel passwordLabel = new JLabel("Password");
		JPasswordField passwordField = new JPasswordField();
		passwordField.getDocument().addDocumentListener(
				new TextChangeNotifierListener("password"));

		GroupLayout layout = UIUtil.createGroupLayout(this);

		// horizontaal gezien heb ik van links naar rechts twee paralelle
		// groepen. In de eerste groep zitten labels, in de tweede velden
		layout.setHorizontalGroup(layout.createSequentialGroup() //
				.addGroup(layout.createParallelGroup() //
						.addComponent(urlLabel) //
						.addComponent(userLabel) //
						.addComponent(passwordLabel) //
				) //
				.addGroup(layout.createParallelGroup() //
						.addComponent(urlField) //
						.addComponent(userField) //
						.addComponent(passwordField) //
				) //
		);
		// Verticaal gezien heb ik van boven naar beneden gezien drie
		// paralelle
		// groepen. Die groepen zijn de regels met de label|field
		// combinatie.
		layout.setVerticalGroup(layout.createSequentialGroup() //
				.addGroup(layout.createParallelGroup() //
						.addComponent(urlLabel) //
						.addComponent(urlField) //
				) //
				.addGroup(layout.createParallelGroup() //
						.addComponent(userLabel) //
						.addComponent(userField) //
				) //
				.addGroup(layout.createParallelGroup() //
						.addComponent(passwordLabel) //
						.addComponent(passwordField) //
				) //
		);

	}

	private JoomlaOutputModule outputModule;

	public OutputModule getProcessor() {
		if (outputModule == null) {
			outputModule = new JoomlaOutputModule();
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