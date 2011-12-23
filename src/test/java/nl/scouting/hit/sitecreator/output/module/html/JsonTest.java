package nl.scouting.hit.sitecreator.output.module.html;

import java.util.Date;

import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitPlaats;
import nl.scouting.hit.sitecreator.model.HitProject;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class JsonTest {

	@Test
	public void explore_groupDir() throws Exception {
		final HitPlaats alphen = new HitPlaats("Alphen", new HitKamp("ATB"),
				new HitKamp("Dinges"));
		final HitKamp stook = new HitKamp("Stook");
		stook.setDeelnamekosten(45);
		stook.setMinimumLeeftijd(10);
		stook.setMaximumLeeftijd(15);
		stook.setIcoontje("Staand kamp");

		final HitKamp water = new HitKamp("Water");
		water.setDeelnamekosten(60);
		water.setMinimumLeeftijd(16);
		water.setMaximumLeeftijd(32);
		water.setIcoontje("Staand kamp");

		final HitPlaats mook = new HitPlaats("Mook", stook, water);
		final HitProject hit = new HitProject(2012, alphen, mook);
		hit.setInschrijvingStartdatum(new LocalDate(2012, 01, 01));
		hit.setInschrijvingEinddatum(new LocalDate(2012, 02, 02));
		hit.setInschrijvingWijzigenTotDatum(new LocalDate(2012, 03, 03));
		hit.setInschrijvingKosteloosAnnulerenDatum(new LocalDate(2012, 04, 04));
		hit.setInschrijvingGeenRestitutieDatum(new LocalDate(2012, 05, 05));
		hit.setInningsdatum(new LocalDate(2012, 06, 06));

		final STGroup group = new STGroupFile(
				"nl/scouting/hit/sitecreator/output/module/html/json.stg", '$',
				'$');
		group.registerRenderer(Date.class, new DateRenderer());
		final ST st = group.getInstanceOf("json");
		st.add("hit", hit);

		final String result = st.render();
		System.out.println(result);
	}

}
