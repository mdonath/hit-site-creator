package nl.scouting.hit.sitecreator.transform;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitPlaats;

public final class HitTreeModel implements TreeModel {
	private final Hit root;

	private final List<TreeModelListener> treeModelListeners = new ArrayList<TreeModelListener>();

	public HitTreeModel(final Hit hit) {
		this.root = hit;
	}

	@Override
	public void addTreeModelListener(final TreeModelListener l) {
		this.treeModelListeners.add(l);
	}

	@Override
	public Object getChild(final Object parent, final int index) {
		if (parent instanceof Hit) {
			return ((Hit) parent).getHitPlaatsen().get(index);
		} else if (parent instanceof HitPlaats) {
			return ((HitPlaats) parent).getHitKampen().get(index);
		}
		return null;
	}

	@Override
	public int getChildCount(final Object parent) {
		if (parent instanceof Hit) {
			return ((Hit) parent).getHitPlaatsen().size();
		} else if (parent instanceof HitPlaats) {
			return ((HitPlaats) parent).getHitKampen().size();
		}
		return 0;
	}

	@Override
	public int getIndexOfChild(final Object parent, final Object child) {
		if (parent instanceof Hit) {
			return ((Hit) parent).getHitPlaatsen().indexOf(child);
		} else if (parent instanceof HitPlaats) {
			return ((HitPlaats) parent).getHitKampen().indexOf(child);
		}
		return -1;
	}

	@Override
	public Object getRoot() {
		return this.root;
	}

	@Override
	public boolean isLeaf(final Object node) {
		return node instanceof HitKamp;
	}

	@Override
	public void removeTreeModelListener(final TreeModelListener l) {
		this.treeModelListeners.remove(l);
	}

	@Override
	public void valueForPathChanged(final TreePath path, final Object newValue) {
		// empty
	}
}