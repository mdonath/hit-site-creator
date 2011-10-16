package nl.scouting.hit.sitecreator.transform;

import javax.swing.table.AbstractTableModel;

import nl.scouting.hit.sitecreator.model.HitPlaats;

public class HitPlaatsTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private static final String[] KOLOMMEN = { "Naam" };

	private final HitPlaats hitPlaats;

	public HitPlaatsTableModel(HitPlaats hitPlaats) {
		this.hitPlaats = hitPlaats;
	}

	public int getRowCount() {
		return hitPlaats.getAantalKampen();
	}

	public int getColumnCount() {
		return KOLOMMEN.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return hitPlaats.getHitKampen().get(rowIndex).getNaam();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		return KOLOMMEN[column];
	}
}
