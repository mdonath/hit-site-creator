package nl.scouting.hit.sitecreator.model;

import static org.junit.Assert.assertEquals;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Test;

public class HitKampTest {

	@Test
	public void constructie_van_startDatumTijd_eerst_datum_dan_tijd()
			throws Exception {
		final HitKamp k = new HitKamp();
		k.setStartDatum(new LocalDate("2012-12-13"));
		k.setStartTijd(new LocalTime("13:37"));
		assertEquals("juiste tijd moet gevuld zijn", new LocalDateTime(
				"2012-12-13T13:37:00"), k.getStartDatumtijd());
	}

	@Test
	public void constructie_van_startDatumTijd_eerst_tijd_dan_datum()
			throws Exception {
		final HitKamp k = new HitKamp();
		k.setStartTijd(new LocalTime("13:37"));
		k.setStartDatum(new LocalDate("2012-12-13"));
		assertEquals("juist tijd moet gevuld zijn", new LocalDateTime(
				"2012-12-13T13:37:00"), k.getStartDatumtijd());
	}

	@Test
	public void constructie_van_eindDatumTijd_eerst_datum_dan_tijd()
			throws Exception {
		final HitKamp k = new HitKamp();
		k.setEindDatum(new LocalDate("2012-12-13"));
		k.setEindTijd(new LocalTime("13:37"));
		assertEquals("juist tijd moet gevuld zijn", new LocalDateTime(
				"2012-12-13T13:37:00"), k.getEindDatumtijd());
	}

	@Test
	public void constructie_van_eindDatumTijd_eerst_tijd_dan_datum()
			throws Exception {
		final HitKamp k = new HitKamp();
		k.setEindTijd(new LocalTime("13:37"));
		k.setEindDatum(new LocalDate("2012-12-13"));
		assertEquals("juist tijd moet gevuld zijn", new LocalDateTime(
				"2012-12-13T13:37:00"), k.getEindDatumtijd());
	}

	@Test
	public void bepaal_afgelegde_kilometers() {
		final HitKamp k = new HitKamp();
		k.setIcoontje("Totale afstand is 20 km");
		assertEquals(Integer.valueOf(20), k.getAfgelegdeKilometers());
	}
}
