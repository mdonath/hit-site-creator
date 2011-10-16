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

	private void initComponents() {
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Transform"));

		add(new JSplitPane( //
				JSplitPane.HORIZONTAL_SPLIT, //
				createTree(), //
				createTable() //
		), BorderLayout.CENTER);

		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				Object obj = tree.getLastSelectedPathComponent();
				if (obj instanceof Hit) {
					table.setModel(new HitTableModel((Hit) obj));
				} else if (obj instanceof HitPlaats) {
					table.setModel(new HitPlaatsTableModel((HitPlaats) obj));
				} else if (obj instanceof HitKamp) {
					HitKamp sel = (HitKamp) obj;
					table.setModel(new HitPlaatsTableModel(sel.getPlaats()));
					int index = sel.getPlaats().getHitKampen().indexOf(sel);
					table.getSelectionModel()
							.setSelectionInterval(index, index);
				}
			}
		});
	}

	public JHitTree createTree() {
		return tree = new JHitTree(new HitTreeModel(
				ModelUtil.createEmptyStructure()));
	}

	public JComponent createTable() {
		return new JScrollPane(table = new JHitTable(new HitTableModel(
				ModelUtil.createEmptyStructure())));
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if ("hit".equals(evt.getPropertyName())) {
			Hit hit = (Hit) evt.getNewValue();
			tree.setModel(new HitTreeModel(hit));
			table.setModel(new HitTableModel(hit));
		}
	}
}