package nl.scouting.hit.sitecreator.input.module;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import nl.scouting.hit.sitecreator.input.module.csv.ColumnMapperHelper;
import nl.scouting.hit.sitecreator.input.module.csv.ColumnMapperHelperFactory;
import nl.scouting.hit.sitecreator.input.module.csv.ColumnMapperHelperFactory.FactoryException;
import nl.scouting.hit.sitecreator.input.module.csv.MappingException;
import nl.scouting.hit.sitecreator.model.Hit;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import au.com.bytecode.opencsv.bean.MappingStrategy;

public abstract class AbstractCsvFileImportInputModule<T> extends
		AbstractFileImportInputModule {

	private final Class<T> clazz;

	protected AbstractCsvFileImportInputModule(final Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public final Hit load() throws InputModuleException {
		return loadCsv();
	}

	private Hit loadCsv() throws InputModuleException {
		try {
			final CSVReader reader = new CSVReader( //
					new InputStreamReader(getInputStream(), getEncoding()));

			final CsvToBean<T> csv = new CsvToBean<T>();
			final List<T> list = csv.parse(getStrategy(), reader);

			return maakStructuur(list);
		} catch (final IOException e) {
			throw new InputModuleException(e);
		} catch (final FactoryException e) {
			throw new InputModuleException(e);
		} catch (final MappingException e) {
			throw new InputModuleException(e);
		}
	}

	protected InputStream getInputStream() throws IOException {
		return new FileInputStream(getFile());
	}

	private MappingStrategy<T> getStrategy() throws FactoryException,
			MappingException {
		final ColumnMapperHelper helper = ColumnMapperHelperFactory
				.getColumnMapperHelperForYear(getJaar(), clazz);

		final HeaderColumnNameTranslateMappingStrategy<T> strat = new HeaderColumnNameTranslateMappingStrategy<T>();
		strat.setType(clazz);
		strat.setColumnMapping(helper.getColumnMapping());
		return strat;
	}

	protected abstract Hit maakStructuur(final List<T> list);
}
