package nl.scouting.hit.sitecreator.model;

import static org.junit.Assert.assertEquals;

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

}
