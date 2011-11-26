package nl.scouting.hit.sitecreator.components;

import java.util.EventListener;

import javax.swing.event.EventListenerList;

import nl.scouting.hit.sitecreator.components.AbstractEventFirer.ModelListener;

public abstract class AbstractEventFirer<MEV, ML extends ModelListener<MEV>> {

	public interface ModelListener<MEV> extends EventListener {
		void modelChanged(MEV event);
	}

	private EventListenerList ell;
	private final Class<ML> clazz;

	public AbstractEventFirer(final Class<ML> clazz) {
		this.clazz = clazz;
	}

	public final void addModelListener(final ML l) {
		checkEventListenerList();
		if (l != null) {
			ell.add(clazz, l);
		}
	}

	private void checkEventListenerList() {
		if (ell == null) {
			ell = new EventListenerList();
		}
	}

	public final void removeModelListener(final ML l) {
		if ((ell != null) && (l != null)) {
			ell.remove(clazz, l);
		}
	}

	protected void notifyModelEvent(final MEV event) {
		if ((ell != null) && (event != null)) {
			for (final ML l : ell.getListeners(clazz)) {
				l.modelChanged(event);
			}
		}
	}

}
