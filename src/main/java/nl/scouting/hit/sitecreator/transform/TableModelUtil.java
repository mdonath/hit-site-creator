package nl.scouting.hit.sitecreator.transform;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;

public final class TableModelUtil {
	/** Welke properties moeten altijd geskipped worden. */
	private static final Collection<String> EXCLUDES = Arrays.asList("class");

	public static LinkedHashMap<String, Object> getPropertiesInMap(
			final Object obj) {
		return getPropertiesInMap(obj, Collections.<String> emptyList());
	}

	public static LinkedHashMap<String, Object> getPropertiesInMap(
			final Object obj, final String... excludes) {
		return getPropertiesInMap(obj, Arrays.asList(excludes));
	}

	public static LinkedHashMap<String, Object> getPropertiesInMap(
			final Object obj, final Collection<String> excludes) {
		final LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>();
		try {
			final BeanInfo bi = Introspector.getBeanInfo(obj.getClass());
			for (final PropertyDescriptor pd : bi.getPropertyDescriptors()) {
				final String name = pd.getName();
				if (isIncluded(name, excludes)) {
					final Method readMethod = pd.getReadMethod();
					if (readMethod != null) {
						result.put(name,
								readMethod.invoke(obj, (Object[]) null));
					}
				}
			}
		} catch (final Exception ignore) {
		}
		return result;
	}

	protected static boolean isIncluded(final String name,
			final Collection<String> excludes) {
		return !(EXCLUDES.contains(name) || excludes.contains(name));
	}
}