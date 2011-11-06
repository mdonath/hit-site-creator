package nl.scouting.hit.sitecreator;

import java.io.File;
import java.util.Map;

public abstract class ConfigKey<T> {

	public final static class FileConfigKey extends ConfigKey<File> {
		public FileConfigKey(final String name) {
			super(name);
		}

		@Override
		public File as(final String s) {
			return new File(s);
		}
	}

	public final static class StringConfigKey extends ConfigKey<String> {
		public StringConfigKey(final String name) {
			super(name);
		}

		@Override
		public String as(final String s) {
			return s;
		}
	}

	public final static class IntegerConfigKey extends ConfigKey<Integer> {
		public IntegerConfigKey(final String name) {
			super(name);
		}

		@Override
		public Integer as(final String s) {
			return Integer.valueOf(s);
		}
	}

	private final String name;

	public ConfigKey(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public T getValue(final Map<String, String> configuration) {
		final String s = configuration.get(name);
		if (s == null) {
			return null;
		}
		return as(s);
	}

	protected abstract T as(String s);
}
