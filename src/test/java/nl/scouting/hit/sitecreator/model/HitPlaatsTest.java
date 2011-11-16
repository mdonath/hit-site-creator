package nl.scouting.hit.sitecreator.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HitPlaatsTest {

	@Test
	public void merge_plaatsgegevens() throws Exception {
		final HitPlaats alphenOud = new HitPlaats("alphen");
		final HitPlaats alphenNieuw = new HitPlaats("alphen");
		alphenNieuw.setDeelnemersnummer(123456);
		alphenNieuw.setHitCourantTekst("tekst");
		alphenOud.merge(alphenNieuw);
		assertEquals("alphen", alphenOud.getNaam());
		assertEquals(Integer.valueOf(123456), alphenOud.getDeelnemersnummer());
		assertEquals("tekst", alphenOud.getHitCourantTekst());
	}

	@Test
	public void merge_kampen() throws Exception {
		final HitPlaats alphenOud = new HitPlaats("alphen", new HitKamp("een"));
		final HitPlaats alphenNieuw = new HitPlaats("alphen", new HitKamp(
				"twee"));

		assertEquals(1, alphenOud.getAantalKampen());
		alphenOud.merge(alphenNieuw);
		assertEquals(2, alphenOud.getAantalKampen());
	}

	@Test(expected = MergeException.class)
	public void merge_plaatsen_met_verschillende_namen() throws Exception {
		final HitPlaats alphen = new HitPlaats("alphen");
		final HitPlaats mook = new HitPlaats("mook");
		alphen.merge(mook);
	}

}
