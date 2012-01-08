package nl.scouting.hit.sitecreator.input.module.csv;

import java.io.InputStream;

/**
 * Maakt ColumnMapperHelpers op basis van jaartal.
 * 
 * @author martijn
 */
public final class ColumnMapperHelperFactory {

	public static class FactoryException extends Exception {
		private static final long serialVersionUID = 1L;

		public FactoryException(final Exception e) {
			super(e);
		}
	}

	public static final ColumnMapperHelper getColumnMapperHelperForYear(
			final int jaar, final Class<?> modelClass) throws FactoryException {
		return getColumnMapperHelper(ColumnMapperHelperFactory.class
				.getResourceAsStream("/nl/scouting/hit/sitecreator/input/module/csv/"
						+ modelClass.getSimpleName().toLowerCase()
						+ "-mapping-" + jaar + ".properties"));
	}

	public static final ColumnMapperHelper getColumnMapperHelper(
			final InputStream inputStream) throws FactoryException {
		try {
			return new ColumnMapperHelperByInputStream(inputStream);
		} catch (final MappingException e) {
			throw new FactoryException(e);
		}
	}
}
