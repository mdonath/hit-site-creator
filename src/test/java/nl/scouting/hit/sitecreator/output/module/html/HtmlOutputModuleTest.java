package nl.scouting.hit.sitecreator.output.module.html;

import java.io.File;
import java.io.IOException;

import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitPlaats;
import nl.scouting.hit.sitecreator.util.IOUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.stringtemplate.v4.ST;

public class HtmlOutputModuleTest {

	private File tempDir;

	private static final Hit HIT = new Hit(
			2012, //
			new HitPlaats("Mook", new HitKamp("Stook"), new HitKamp("Water")),
			new HitPlaats("Alphen"));

	@Before
	public void setUp() throws IOException {
		tempDir = IOUtil.createTempDir();
	}

	@After
	public void cleanUp() {
		IOUtil.deleteDir(tempDir);
	}

	@Test
	public void save() throws Exception {
		final HtmlOutputModule module = new HtmlOutputModule();
		module.setEncoding("UTF-8");
		module.setOutDir(tempDir);

		final HitKamp stook = new HitKamp("Stook");
		stook.setDeelnemersnummer(111);
		final HitKamp water = new HitKamp("Water");
		water.setDeelnemersnummer(333);
		final HitKamp theater = new HitKamp("Theater");
		theater.setDeelnemersnummer(222);
		final HitPlaats mook = new HitPlaats("Mook", stook, water, theater);
		final HitPlaats alphen = new HitPlaats("Alphen");
		final Hit hit = new Hit(2012, alphen, mook);
		module.save(hit);
	}

	@Ignore
	@Test
	public void access_objects() throws Exception {
		final ST st = new ST(
				//
				"<h1>HIT $hit.jaar$</h1>" + //
						"<ul>$hit.hitPlaatsen:{plaats|<li>$plaats.naam$</li>}$</ul>",
				'$', '$');
		st.add("hit", HIT);
		final String result = st.render();
		System.out.println(result);
	}

}
