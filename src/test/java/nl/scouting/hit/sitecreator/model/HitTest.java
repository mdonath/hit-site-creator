package nl.scouting.hit.sitecreator.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class HitTest {
	@Test
	public void testname() throws Exception {
		assertEquals(Integer.valueOf(2012), new Hit(2012).getJaar());
	}
}
