package nl.scouting.hit.sitecreator.output;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nl.scouting.hit.sitecreator.Application;
import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.output.module.html.HtmlOutputPanel;
import nl.scouting.hit.sitecreator.output.module.joomla.JoomlaOutputPanel;
import nl.scouting.hit.sitecreator.util.UIUtil;

public final class OutputPanel extends JPanel implements PropertyChangeListener {

	public final class SaveAction extends AbstractAction {
		public class Saver extends SwingWorker<Void, Void> {

			private final OutputModule outputModule;
			private final Hit hit;

			public Saver(final OutputModule outputModule, final Hit hit) {
				this.outputModule = outputModule;
				this.hit = hit;
			}

			@Override
			protected Void doInBackground() throws Exception {
				outputModule.save(hit);
				return null;
			}

			@Override
			protected void done() {
				Void hit;
				try {
					hit = get();
				} catch (final Exception ignore) {
					ignore.printStackTrace();
					hit = null;
				}
				OutputPanel.this.firePropertyChange("save", null, hit);
			}
		}

		private static final long serialVersionUID = 1L;

		public SaveAction() {
			super("Maak bestanden");
		}

		@Override
		public void actionPerformed(final ActionEvent e) {
			new Saver(currentOutputModule, hit).execute();
		}
	}

	private static final long serialVersionUID = 1L;

	private OutputModule currentOutputModule;

	private Hit hit;
	private final Application application;

	public OutputPanel(final Application application) {
		this.application = application;
		initComponents();
	}

	private Component createButtonPanel() {
		final JPanel result = new JPanel();
		result.add(new JButton(new SaveAction()));
		return result;
	}

	private JTabbedPane createTabPanel() {
		final JTabbedPane tab = UIUtil.createTab( //
				new HtmlOutputPanel(application) //
				, new JoomlaOutputPanel(application) //
				);
		tab.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(final ChangeEvent changeEvent) {
				final JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent
						.getSource();
				final Component selectedComponent = sourceTabbedPane
						.getSelectedComponent();
				if (selectedComponent != null) {
					currentOutputModule = ((OutputModuleUI) selectedComponent)
							.getProcessor();
				}
			}
		});
		tab.setSelectedIndex(-1);
		tab.setSelectedIndex(0);
		return tab;
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Output"));
		add(createTabPanel(), BorderLayout.CENTER);
		add(createButtonPanel(), BorderLayout.SOUTH);
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		if ("hit".equals(evt.getPropertyName())) {
			final Hit hit = (Hit) evt.getNewValue();
			this.hit = hit;
		}
	}

}