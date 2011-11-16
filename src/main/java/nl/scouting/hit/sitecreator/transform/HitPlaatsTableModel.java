package nl.scouting.hit.sitecreator.transform;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitPlaats;

public class HitPlaatsTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private static final String[] KOLOM_KOPPEN = { "Kamp", "AkkoordKamp",
			"AkkoordPlaats", "Prijs", "Leeftijd", "Aantallen" };

	private final List<HitKamp> hitKampen;

	public HitPlaatsTableModel(final HitPlaats plaats) {
		hitKampen = plaats.getHitKampen();
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
	public Class getColumnClass(final int column) {
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
			return hitKamp.getNaam();
		case 1:
			return hitKamp.getAkkoordHitKamp();
		case 2:
			return hitKamp.getAkkoordHitPlaats();
		case 3:
			return "€ " + emptyIfNull(hitKamp.getDeelnamekosten());
		case 4:
			return minMax(hitKamp.getMinimumLeeftijd(),
					+hitKamp.getMaximumLeeftijd());
		case 5:
			return minMax(hitKamp.getMinimumAantalDeelnemers(),
					+hitKamp.getMaximumAantalDeelnemers());
		}
		return null;
	}

	private static String minMax(final Integer min, final Integer max) {
		return min + " - " + max;
	}

	private static Object emptyIfNull(final Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj;
		}
	}
}
