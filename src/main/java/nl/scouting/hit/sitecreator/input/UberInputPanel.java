package nl.scouting.hit.sitecreator.input;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import nl.scouting.hit.sitecreator.Application;
import nl.scouting.hit.sitecreator.ConfigKey;
import nl.scouting.hit.sitecreator.ConfigKey.FileConfigKey;
import nl.scouting.hit.sitecreator.ConfigKey.IntegerConfigKey;
import nl.scouting.hit.sitecreator.ConfigKey.StringConfigKey;
import nl.scouting.hit.sitecreator.input.module.FileImportModel;
import nl.scouting.hit.sitecreator.input.module.csv.CsvFileImportModel;
import nl.scouting.hit.sitecreator.model.HitPlaats;
import nl.scouting.hit.sitecreator.model.HitProject;
import nl.scouting.hit.sitecreator.util.UIUtil;

public class UberInputPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public static final ConfigKey<Integer> CONFIG_JAAR = new IntegerConfigKey(
			"jaar");

	private final Application<HitProject> application;

	public UberInputPanel(final Application<HitProject> application) {
		super(new BorderLayout());
		this.application = application;

		initComponents();
	}

	private void initComponents() {
		add(createTabPanel(), BorderLayout.CENTER);
		add(createJaarPanel(), BorderLayout.NORTH);
	}

	private Component createJaarPanel() {
		final JPanel result = new JPanel();
		final JLabel yearLabel = new JLabel("Jaar");

		int huidigeJaar = Calendar.getInstance().get(Calendar.YEAR);
		final JComboBox yearField = new JComboBox(createJaarItems(huidigeJaar));
		yearField.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(final ItemEvent e) {
				final Object[] selected = e.getItemSelectable()
						.getSelectedObjects();
				if (selected != null) {
					fireJaarChanged(selected[0]);
				}
			}
		});

		if (application.hasConfigurationValue(CONFIG_JAAR)) {
			huidigeJaar = application.getConfigurationValue(CONFIG_JAAR);
		}
		yearField.setSelectedItem(huidigeJaar);
		fireJaarChanged(huidigeJaar);

		final GroupLayout layout = UIUtil.createGroupLayout(result);
		layout.setHorizontalGroup(layout.createSequentialGroup() //
				.addGroup(layout.createParallelGroup(Alignment.LEADING) //
						.addComponent(yearLabel) //
				) //
				.addGroup(layout.createParallelGroup(Alignment.LEADING) //
						.addComponent(yearField) //
				) //
		);
		layout.setVerticalGroup(layout.createSequentialGroup() //
				.addGroup(layout.createParallelGroup(Alignment.CENTER) //
						.addComponent(yearLabel) //
						.addComponent(yearField) //
				) //
		);

		return result;
	}

	private static Integer[] createJaarItems(final int huidigeJaar) {
		final int startJaar = 2012;
		final int aantal = huidigeJaar - startJaar;
		final Integer[] items = new Integer[aantal + 2];
		for (int i = 0; i < items.length; i++) {
			items[i] = Integer.valueOf(startJaar + i);
		}
		return items;
	}

	private Component createTabPanel() {

		final JTabbedPane tab = UIUtil.createTab( //
				createProjectPanel() //
				, createPlaatsPanel() //
				, createKampPanel() //
				, createDeelnemerPanel());

		tab.setSelectedIndex(-1);
		tab.setSelectedIndex(0);
		return tab;
	}

	protected AbstractInputTabPanel createProjectPanel() {
		final FileImportModel model = createCsvFileImportModel("project");
		addPropertyChangeListener("jaar", model);

		final ProjectInputTabPanel result = new ProjectInputTabPanel(model);
		result.addHitModelListener(new HitModelChangeListener() {

			@Override
			public void resetModel(final ResetEvent event) {
				final HitProject hit = new HitProject(getApplication()
						.getConfigurationValue(CONFIG_JAAR));
				getApplication().setModel(null);
				fireHitChanged(null);
			}

			@Override
			protected void merge(final HitProject oldHit,
					final HitProject newHit) {
				oldHit.mergeProject(newHit);
			}
		});
		return result;
	}

	protected AbstractInputTabPanel createPlaatsPanel() {
		final FileImportModel model = createCsvFileImportModel("plaats");
		addPropertyChangeListener("jaar", model);

		final PlaatsenInputTabPanel result = new PlaatsenInputTabPanel(model);

		result.addHitModelListener(new HitModelChangeListener() {

			@Override
			public void resetModel(final ResetEvent event) {
				final HitProject hit = getApplication().getModel();
				hit.getHitPlaatsen().clear();
				fireHitChanged(hit);
			}

			@Override
			protected void merge(final HitProject oldHit,
					final HitProject newHit) {
				oldHit.mergePlaatsen(newHit.getHitPlaatsen());
			}

		});
		return result;
	}

	protected AbstractInputTabPanel createKampPanel() {
		final FileImportModel model = createCsvFileImportModel("kamp");
		addPropertyChangeListener("jaar", model);

		final KampenInputTabPanel result = new KampenInputTabPanel(model);

		result.addHitModelListener(new HitModelChangeListener() {

			@Override
			public void resetModel(final ResetEvent event) {
				final HitProject hit = getApplication().getModel();
				for (final HitPlaats p : hit.getHitPlaatsen()) {
					p.getHitKampen().clear();
				}
				fireHitChanged(hit);
			}

			@Override
			protected void merge(final HitProject oldHit,
					final HitProject newHit) {
				oldHit.mergeKampen(newHit.getHitPlaatsen());
			}

		});
		return result;
	}

	private AbstractInputTabPanel createDeelnemerPanel() {

		final FileImportModel model = createCsvFileImportModel("dln");
		addPropertyChangeListener("jaar", model);
		final DeelnemersInputTabPanel result = new DeelnemersInputTabPanel(
				model);
		result.addHitModelListener(new HitModelChangeListener() {

			@Override
			public void resetModel(final ResetEvent event) {
				final HitProject hit = getApplication().getModel();
				hit.getHitPlaatsen().clear();
				fireHitChanged(hit);
			}

			@Override
			protected void merge(final HitProject oldHit,
					final HitProject newHit) {
				oldHit.mergeDeelnemers(newHit.getHitPlaatsen());
			}

		});

		return result;
	}

	protected FileImportModel createCsvFileImportModel(final String wat) {
		return new CsvFileImportModel(
				application.getConfigurationValue(new FileConfigKey(wat + "csv")), //
				application.getConfigurationValue(new StringConfigKey(wat
						+ "enc")), //
				application.getConfigurationValue(new IntegerConfigKey("jaar")) //
		);
	}

	abstract class HitModelChangeListener implements HitModelListener {

		@Override
		public final void updateModel(final UpdateEvent event) {
			final HitProject load = event.getHit();
			HitProject hit = getApplication().getModel();
			if (hit == null) {
				hit = load;
				getApplication().setModel(hit);
			} else {
				merge(hit, load);
			}
			fireHitChanged(hit);
		}

		/**
		 * Mergt de twee hit projecten.
		 * 
		 * @param oldHit
		 * @param newHit
		 */
		protected abstract void merge(HitProject oldHit, HitProject newHit);

	}

	protected void fireJaarChanged(final Object jaar) {
		firePropertyChange("jaar", null, jaar);
	}

	protected void fireHitChanged(final HitProject hitProject) {
		firePropertyChange("hit", null, hitProject);
	}

	protected Application<HitProject> getApplication() {
		return application;
	}

}
