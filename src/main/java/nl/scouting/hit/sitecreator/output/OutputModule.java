package nl.scouting.hit.sitecreator.output;

import java.beans.PropertyChangeListener;

import nl.scouting.hit.sitecreator.model.Hit;

public interface OutputModule extends PropertyChangeListener {
	void save(Hit hit);
}
