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

import nl.scouting.hit.sitecreator.Application;
import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitPlaats;
import nl.scouting.hit.sitecreator.model.ModelUtil;

public final class TransformPanel extends JPanel implements
		PropertyChangeListener {
	private static final long serialVersionUID = 1L;

	private JHitTree tree;
	private JTable table;

	public TransformPanel(final Application application) {
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Transform"));

		final JSplitPane split = new JSplitPane( //
				JSplitPane.HORIZONTAL_SPLIT, //
				createTree(), //
				createTable() //
		);
		split.setDividerLocation(150);
		add(split, BorderLayout.CENTER);

		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(final TreeSelectionEvent e) {
				final Object obj = tree.getLastSelectedPathComponent();
				if (obj instanceof Hit) {
					table.setModel(new HitTableModel((Hit) obj));
				} else if (obj instanceof HitPlaats) {
					table.setModel(new HitPlaatsTableModel((HitPlaats) obj));
				} else if (obj instanceof HitKamp) {
					table.setModel(new HitKampTableModel((HitKamp) obj));
				}
			}
		});
	}

	private JComponent createTable() {
		return new JScrollPane(table = new JHitTable(new HitTableModel(
				ModelUtil.createEmptyStructure())));
	}

	private JComponent createTree() {
		return new JScrollPane(tree = new JHitTree(new HitTreeModel(
				ModelUtil.createEmptyStructure())));
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		if ("hit".equals(evt.getPropertyName())) {
			final Hit hit = (Hit) evt.getNewValue();
			tree.setModel(new HitTreeModel(hit));
			table.setModel(new HitTableModel(hit));
		}
	}
}