package nl.scouting.hit.sitecreator.util;

import org.joda.time.LocalDate;

public final class DateUtil {
	/**
	 * Get easter date for a year - default implementation.<br>
	 * Implements Meeus/Jones/Butcher Gregorian algorithm<br>
	 * From: <http://en.wikipedia.org/wiki/Computus><br>
	 * 
	 * @param year
	 * @return
	 */
	@SuppressWarnings("unused")
	public static LocalDate getEaster(final int year) {
		final int a = year % 19;
		final int b = year / 100;
		final int c = year % 100;
		final int d = b / 4;
		final int e = b % 4;
		final int f = (b + 8) / 25;
		final int g = ((b - f) + 1) / 3;
		final int h = ((((19 * a) + b) - d - g) + 15) % 30;
		final int i = c / 4;
		final int k = c % 4;
		final int L = ((32 + (2 * e) + (2 * i)) - h - k) % 7;
		final int m = (a + (11 * h) + (22 * L)) / 451;

		final int month = (((h + L) - (7 * m)) + 114) / 31;
		final int day = ((((h + L) - (7 * m)) + 114) % 31) + 1;

		return new LocalDate(year, month, day);
	}
}
