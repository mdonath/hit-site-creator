package nl.scouting.hit.sitecreator.input;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

import nl.scouting.hit.sitecreator.input.module.InputModule;
import nl.scouting.hit.sitecreator.model.HitProject;
import nl.scouting.hit.sitecreator.model.ModelUtil;
import nl.scouting.hit.sitecreator.util.UIUtil;

public abstract class AbstractInputTabPanel extends JPanel {
	private static final long serialVersionUID = -5685192209272680443L;

	private final EventListenerList ell = new EventListenerList();

	public void addHitModelListener(final HitModelListener l) {
		ell.add(HitModelListener.class, l);
	}

	public void removeHitModelListener(final HitModelListener l) {
		ell.remove(HitModelListener.class, l);
	}

	protected void notifyHitModelListeners(
			final HitModelListener.HitModelEvent event) {
		event.notify(ell.getListeners(HitModelListener.class));
	}

	private final class LoadAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		private abstract class AbstractLoader extends
				SwingWorker<HitProject, Void> {
			private final InputModule inputModule;

			public AbstractLoader(final InputModule inputModule) {
				this.inputModule = inputModule;
			}

			@Override
			protected final HitProject doInBackground() throws Exception {
				LoadAction.this.setEnabled(false);
				HitProject load;
				try {
					load = inputModule.load();
				} finally {
					LoadAction.this.setEnabled(true);
				}
				return load;
			}

			@Override
			protected final void done() {
				HitProject hit;
				try {
					hit = get();
				} catch (final Exception ignore) {
					ignore.printStackTrace();
					hit = ModelUtil.createEmptyStructure();
				}

				loadFinished(hit);
			}

			protected abstract void loadFinished(HitProject hit);

		}

		public LoadAction() {
			super("Laad gegevens");
		}

		@Override
		public void actionPerformed(final ActionEvent e) {
			new AbstractLoader(currentInputModule) {
				@Override
				protected void loadFinished(final HitProject hit) {
					notifyHitModelListeners(new HitModelListener.UpdateEvent(
							currentInputModule.getEntityType(), hit));
				}
			}.execute();
		}
	}

	private final class ResetAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public ResetAction() {
			super("Verwijder gegevens");
		}

		@Override
		public void actionPerformed(final ActionEvent e) {
			setEnabled(false);
			try {

				notifyHitModelListeners(new HitModelListener.ResetEvent(
						currentInputModule.getEntityType()));
			} finally {
				setEnabled(true);
			}
		}
	}

	protected InputModule currentInputModule;

	public AbstractInputTabPanel(final String name) {
		super(new BorderLayout());
		setName(name);
	}

	protected final Component createButtonPanel() {
		final JPanel result = new JPanel();
		result.add(new JButton(new LoadAction()));
		result.add(new JButton(new ResetAction()));
		return result;
	}

	protected final JTabbedPane createTabPanel(final JComponent... tabs) {
		final JTabbedPane tab = UIUtil.createTab( //
				tabs);
		tab.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(final ChangeEvent changeEvent) {
				final JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent
						.getSource();
				final Component selectedComponent = sourceTabbedPane
						.getSelectedComponent();
				if (selectedComponent != null) {
					currentInputModule = ((InputModuleUI) selectedComponent)
							.getProcessor();
				}
			}
		});
		tab.setSelectedIndex(-1);
		tab.setSelectedIndex(0);
		return tab;
	}

}
