package nl.scouting.hit.sitecreator.transform;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import nl.scouting.hit.sitecreator.model.HitProject;
import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitPlaats;

public final class HitTreeCellRenderer implements TreeCellRenderer {
	private final JLabel titleLabel;
	private final JPanel renderer;
	private final DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();
	private final Color backgroundSelectionColor;
	private final Color backgroundNonSelectionColor;

	private static final String FMT = "%s (%d)";

	public HitTreeCellRenderer() {
		renderer = new JPanel(new GridLayout(0, 1));
		titleLabel = new JLabel(" ");
		renderer.add(titleLabel);
		backgroundSelectionColor = defaultRenderer
				.getBackgroundSelectionColor();
		backgroundNonSelectionColor = defaultRenderer
				.getBackgroundNonSelectionColor();
	}

	@Override
	public Component getTreeCellRendererComponent(final JTree tree,
			final Object value, final boolean selected, final boolean expanded,
			final boolean leaf, final int row, final boolean hasFocus) {
		Component returnValue = null;
		if (value != null) {
			if (value instanceof HitProject) {
				titleLabel.setText(printHit((HitProject) value));
			} else if (value instanceof HitPlaats) {
				titleLabel.setText(printHitPlaats((HitPlaats) value));
			} else if (value instanceof HitKamp) {
				titleLabel.setText(printHitKamp((HitKamp) value));
			}
			if (selected) {
				renderer.setBackground(backgroundSelectionColor);
			} else {
				renderer.setBackground(backgroundNonSelectionColor);
			}
			renderer.setEnabled(tree.isEnabled());
			returnValue = renderer;
		}
		if (returnValue == null) {
			returnValue = defaultRenderer.getTreeCellRendererComponent(tree,
					value, selected, expanded, leaf, row, hasFocus);
		}
		return returnValue;
	}

	private String printHit(final HitProject hit) {
		if (hit.getJaar() == null) {
			return "";
		}
		return String.format(FMT, hit.getJaar(), hit.getAantalKampen());
	}

	private String printHitPlaats(final HitPlaats plaats) {
		return String.format(FMT, plaats.getNaam(), plaats.getAantalKampen());
	}

	private String printHitKamp(final HitKamp kamp) {
		return kamp.getNaam();
	}
}