package nl.scouting.hit.sitecreator.transform;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitPlaats;

public class HitTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private static final String[] KOLOM_KOPPEN = { "Plaats", "Kamp" };

	private List<HitKamp> hitKampen;

	public HitTableModel(final Hit hit) {
		hitKampen = new ArrayList<HitKamp>();
		for (HitPlaats plaats : hit.getHitPlaatsen()) {
			for (HitKamp kamp : plaats.getHitKampen()) {
				kamp.setPlaats(plaats);
				hitKampen.add(kamp);
			}
		}
	}

	public int getRowCount() {
		return hitKampen.size();
	}

	public int getColumnCount() {
		return KOLOM_KOPPEN.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		HitKamp hitKamp = hitKampen.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return hitKamp.getPlaats().getNaam();
		case 1:
			return hitKamp.getNaam();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		return KOLOM_KOPPEN[column];
	}
}
