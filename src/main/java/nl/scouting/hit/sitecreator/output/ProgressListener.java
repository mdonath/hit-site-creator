package nl.scouting.hit.sitecreator.output;

import java.util.EventListener;

/**
 * Nodig voor het weergeven van voortgang.
 */
public interface ProgressListener extends EventListener {
	/**
	 * Wordt aangeroepen als er voortgang te melden is.
	 * 
	 * @param event
	 */
	void progress(ProgressListener.ProgressListenerEvent event);

	public static class ProgressListenerEvent {
		private final int total;
		private final int index;

		public ProgressListenerEvent(final int index, final int total) {
			this.index = index;
			this.total = total;
		}

		public int getTotal() {
			return total;
		}

		public int getIndex() {
			return index;
		}
	}
}