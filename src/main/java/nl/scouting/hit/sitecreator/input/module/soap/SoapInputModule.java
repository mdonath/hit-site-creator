package nl.scouting.hit.sitecreator.input.module.soap;

import java.beans.PropertyChangeEvent;

import nl.scouting.hit.sitecreator.input.InputModule;
import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.ModelUtil;

public class SoapInputModule implements InputModule {

	private String url;
	private String user;
	private String password;

	public Hit load() {
		connect();
		return ModelUtil.createTestStructure();
	}

	private void connect() {
		System.out.println("Connect naar '" + user + ":" + password + "@" + url
				+ "'");
	}

	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
 		if ("url".equals(propertyName)) {
			url = (String) evt.getNewValue();
		} else if ("user".equals(propertyName)) {
			user = (String) evt.getNewValue();
		} else if ("password".equals(propertyName)) {
			password = (String) evt.getNewValue();
		}
	}

}
