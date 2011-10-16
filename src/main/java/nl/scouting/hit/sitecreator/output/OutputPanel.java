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

import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.output.module.html.HtmlOutputPanel;
import nl.scouting.hit.sitecreator.output.module.joomla.JoomlaOutputPanel;
import nl.scouting.hit.sitecreator.util.UIUtil;

public class OutputPanel extends JPanel implements PropertyChangeListener {
	private static final long serialVersionUID = 1L;

	private OutputModule currentOutputModule;
	private Hit hit;

	public OutputPanel() {
		super(new BorderLayout());
		initComponents();
	}

	private void initComponents() {
		setBorder(new TitledBorder("Output"));
		add(createTabPanel(), BorderLayout.CENTER);
		add(createButtonPanel(), BorderLayout.SOUTH);
	}

	private JTabbedPane createTabPanel() {
		JTabbedPane tab = UIUtil.createTab( //
				new HtmlOutputPanel() //
				, new JoomlaOutputPanel() //
				);
		tab.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent
						.getSource();
				Component selectedComponent = sourceTabbedPane
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

	private Component createButtonPanel() {
		JPanel result = new JPanel();
		result.add(new JButton(new SaveAction()));
		return result;
	}

	public class SaveAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public SaveAction() {
			super("Maak bestanden");
		}

		public void actionPerformed(ActionEvent e) {
 			new Saver(currentOutputModule, hit).execute();
		}

		public class Saver extends SwingWorker<Void, Void> {

			private final OutputModule outputModule;
			private final Hit hit;

			public Saver(OutputModule outputModule, Hit hit) {
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
				} catch (Exception ignore) {
					hit = null;
				}
				OutputPanel.this.firePropertyChange("save", null, hit);
			}
		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if ("hit".equals(evt.getPropertyName())) {
			Hit hit = (Hit) evt.getNewValue();
			this.hit = hit;
		}
	}
}