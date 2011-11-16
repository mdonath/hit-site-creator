package nl.scouting.hit.sitecreator.transform;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class JHitTable extends JTable {
	private static final long serialVersionUID = 1L;

	public JHitTable() {
		this(null);
	}

	public JHitTable(final TableModel model) {
		super(model);
		setPreferredSize(null);
	}
}