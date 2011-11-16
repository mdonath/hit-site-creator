package nl.scouting.hit.sitecreator.transform;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class BeanPropertyTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private static final String[] KOLOMMEN = { "Veld", "Waarde" };

	private final LinkedHashMap<String, Object> values;
	private final List<String> keys;

	public BeanPropertyTableModel(final Object object, final String... excludes) {
		values = TableModelUtil.getPropertiesInMap(object, excludes);
		keys = new ArrayList<String>(values.keySet());
	}

	@Override
	public final int getColumnCount() {
		return KOLOMMEN.length;
	}

	@Override
	public final String getColumnName(final int column) {
		return KOLOMMEN[column];
	}

	@Override
	public final int getRowCount() {
		return values.size();
	}

	@Override
	public final Object getValueAt(final int rowIndex, final int columnIndex) {
		final String key = keys.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return key;
		case 1:
			return values.get(key);
		}
		return null;
	}
}
