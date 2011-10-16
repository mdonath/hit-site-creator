package nl.scouting.hit.sitecreator.output.module.joomla;

import java.beans.PropertyChangeEvent;

import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.output.OutputModule;

public class JoomlaOutputModule implements OutputModule {

	private String url;
	private String user;
	private String password;

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		final String propertyName = evt.getPropertyName();
		if ("url".equals(propertyName)) {
			this.url = (String) evt.getNewValue();
		} else if ("user".equals(propertyName)) {
			this.user = (String) evt.getNewValue();
		} else if ("password".equals(propertyName)) {
			this.password = (String) evt.getNewValue();
		}
	}

	@Override
	public void save(final Hit hit) {
		System.out.println("Save de inhoud van " + hit.getJaar()
				+ " naar Joomla op" + this.url + " met " + this.user + "@"
				+ this.password);
	}

}
