package nl.scouting.hit.sitecreator.output;

import java.beans.PropertyChangeListener;
import java.io.IOException;

import nl.scouting.hit.sitecreator.model.Hit;

public interface OutputModule extends PropertyChangeListener {
	void save(Hit hit) throws IOException;
}
