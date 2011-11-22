package nl.scouting.hit.sitecreator.transform;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.Icoon;

public class HitKampTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private static final String[] KOLOM_KOPPEN = { "Icoon" };

	private final List<Icoon> data;

	public HitKampTableModel(final HitKamp hitKamp) {
		data = new ArrayList<Icoon>(hitKamp.getIcoontjes());
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
		return data.size();
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		final Icoon icoon = data.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return icoon.getTekst();
		}
		return null;
	}
}
