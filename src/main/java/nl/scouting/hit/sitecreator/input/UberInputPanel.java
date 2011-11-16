package nl.scouting.hit.sitecreator.input;

import java.awt.BorderLayout;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

import nl.scouting.hit.sitecreator.Application;
import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.util.UIUtil;

public class UberInputPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private final Application<Hit> application;

	public UberInputPanel(final Application<Hit> application) {
		super(new BorderLayout());
		this.application = application;
		initComponents();
	}

	private void initComponents() {
		setBorder(new TitledBorder("Alle soorten input"));
		add(createTabPanel(), BorderLayout.CENTER);
	}

	private Component createTabPanel() {
		final ProjectInputPanel project = new ProjectInputPanel(application);
		project.addPropertyChangeListener("hit", new PropertyChangeListener() {
			@Override
			public void propertyChange(final PropertyChangeEvent evt) {
				final Hit load = (Hit) evt.getNewValue();
				Hit hit = application.getModel();
				if (hit == null) {
					hit = load;
					application.setModel(hit);
				} else {
					hit.merge(load);
				}
				firePropertyChange("hit", null, hit);
			}
		});

		final PlaatsenInputPanel plaats = new PlaatsenInputPanel(application);
		plaats.addPropertyChangeListener("hit", new PropertyChangeListener() {
			@Override
			public void propertyChange(final PropertyChangeEvent evt) {
				final Hit load = (Hit) evt.getNewValue();
				Hit hit = application.getModel();
				if (hit == null) {
					hit = load;
					application.setModel(hit);
				} else {
					hit.merge(load.getHitPlaatsen());
				}
				firePropertyChange("hit", null, hit);
			}
		});

		final KampenInputPanel kamp = new KampenInputPanel(application);
		kamp.addPropertyChangeListener("hit", new PropertyChangeListener() {
			@Override
			public void propertyChange(final PropertyChangeEvent evt) {
				final Hit load = (Hit) evt.getNewValue();
				Hit hit = application.getModel();
				if (hit == null) {
					hit = load;
					application.setModel(hit);
				} else {
					hit.mergeKampen(load.getHitPlaatsen());
				}
				firePropertyChange("hit", null, hit);
			}
		});

		final JTabbedPane tab = UIUtil.createTab( //
				project, plaats, kamp);

		tab.setSelectedIndex(-1);
		tab.setSelectedIndex(0);
		return tab;
	}
}