package nl.scouting.hit.sitecreator.input.module.csv;

public class MappingException extends Exception {

	private static final long serialVersionUID = 2912168926558733269L;

	public MappingException(final Throwable parent) {
		super(parent);
	}

	public MappingException(final String string) {
		super(string);
	}
}
