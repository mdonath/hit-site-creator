package nl.scouting.hit.sitecreator.input.module.soap;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import nl.scouting.hit.sitecreator.Application;
import nl.scouting.hit.sitecreator.components.TextChangedDocumentListener;
import nl.scouting.hit.sitecreator.input.AbstractInputPanel;
import nl.scouting.hit.sitecreator.input.InputModule;
import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.util.UIUtil;

public class SoapInputPanel extends AbstractInputPanel {
	private final class TextChangeNotifierListener extends
			TextChangedDocumentListener {

		private TextChangeNotifierListener(final String propertyName) {
			super(propertyName);
		}

		@Override
		protected void textChanged(final String propertyName,
				final String oldValue, final String newValue) {
			firePropertyChange(propertyName, oldValue, newValue);
		}
	}

	private static final long serialVersionUID = 1L;

	private SoapInputModule inputModule;

	public SoapInputPanel(final Application<Hit> application) {
		initComponents();
	}

	@Override
	public InputModule getProcessor() {
		if (inputModule == null) {
			inputModule = new SoapInputModule();
			addPropertyChangeListener("url", inputModule);
			addPropertyChangeListener("user", inputModule);
			addPropertyChangeListener("password", inputModule);
		}
		return inputModule;
	}

	private void initComponents() {
		setName("Soap");
		setBackground(Color.RED); // FIXME soap-input doet het nog niet

		final JLabel urlLabel = new JLabel("Soap URL");
		final JTextField urlField = new JTextField();
		urlField.getDocument().addDocumentListener(
				new TextChangeNotifierListener("url"));
		final JLabel userLabel = new JLabel("Username");
		final JTextField userField = new JTextField();
		userField.getDocument().addDocumentListener(
				new TextChangeNotifierListener("user"));
		final JLabel passwordLabel = new JLabel("Password");
		final JPasswordField passwordField = new JPasswordField();
		passwordField.getDocument().addDocumentListener(
				new TextChangeNotifierListener("password"));

		final GroupLayout layout = UIUtil.createGroupLayout(this);

		// horizontaal gezien heb ik van links naar rechts twee paralelle
		// groepen. In de eerste groep zitten labels, in de tweede velden
		layout.setHorizontalGroup(layout.createSequentialGroup() //
				.addGroup(layout.createParallelGroup(Alignment.LEADING) //
						.addComponent(urlLabel) //
						.addComponent(userLabel) //
						.addComponent(passwordLabel) //
				) //
				.addGroup(layout.createParallelGroup(Alignment.LEADING) //
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
				.addGroup(layout.createParallelGroup(Alignment.CENTER) //
						.addComponent(urlLabel) //
						.addComponent(urlField) //
				) //
				.addGroup(layout.createParallelGroup(Alignment.CENTER) //
						.addComponent(userLabel) //
						.addComponent(userField) //
				) //
				.addGroup(layout.createParallelGroup(Alignment.CENTER) //
						.addComponent(passwordLabel) //
						.addComponent(passwordField) //
				) //
		);
	}
}