package nl.scouting.hit.sitecreator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

public class HitTest {
	@Test
	public void testname() throws Exception {
		assertEquals(Integer.valueOf(2012), new Hit(2012).getJaar());
	}

	@Test
	public void linkKampenAanElkaar_met_twee_plaatsen_vier_kampen()
			throws Exception {
		final Hit hit = new Hit(2012);
		final HitPlaats alphen = new HitPlaats("Alphen");
		final HitKamp een = new HitKamp("Een");
		alphen.addHitKamp(een);
		final HitKamp twee = new HitKamp("Twee");
		alphen.addHitKamp(twee);
		hit.addHitPlaats(alphen);

		final HitPlaats mook = new HitPlaats("Mook");
		final HitKamp drie = new HitKamp("Drie");
		mook.addHitKamp(drie);
		final HitKamp vier = new HitKamp("Vier");
		mook.addHitKamp(vier);
		hit.addHitPlaats(mook);

		hit.linkKampenAanElkaar();

		assertEquals("een.next=twee", twee, een.getNext());
		assertEquals("twee.next=drie", drie, twee.getNext());
		assertEquals("drie.next=vier", vier, drie.getNext());
		assertEquals("vier.next=een", een, vier.getNext());
	}

	@Test
	public void linkKampenAanElkaar_met_een_plaats_een_kamp() throws Exception {
		final Hit hit = new Hit(2012);
		final HitPlaats alphen = new HitPlaats("Alphen");
		final HitKamp een = new HitKamp("Een");
		alphen.addHitKamp(een);
		hit.addHitPlaats(alphen);

		hit.linkKampenAanElkaar();

		assertEquals("een.next=een", een, een.getNext());
	}

	@Test
	public void beschikbare_iconen() throws Exception {
		final Hit hit = new Hit(2012);
		final Set<Icoon> beschikbareIconen = hit.getBeschikbareIconen();
		assertNotNull(beschikbareIconen);
		assertEquals(50, beschikbareIconen.size());
	}

	@Test
	public void gebruikte_iconen_leeg() throws Exception {
		final Hit hit = new Hit(2012);
		final Set<Icoon> gebruikteIconen = hit.getGebruikteIconen();
		assertNotNull(gebruikteIconen);
		assertEquals(0, gebruikteIconen.size());
	}

	@Test
	public void gebruikte_iconen() throws Exception {
		final HitKamp kamp1 = new HitKamp("kamp1");
		kamp1.setIcoontje("Staand kamp");
		kamp1.setIcoontje("Trekken met rugzak");
		final HitKamp kamp2 = new HitKamp("kamp2");
		kamp2.setIcoontje("Staand kamp");
		kamp2.setIcoontje("Totale afstand is 15 km");

		final Hit hit = new Hit(2012, new HitPlaats("Mook", kamp1, kamp2));

		final Set<Icoon> gebruikteIconen = hit.getGebruikteIconen();
		assertNotNull(gebruikteIconen);
		assertEquals(3, gebruikteIconen.size());
		assertTrue(gebruikteIconen.contains(Icoon.forIdentifier("Staand kamp")));
		assertTrue(gebruikteIconen.contains(Icoon
				.forIdentifier("Trekken met rugzak")));
		assertTrue(gebruikteIconen.contains(Icoon
				.forIdentifier("Totale afstand is 15 km")));
		System.out.println(gebruikteIconen);
	}

}
