package nl.scouting.hit.sitecreator.input;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

import nl.scouting.hit.sitecreator.Application;
import nl.scouting.hit.sitecreator.ConfigKey;
import nl.scouting.hit.sitecreator.ConfigKey.FileConfigKey;
import nl.scouting.hit.sitecreator.ConfigKey.IntegerConfigKey;
import nl.scouting.hit.sitecreator.ConfigKey.StringConfigKey;
import nl.scouting.hit.sitecreator.input.module.FileImportModel;
import nl.scouting.hit.sitecreator.input.module.csv.CsvFileImportModel;
import nl.scouting.hit.sitecreator.model.HitEntiteit;
import nl.scouting.hit.sitecreator.model.HitProject;
import nl.scouting.hit.sitecreator.util.UIUtil;

public class UberInputPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private final Application<HitProject> application;

	public static final ConfigKey<Integer> CONFIG_JAAR = new IntegerConfigKey(
			"jaar");

	public UberInputPanel(final Application<HitProject> application) {
		super(new BorderLayout());
		this.application = application;
		initComponents();
	}

	private void initComponents() {
		setBorder(new TitledBorder("Alle soorten input"));

		add(createTabPanel(), BorderLayout.CENTER);
		add(createJaarPanel(), BorderLayout.NORTH);
	}

	protected void fireJaarChanged(final Object jaar) {
		firePropertyChange("jaar", null, jaar);
	}

	private Component createJaarPanel() {
		final JPanel result = new JPanel();
		final JLabel yearLabel = new JLabel("Jaar");

		final int huidigeJaar = Calendar.getInstance().get(Calendar.YEAR);
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
			yearField.setSelectedItem(application
					.getConfigurationValue(CONFIG_JAAR));
		} else {
			yearField.setSelectedItem(huidigeJaar);
		}

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
		final int startJaar = 2010;
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

	protected ProjectInputTabPanel createProjectPanel() {
		final FileImportModel model = createCsvFileImportModel("project");
		addPropertyChangeListener("jaar", model);

		final ProjectInputTabPanel result = new ProjectInputTabPanel(model);
		result.addPropertyChangeListener(HitEntiteit.Project.name(),
				new HitPropertyChangeListener() {
					@Override
					protected void merge(final HitProject oldHit,
							final HitProject newHit) {
						oldHit.mergeProject(newHit);
					}
				});
		return result;
	}

	protected PlaatsenInputTabPanel createPlaatsPanel() {
		final FileImportModel model = createCsvFileImportModel("plaats");
		addPropertyChangeListener("jaar", model);

		final PlaatsenInputTabPanel result = new PlaatsenInputTabPanel(model);

		result.addPropertyChangeListener(HitEntiteit.Plaats.name(),
				new HitPropertyChangeListener() {
					@Override
					protected void merge(final HitProject oldHit,
							final HitProject newHit) {
						oldHit.mergePlaatsen(newHit.getHitPlaatsen());
					}
				});
		return result;
	}

	protected KampenInputTabPanel createKampPanel() {
		final FileImportModel model = createCsvFileImportModel("kamp");
		addPropertyChangeListener("jaar", model);

		final KampenInputTabPanel result = new KampenInputTabPanel(model);

		result.addPropertyChangeListener(HitEntiteit.Kamp.name(),
				new HitPropertyChangeListener() {
					@Override
					protected void merge(final HitProject oldHit,
							final HitProject newHit) {
						oldHit.mergeKampen(newHit.getHitPlaatsen());
					}
				});
		return result;
	}

	private DeelnemersInputTabPanel createDeelnemerPanel() {

		final FileImportModel model = createCsvFileImportModel("dln");
		addPropertyChangeListener("jaar", model);
		final DeelnemersInputTabPanel result = new DeelnemersInputTabPanel(
				model);

		result.addPropertyChangeListener(HitEntiteit.Deelnemer.name(),
				new HitPropertyChangeListener() {
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

	abstract class HitPropertyChangeListener implements PropertyChangeListener {

		@Override
		public final void propertyChange(final PropertyChangeEvent evt) {
			final HitProject load = (HitProject) evt.getNewValue();
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

	protected void fireHitChanged(final HitProject hitProject) {
		firePropertyChange("hit", null, hitProject);
	}

	protected Application<HitProject> getApplication() {
		return application;
	}

}
