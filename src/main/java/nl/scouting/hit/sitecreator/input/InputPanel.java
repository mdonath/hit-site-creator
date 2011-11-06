package nl.scouting.hit.sitecreator.input;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nl.scouting.hit.sitecreator.Application;
import nl.scouting.hit.sitecreator.input.module.csv.CsvFileImportPanel;
import nl.scouting.hit.sitecreator.input.module.soap.SoapInputPanel;
import nl.scouting.hit.sitecreator.input.module.xml.XmlInputPanel;
import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.ModelUtil;
import nl.scouting.hit.sitecreator.util.UIUtil;

public class InputPanel extends JPanel {
	public class LoadAction extends AbstractAction {
		public class Loader extends SwingWorker<Hit, Void> {

			private final InputModule inputModule;

			public Loader(final InputModule inputModule) {
				this.inputModule = inputModule;

			}

			@Override
			protected Hit doInBackground() throws Exception {
				return inputModule.load();
			}

			@Override
			protected void done() {
				Hit hit;
				try {
					hit = get();
				} catch (final Exception ignore) {
					hit = ModelUtil.createEmptyStructure();
				}
				InputPanel.this.firePropertyChange("hit", null, hit);
			}
		}

		private static final long serialVersionUID = 1L;

		public LoadAction() {
			super("Laad gegevens");
		}

		@Override
		public void actionPerformed(final ActionEvent e) {
			new Loader(currentInputModule).execute();
		}
	}

	private static final long serialVersionUID = 1L;

	private InputModule currentInputModule;

	private final Application application;

	public InputPanel(final Application application) {
		super(new BorderLayout());
		this.application = application;
		initComponents();
	}

	private Component createButtonPanel() {
		final JPanel result = new JPanel();
		result.add(new JButton(new LoadAction()));
		return result;
	}

	private JTabbedPane createTabPanel() {
		final JTabbedPane tab = UIUtil.createTab( //
				new CsvFileImportPanel(application) //
				, new XmlInputPanel(application) //
				, new SoapInputPanel(application) //
				);

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

	private void initComponents() {
		setBorder(new TitledBorder("Input"));
		add(createTabPanel(), BorderLayout.CENTER);
		add(createButtonPanel(), BorderLayout.SOUTH);
	}
}