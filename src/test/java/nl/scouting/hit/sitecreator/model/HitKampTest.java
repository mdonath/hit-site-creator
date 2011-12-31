package nl.scouting.hit.sitecreator.model;

import static org.junit.Assert.assertEquals;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Test;

public class HitKampTest {

	@Test
	public void urlified_naam_degrees() throws Exception {
		assertEquals("eifel-50d-noord",
				new HitKamp("Eifel 50° Noord").getUrlifiedNaam());
	}

	@Test
	public void urlified_naam_oh() throws Exception {
		assertEquals("eifel-50o-noord",
				new HitKamp("Eifel 50º Noord").getUrlifiedNaam());
	}

	@Test
	public void urlified_naam_minus() throws Exception {
		assertEquals("aaa-bbb", new HitKamp("aaa-bbb").getUrlifiedNaam());
	}

	@Test
	public void urlified_naam_spatie_minus_spatie() throws Exception {
		assertEquals("aaa-bbb", new HitKamp("aaa - bbb").getUrlifiedNaam());
	}

	@Test
	public void urlified_naam_lowercase() throws Exception {
		assertEquals("abcdef", new HitKamp("ABCDEF").getUrlifiedNaam());
	}

	@Test
	public void urlified_naam_ampersand() throws Exception {
		assertEquals("3xaaa", new HitKamp("3xa&a").getUrlifiedNaam());
	}

	@Test
	public void urlified_naam_dubbele_minus_na_vervangen() throws Exception {
		assertEquals("a-b", new HitKamp("a - ( - ) - b").getUrlifiedNaam());
	}

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
