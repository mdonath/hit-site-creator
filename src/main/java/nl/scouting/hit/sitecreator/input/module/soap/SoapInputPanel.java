package nl.scouting.hit.sitecreator.input.module.soap;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import nl.scouting.hit.sitecreator.components.TextChangedDocumentListener;
import nl.scouting.hit.sitecreator.input.InputModule;
import nl.scouting.hit.sitecreator.input.InputModuleUI;
import nl.scouting.hit.sitecreator.util.UIUtil;

public class SoapInputPanel extends JPanel implements InputModuleUI {
	private static final long serialVersionUID = 1L;

	public SoapInputPanel() {
		initComponents();
	}

	private void initComponents() {
		setName("Soap");

		JLabel urlLabel = new JLabel("Soap URL");
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

	private SoapInputModule inputModule;

	public InputModule getProcessor() {
		if (inputModule == null) {
			inputModule = new SoapInputModule();
			addPropertyChangeListener("url", inputModule);
			addPropertyChangeListener("user", inputModule);
			addPropertyChangeListener("password", inputModule);
		}
		return inputModule;
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