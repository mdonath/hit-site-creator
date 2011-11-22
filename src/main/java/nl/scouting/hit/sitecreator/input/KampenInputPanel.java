package nl.scouting.hit.sitecreator.input;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nl.scouting.hit.sitecreator.Application;
import nl.scouting.hit.sitecreator.input.module.csv.CsvKampFileImportPanel;
import nl.scouting.hit.sitecreator.input.module.soap.SoapInputPanel;
import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.util.UIUtil;

public class KampenInputPanel extends JPanel {
	public final class LoadAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public LoadAction() {
			super("Laad gegevens");
		}

		@Override
		public void actionPerformed(final ActionEvent e) {
			new AbstractLoader(currentInputModule) {
				@Override
				protected void loadFinished(final Hit hit) {
					KampenInputPanel.this.firePropertyChange("hit", null, hit);
				}
			}.execute();
		}
	}

	private static final long serialVersionUID = 1L;

	private InputModule currentInputModule;

	private final Application<Hit> application;

	public KampenInputPanel(final Application<Hit> application) {
		super(new BorderLayout());
		this.application = application;
		setName("Kampen");
		initComponents();
	}

	private void initComponents() {
		add(createTabPanel(), BorderLayout.CENTER);
		add(createButtonPanel(), BorderLayout.SOUTH);
	}

	private Component createButtonPanel() {
		final JPanel result = new JPanel();
		result.add(new JButton(new LoadAction()));
		return result;
	}

	private JTabbedPane createTabPanel() {
		final JTabbedPane tab = UIUtil.createTab( //
				new CsvKampFileImportPanel(application) //
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
}