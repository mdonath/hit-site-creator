package nl.scouting.hit.sitecreator.transform;

import java.awt.Dimension;

import javax.swing.JTree;

public class JHitTree extends JTree {
	private static final long serialVersionUID = 1L;

	public JHitTree(final HitTreeModel hitModel) {
		super(hitModel);
		setCellRenderer(new HitTreeCellRenderer());
		setPreferredSize(new Dimension(120, 0));
	}
}