package nl.scouting.hit.sitecreator.input.module.csv;

public final class ColumnMapperHelperFactory {

	public static class FactoryException extends Exception {
		private static final long serialVersionUID = 1L;

		public FactoryException(final Exception e) {
			super(e);
		}
	}

	public static final ColumnMapperHelper getColumnMapperHelperForYear(
			final int jaar) throws FactoryException {
		final String className = ColumnMapperHelper.class.getName() + jaar;
		try {
			return (ColumnMapperHelper) Class.forName(className).newInstance();
		} catch (final Exception e) {
			throw new FactoryException(e);
		}
	}

}
