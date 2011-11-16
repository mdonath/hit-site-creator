package nl.scouting.hit.sitecreator;

public interface Application<M> {

	<T> T getConfigurationValue(final ConfigKey<T> key);

	<T> boolean hasConfigurationValue(final ConfigKey<T> key);

	void setModel(M model);

	M getModel();

}
