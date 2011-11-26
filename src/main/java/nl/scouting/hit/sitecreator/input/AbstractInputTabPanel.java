package nl.scouting.hit.sitecreator.input;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nl.scouting.hit.sitecreator.input.module.InputModule;
import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.util.UIUtil;

public abstract class AbstractInputTabPanel extends JPanel {
	private static final long serialVersionUID = -5685192209272680443L;

	private final class LoadAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public LoadAction() {
			super("Laad gegevens");
		}

		@Override
		public void actionPerformed(final ActionEvent e) {
			new AbstractLoader(currentInputModule) {
				@Override
				protected void loadFinished(final Hit hit) {
					AbstractInputTabPanel.this.firePropertyChange("hit", null,
							hit);
				}
			}.execute();
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
