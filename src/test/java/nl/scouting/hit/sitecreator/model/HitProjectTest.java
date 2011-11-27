package nl.scouting.hit.sitecreator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

public class HitProjectTest {

	@Test(expected = MergeException.class)
			public void mergePlaatsenProject_twee_hits_verschillend_jaar() throws Exception {
				final HitProject hit1 = new HitProject(2012);
				final HitProject hit2 = new HitProject(2013);
				hit1.mergeProject(hit2);
			}

	@Test
			public void mergePlaatsenProject_twee_hits() throws Exception {
				final HitProject hit1 = new HitProject(2012, new HitPlaats("alphen"));
				final HitProject hit2 = new HitProject(2012, new HitPlaats("mook"));
				assertEquals(1, hit1.getHitPlaatsen().size());
				hit1.mergeProject(hit2);
				assertEquals(2, hit1.getHitPlaatsen().size());
			}

	@Test
			public void mergePlaatsenProject_dezelfde_plaats() throws Exception {
				final HitProject hit1 = new HitProject(2012, new HitPlaats("alphen"));
				final HitProject hit2 = new HitProject(2012, new HitPlaats("alphen"));
				assertEquals(1, hit1.getHitPlaatsen().size());
				hit1.mergeProject(hit2);
				assertEquals(1, hit1.getHitPlaatsen().size());
			}

	@Test
			public void mergePlaatsenProject_dezelfde_plaats_met_kampen() throws Exception {
				final HitProject hit1 = new HitProject(2012, new HitPlaats("alphen", new HitKamp(
						"een")));
				final HitProject hit2 = new HitProject(2012, new HitPlaats("alphen", new HitKamp(
						"twee")));
				hit1.mergeProject(hit2);
				assertEquals(1, hit1.getHitPlaatsen().size());
				assertEquals(2, hit1.getAantalKampen());
			}

	@Test
	public void simpele_constructor() throws Exception {
		assertEquals(Integer.valueOf(2012), new HitProject(2012).getJaar());
	}

	@Test
	public void aantal_kampen_is_nul() throws Exception {
		assertEquals(0, new HitProject(2012).getAantalKampen());
	}

	@Test
	public void aantal_kampen_is_twee() throws Exception {
		assertEquals(2,
				new HitProject(2012, //
						new HitPlaats("mook", //
								new HitKamp("stook"), new HitKamp("water")))
						.getAantalKampen());
	}

	@Test
	public void aantal_kampen_over_twee_plaatsen_is_twee() throws Exception {
		assertEquals(2, new HitProject(2012 //
				, new HitPlaats("mook", new HitKamp("stook")) //
				, new HitPlaats("zeeland", new HitKamp("water"))) //
				.getAantalKampen());
	}

	@Test
	public void linkKampenAanElkaar_met_twee_plaatsen_vier_kampen()
			throws Exception {
		final HitProject hit = new HitProject(2012);
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
		final HitProject hit = new HitProject(2012);
		final HitPlaats alphen = new HitPlaats("Alphen");
		final HitKamp een = new HitKamp("Een");
		alphen.addHitKamp(een);
		hit.addHitPlaats(alphen);

		hit.linkKampenAanElkaar();

		assertEquals("een.next=een", een, een.getNext());
	}

	@Test
	public void beschikbare_iconen() throws Exception {
		final HitProject hit = new HitProject(2012);
		final Set<Icoon> beschikbareIconen = hit.getBeschikbareIconen();
		assertNotNull(beschikbareIconen);
		assertEquals(44, beschikbareIconen.size());
	}

	@Test
	public void gebruikte_iconen_leeg() throws Exception {
		final HitProject hit = new HitProject(2012);
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

		final HitProject hit = new HitProject(2012, new HitPlaats("Mook", kamp1, kamp2));

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
