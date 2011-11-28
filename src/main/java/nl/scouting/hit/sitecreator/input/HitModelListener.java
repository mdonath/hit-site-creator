package nl.scouting.hit.sitecreator.input;

import java.util.EventListener;

import nl.scouting.hit.sitecreator.model.HitEntiteit;
import nl.scouting.hit.sitecreator.model.HitProject;

public interface HitModelListener extends EventListener {

	void resetModel(HitModelListener.ResetEvent event);

	void updateModel(HitModelListener.UpdateEvent event);

	public static class DefaultHitModelListener implements HitModelListener {
		@Override
		public void resetModel(final HitModelListener.ResetEvent event) {
			// empty
		}

		@Override
		public void updateModel(final HitModelListener.UpdateEvent event) {
			// empty
		}
	}

	public static interface HitModelEvent {
		void notify(final HitModelListener[] ls);
	}

	public static class ResetEvent implements HitModelListener.HitModelEvent {
		private final HitEntiteit entityType;

		public ResetEvent(final HitEntiteit entityType) {
			this.entityType = entityType;
		}

		public HitEntiteit getEntityType() {
			return entityType;
		}

		@Override
		public void notify(final HitModelListener[] ls) {
			for (final HitModelListener l : ls) {
				l.resetModel(this);
			}
		}
	}

	public static class UpdateEvent extends HitModelListener.ResetEvent {
		private final HitProject hit;

		public UpdateEvent(final HitEntiteit entityType, final HitProject hit) {
			super(entityType);
			this.hit = hit;
		}

		public HitProject getHit() {
			return hit;
		}

		@Override
		public void notify(final HitModelListener[] ls) {
			for (final HitModelListener l : ls) {
				l.updateModel(this);
			}
		}
	}
}