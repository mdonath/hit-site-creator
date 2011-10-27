package nl.scouting.hit.sitecreator.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

	/**
	 * dd-MM-yyyy.
	 * @param ddMMyyyy
	 * @return
	 */
	public static Date asDate(final String ddMMyyyy) {
		try {
			return new SimpleDateFormat("dd-MM-yyyy").parse(ddMMyyyy);
		} catch (final ParseException e) {
			throw new RuntimeException(e);
		}
	}

	private DateUtil() {
		// private constructor
	}
}
