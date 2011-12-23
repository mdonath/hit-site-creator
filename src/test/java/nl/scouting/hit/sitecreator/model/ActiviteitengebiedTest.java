package nl.scouting.hit.sitecreator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ActiviteitengebiedTest {

	@Test
	public void aantal_gebieden_is_8() throws Exception {
		assertEquals(8, Activiteitengebied.getAll().size());
	}

	@Test
	public void samenleving_is_vindbaar() throws Exception {
		assertNotNull(Activiteitengebied.forIdentifier("Samenleving"));
	}
}
