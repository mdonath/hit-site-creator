package nl.scouting.hit.sitecreator;

public interface Application {

	<T> T getConfigurationValue(final ConfigKey<T> key);

	<T> boolean hasConfigurationValue(final ConfigKey<T> key);

}
