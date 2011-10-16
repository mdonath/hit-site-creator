package nl.scouting.hit.sitecreator.transform;

import javax.swing.table.AbstractTableModel;

import nl.scouting.hit.sitecreator.model.HitPlaats;

public class HitPlaatsTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private static final String[] KOLOMMEN = { "Naam" };

	private final HitPlaats hitPlaats;

	public HitPlaatsTableModel(final HitPlaats hitPlaats) {
		this.hitPlaats = hitPlaats;
	}

	@Override
	public int getColumnCount() {
		return KOLOMMEN.length;
	}

	@Override
	public String getColumnName(final int column) {
		return KOLOMMEN[column];
	}

	@Override
	public int getRowCount() {
		return this.hitPlaats.getAantalKampen();
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		switch (columnIndex) {
		case 0:
			return this.hitPlaats.getHitKampen().get(rowIndex).getNaam();
		}
		return null;
	}
}
