package nl.scouting.hit.sitecreator.output;

import java.beans.PropertyChangeListener;
import java.io.IOException;

import nl.scouting.hit.sitecreator.model.HitProject;

public interface OutputModule extends PropertyChangeListener {
	/**
	 * Bewaart, genereert, of doet iets met het meegegeven {@link HitProject} object.
	 * 
	 * @param hit
	 * @throws IOException
	 */
	void save(HitProject hit) throws IOException;
}
