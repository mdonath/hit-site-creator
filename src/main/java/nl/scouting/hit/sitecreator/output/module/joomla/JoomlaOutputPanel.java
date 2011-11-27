package nl.scouting.hit.sitecreator.output.module.joomla;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import nl.scouting.hit.sitecreator.Application;
import nl.scouting.hit.sitecreator.components.TextChangedDocumentListener;
import nl.scouting.hit.sitecreator.model.HitProject;
import nl.scouting.hit.sitecreator.output.OutputModule;
import nl.scouting.hit.sitecreator.output.module.AbstractOutputPanel;
import nl.scouting.hit.sitecreator.util.UIUtil;

/**
 * Panel voor output naar Joomla.
 * 
 * @author Martijn Donath
 */
public final class JoomlaOutputPanel extends AbstractOutputPanel {
	private final class TextChangeNotifierListener extends
			TextChangedDocumentListener {

		private TextChangeNotifierListener(final String propertyName) {
			super(propertyName);
		}

		@Override
		protected void textChanged(final String propertyName,
				final String oldValue, final String newValue) {
			// Moet persé in een eigen class, omdat firePropertyChange protected
			// is
			firePropertyChange(propertyName, oldValue, newValue);
		}
	}

	private static final long serialVersionUID = 1L;

	private JoomlaOutputModule outputModule;

	public JoomlaOutputPanel(final Application<HitProject> application) {
		initComponents();
	}

	/** {@inheritDoc} */
	@Override
	public OutputModule getProcessor() {
		if (outputModule == null) {
			outputModule = new JoomlaOutputModule();
			addPropertyChangeListener("save", outputModule);
		}
		return outputModule;
	}

	private void initComponents() {
		setName("Joomla");
		setBackground(Color.RED); // FIXME joomla-output doet het nog niet

		final JLabel urlLabel = new JLabel("Joomla URL");
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

}