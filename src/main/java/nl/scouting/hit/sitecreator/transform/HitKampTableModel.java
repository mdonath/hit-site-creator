package nl.scouting.hit.sitecreator.transform;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import nl.scouting.hit.sitecreator.model.HitKamp;

public class HitKampTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private static final String[] KOLOMMEN = { "Veld", "Waarde" };

	private final HitKamp hitKamp;
	private final LinkedHashMap<String, Object> values;
	private final List<String> keys;

	public HitKampTableModel(final HitKamp hitKamp) {
		this.hitKamp = hitKamp;

		values = new LinkedHashMap<String, Object>();
		keys = new ArrayList<String>();
		try {
			final BeanInfo bi = Introspector.getBeanInfo(HitKamp.class);
			for (final PropertyDescriptor pd : bi.getPropertyDescriptors()) {
				final Method readMethod = pd.getReadMethod();
				if (readMethod != null) {
					keys.add(pd.getName());
					values.put(pd.getName(),
							readMethod.invoke(hitKamp, (Object[]) null));
				}
			}
		} catch (final IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		return values.size();
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
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
