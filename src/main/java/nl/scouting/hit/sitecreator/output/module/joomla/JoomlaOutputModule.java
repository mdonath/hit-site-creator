package nl.scouting.hit.sitecreator.output.module.joomla;

import java.beans.PropertyChangeEvent;

import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.output.OutputModule;

public class JoomlaOutputModule implements OutputModule {

	private String url;
	private String user;
	private String password;

	public void save(Hit hit) {
		System.out.println("Save de inhoud van " + hit.getJaar()
				+ " naar Joomla op" + url + " met " + user + "@" + password);
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
