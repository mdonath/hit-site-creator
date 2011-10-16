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

	private final List<HitKamp> hitKampen;

	public HitTableModel(final Hit hit) {
		this.hitKampen = new ArrayList<HitKamp>();
		for (final HitPlaats plaats : hit.getHitPlaatsen()) {
			for (final HitKamp kamp : plaats.getHitKampen()) {
				kamp.setPlaats(plaats);
				this.hitKampen.add(kamp);
			}
		}
	}

	@Override
	public int getColumnCount() {
		return KOLOM_KOPPEN.length;
	}

	@Override
	public String getColumnName(final int column) {
		return KOLOM_KOPPEN[column];
	}

	@Override
	public int getRowCount() {
		return this.hitKampen.size();
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		final HitKamp hitKamp = this.hitKampen.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return hitKamp.getPlaats().getNaam();
		case 1:
			return hitKamp.getNaam();
		}
		return null;
	}
}
