package nl.scouting.hit.sitecreator.transform;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import nl.scouting.hit.sitecreator.Application;
import nl.scouting.hit.sitecreator.ApplicationLabels;
import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitPlaats;
import nl.scouting.hit.sitecreator.model.HitProject;
import nl.scouting.hit.sitecreator.model.ModelUtil;
import nl.scouting.hit.sitecreator.util.UIUtil;

public final class TransformPanel extends JPanel implements
		PropertyChangeListener {
	private static final long serialVersionUID = 1L;

	private JHitTree tree;
	private JTable propertyTable;
	private JTable detailTable;

	public TransformPanel(final Application<HitProject> application) {
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		setBorder(new TitledBorder(
				ApplicationLabels.getLabel("panel.transform.model")));

		final JSplitPane split = new JSplitPane( //
				JSplitPane.HORIZONTAL_SPLIT, //
				createTree(), //
				createTab() //
		);
		split.setDividerLocation(150);
		add(split, BorderLayout.CENTER);
		setProjectModels(ModelUtil.createEmptyStructure());
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(final TreeSelectionEvent e) {
				final Object obj = tree.getLastSelectedPathComponent();
				if (obj instanceof HitProject) {
					final HitProject hit = (HitProject) obj;
					setProjectModels(hit);
				} else if (obj instanceof HitPlaats) {
					final HitPlaats plaats = (HitPlaats) obj;
					setPlaatsModels(plaats);
				} else if (obj instanceof HitKamp) {
					final HitKamp kamp = (HitKamp) obj;
					setKampModels(kamp);
				}
			}

		});
	}

	private JComponent createTab() {
		final JTabbedPane tab = UIUtil.createTab( //
				createPropertyTable() //
				, createDetailTable() //
				);
		return tab;
	}

	private JComponent createPropertyTable() {
		return createScrollTable(
				ApplicationLabels.getLabel("panel.transform.eigenschappen"),
				propertyTable = new JHitTable());
	}

	private JComponent createDetailTable() {
		return createScrollTable(
				ApplicationLabels.getLabel("panel.transform.detail"),
				detailTable = new JHitTable());
	}

	protected JComponent createScrollTable(final String name, final JTable table) {
		final JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(-1, 200));
		scroll.setName(name);
		return scroll;
	}

	private JComponent createTree() {
		final JScrollPane scroll = new JScrollPane(tree = new JHitTree(
				new HitTreeModel(ModelUtil.createEmptyStructure())));
		return scroll;
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		if ("hit".equals(evt.getPropertyName())) {
			final HitProject hit = (HitProject) evt.getNewValue();
			tree.setModel(new HitTreeModel(hit));
			setProjectModels(hit);
		}
	}

	protected void setProjectModels(final HitProject hit) {
		detailTable.setModel(new HitProjectTableModel(hit));
		propertyTable.setModel(new BeanPropertyTableModel(hit, "aantalKampen",
				"beschikbareIconen", "gebruikteIconen", "hitPlaatsen",
				"kampenGesorteerd", "datumNu", "gebruikteIconenVoorCourant"));
	}

	protected void setPlaatsModels(final HitPlaats plaats) {
		detailTable.setModel(new HitPlaatsTableModel(plaats));
		propertyTable.setModel(new BeanPropertyTableModel(plaats, "hit",
				"hitKampen"));
	}

	protected void setKampModels(final HitKamp kamp) {
		detailTable.setModel(new HitKampTableModel(kamp));
		propertyTable.setModel(new BeanPropertyTableModel(kamp,
				"activiteitengebied", "activiteitengebieden", "icoontje",
				"icoontjes", "plaats", "plaatsNaam", "previous", "next"));
	}

}