package nl.scouting.hit.sitecreator.transform;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import nl.scouting.hit.sitecreator.ApplicationLabels;

public class BeanPropertyTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private static final String[] KOLOM_KOPPEN = { //
	ApplicationLabels.getLabel("panel.transform.bean.veld"), //
			ApplicationLabels.getLabel("panel.transform.bean.waarde"), //
	};

	private final LinkedHashMap<String, Object> values;
	private final List<String> keys;

	public BeanPropertyTableModel(final Object object, final String... excludes) {
		values = TableModelUtil.getPropertiesInMap(object, excludes);
		keys = new ArrayList<String>(values.keySet());
	}

	@Override
	public final int getColumnCount() {
		return KOLOM_KOPPEN.length;
	}

	@Override
	public final String getColumnName(final int column) {
		return KOLOM_KOPPEN[column];
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
