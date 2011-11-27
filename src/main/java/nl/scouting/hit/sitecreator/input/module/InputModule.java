package nl.scouting.hit.sitecreator.input.module;

import java.beans.PropertyChangeListener;

import nl.scouting.hit.sitecreator.model.HitEntiteit;
import nl.scouting.hit.sitecreator.model.HitProject;

public interface InputModule extends PropertyChangeListener {

	class InputModuleException extends Exception {
		private static final long serialVersionUID = -6659880021291618425L;

		public InputModuleException(final Exception e) {
			super(e);
		}
	}

	HitProject load() throws InputModuleException;

	String getType();

	HitEntiteit getEntityType();
}
