package nl.scouting.hit.sitecreator.input;

import java.beans.PropertyChangeListener;

import nl.scouting.hit.sitecreator.model.Hit;

public interface InputModule extends PropertyChangeListener {

	Hit load();
}
