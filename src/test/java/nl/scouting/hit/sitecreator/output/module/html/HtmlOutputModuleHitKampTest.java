package nl.scouting.hit.sitecreator.output.module.html;

import java.util.Date;

import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitPlaats;

import org.junit.Test;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class HtmlOutputModuleHitKampTest {

	private static final HitKamp STOOK = new HitKamp("Stook");
	private static final Hit HIT = createHit();

	private static Hit createHit() {
		final HitKamp water = new HitKamp("Water");
		final HitKamp theater = new HitKamp("Theater");
		STOOK.setDeelnemersnummer(111111);
		water.setDeelnemersnummer(222222);
		theater.setDeelnemersnummer(333333);

		final Hit hit = new Hit(2012, //
				new HitPlaats("Mook", STOOK, water, theater), new HitPlaats(
						"Alphen"));
		hit.linkKampenAanElkaar();
		return hit;
	}

	@Test
	public void explore_groupDir() throws Exception {
		final STGroup group = new STGroupFile(
				"nl/scouting/hit/sitecreator/output/module/html/kamponderdeel.stg",
				'$', '$');
		group.registerRenderer(Date.class, new DateRenderer());
		final ST st = group.getInstanceOf("hitkamp");
		st.add("hit", HIT);
		st.add("kamp", STOOK);

		final String result = st.render();
		System.out.println(result);
	}

}
