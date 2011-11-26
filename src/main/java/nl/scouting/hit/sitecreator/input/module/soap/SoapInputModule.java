package nl.scouting.hit.sitecreator.input.module.soap;

import java.beans.PropertyChangeEvent;

import nl.scouting.hit.sitecreator.input.module.InputModule;
import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.ModelUtil;

/**
 * Haalt de input via Soap op.
 * <p>
 * <ul>
 * <li>Productie-WSDL: https://sol.scouting.nl/sol.wsdl
 * <li>Development-WSDL: http://dev1.scouting.nl/frontend/sol/sol_dev1.wsdl
 * </ul>
 * 
 * @author Martijn Donath
 */
public class SoapInputModule implements InputModule {

	private String url;
	private String user;
	private String password;

	private void connect() {
		System.out.println("Connect naar '" + user + ":" + password + "@" + url
				+ "'");
	}

	@Override
	public Hit load() {
		connect();
		return ModelUtil.createTestStructure();
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		final String propertyName = evt.getPropertyName();
		if ("url".equals(propertyName)) {
			url = (String) evt.getNewValue();
		} else if ("user".equals(propertyName)) {
			user = (String) evt.getNewValue();
		} else if ("password".equals(propertyName)) {
			password = (String) evt.getNewValue();
		}
	}

	@Override
	public String getType() {
		return "Soap";
	}

}
