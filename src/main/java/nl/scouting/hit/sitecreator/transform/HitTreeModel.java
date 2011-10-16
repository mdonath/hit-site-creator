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
	private Hit root;

	private List<TreeModelListener> treeModelListeners = new ArrayList<TreeModelListener>();

	public HitTreeModel(Hit hit) {
		root = hit;
	}

	public Object getRoot() {
		return root;
	}

	public Object getChild(Object parent, int index) {
		if (parent instanceof Hit) {
			return ((Hit) parent).getHitPlaatsen().get(index);
		} else if (parent instanceof HitPlaats) {
			return ((HitPlaats) parent).getHitKampen().get(index);
		}
		return null;
	}

	public int getChildCount(Object parent) {
		if (parent instanceof Hit) {
			return ((Hit) parent).getHitPlaatsen().size();
		} else if (parent instanceof HitPlaats) {
			return ((HitPlaats) parent).getHitKampen().size();
		}
		return 0;
	}

	public boolean isLeaf(Object node) {
		return node instanceof HitKamp;
	}

	public void valueForPathChanged(TreePath path, Object newValue) {
		// empty
	}

	public int getIndexOfChild(Object parent, Object child) {
		if (parent instanceof Hit) {
			return ((Hit) parent).getHitPlaatsen().indexOf(child);
		} else if (parent instanceof HitPlaats) {
			return ((HitPlaats) parent).getHitKampen().indexOf(child);
		}
		return -1;
	}

	public void addTreeModelListener(TreeModelListener l) {
		treeModelListeners.add(l);
	}

	public void removeTreeModelListener(TreeModelListener l) {
		treeModelListeners.remove(l);
	}
}