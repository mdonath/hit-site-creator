package nl.scouting.hit.sitecreator.transform;

import javax.swing.JTable;

public class JHitTable extends JTable {
	private static final long serialVersionUID = 1L;

	public JHitTable(HitTableModel model) {
		super(model);
	}
}