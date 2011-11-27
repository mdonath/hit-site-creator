package nl.scouting.hit.sitecreator.transform;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitPlaats;
import nl.scouting.hit.sitecreator.model.HitProject;

public class HitProjectTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private static final String[] KOLOM_KOPPEN = { "Plaats", "Kamp",
			"AkkoordKamp", "AkkoordPlaats", "Prijs" };

	private final List<HitKamp> hitKampen;

	public HitProjectTableModel(final HitProject hit) {
		hitKampen = new ArrayList<HitKamp>();
		if (hit != null) {
			for (final HitPlaats plaats : hit.getHitPlaatsen()) {
				for (final HitKamp kamp : plaats.getHitKampen()) {
					kamp.setPlaats(plaats);
					hitKampen.add(kamp);
				}
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
	public Class<?> getColumnClass(final int column) {
		return (getValueAt(0, column).getClass());
	}

	@Override
	public int getRowCount() {
		return hitKampen.size();
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		final HitKamp hitKamp = hitKampen.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return hitKamp.getPlaats().getNaam();
		case 1:
			return hitKamp.getNaam();
		case 2:
			return hitKamp.getAkkoordHitKamp();
		case 3:
			return hitKamp.getAkkoordHitPlaats();
		case 4:
			return "€ " + emptyIfNull(hitKamp.getDeelnamekosten());
		}
		return null;
	}

	private static Object emptyIfNull(final Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj;
		}
	}
}
