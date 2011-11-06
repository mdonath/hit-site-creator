package nl.scouting.hit.sitecreator.transform;

import javax.swing.JTree;

public class JHitTree extends JTree {
	private static final long serialVersionUID = 1L;

	public JHitTree(final HitTreeModel hitModel) {
		super(hitModel);
		setCellRenderer(new HitTreeCellRenderer());
		setPreferredSize(null);
	}
}