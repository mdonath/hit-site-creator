package nl.scouting.hit.sitecreator.output.module;

import java.util.HashSet;
import java.util.Set;

import nl.scouting.hit.sitecreator.output.OutputModule;
import nl.scouting.hit.sitecreator.output.ProgressListener;
import nl.scouting.hit.sitecreator.output.ProgressListener.ProgressListenerEvent;

public abstract class AbstractProgressOutputModule implements OutputModule {
	private final Set<ProgressListener> progressListeners = new HashSet<ProgressListener>();

	public void addProgressListener(final ProgressListener progressListener) {
		progressListeners.add(progressListener);
	}

	public void removeProgressListener(final ProgressListener progressListener) {
		progressListeners.remove(progressListener);
	}

	protected void fireProgressListenerEvent(final int index, final int total) {
		ProgressListenerEvent event = null;
		for (final ProgressListener l : progressListeners) {
			if (event == null) {
				event = new ProgressListenerEvent(index, total);
			}
			l.progress(event);
		}
	}
}
