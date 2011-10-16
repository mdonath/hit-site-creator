package nl.scouting.hit.sitecreator.transform;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitPlaats;
import nl.scouting.hit.sitecreator.model.ModelUtil;

public final class TransformPanel extends JPanel implements
		PropertyChangeListener {
	private static final long serialVersionUID = 1L;

	private JHitTree tree;
	private JTable table;

	public TransformPanel() {
		initComponents();
	}

	public JComponent createTable() {
		return new JScrollPane(this.table = new JHitTable(new HitTableModel(
				ModelUtil.createEmptyStructure())));
	}

	public JHitTree createTree() {
		return this.tree = new JHitTree(new HitTreeModel(
				ModelUtil.createEmptyStructure()));
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Transform"));

		add(new JSplitPane( //
				JSplitPane.HORIZONTAL_SPLIT, //
				createTree(), //
				createTable() //
		), BorderLayout.CENTER);

		this.tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(final TreeSelectionEvent e) {
				final Object obj = TransformPanel.this.tree
						.getLastSelectedPathComponent();
				if (obj instanceof Hit) {
					TransformPanel.this.table.setModel(new HitTableModel(
							(Hit) obj));
				} else if (obj instanceof HitPlaats) {
					TransformPanel.this.table.setModel(new HitPlaatsTableModel(
							(HitPlaats) obj));
				} else if (obj instanceof HitKamp) {
					final HitKamp sel = (HitKamp) obj;
					TransformPanel.this.table.setModel(new HitPlaatsTableModel(
							sel.getPlaats()));
					final int index = sel.getPlaats().getHitKampen()
							.indexOf(sel);
					TransformPanel.this.table.getSelectionModel()
							.setSelectionInterval(index, index);
				}
			}
		});
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		if ("hit".equals(evt.getPropertyName())) {
			final Hit hit = (Hit) evt.getNewValue();
			this.tree.setModel(new HitTreeModel(hit));
			this.table.setModel(new HitTableModel(hit));
		}
	}
}