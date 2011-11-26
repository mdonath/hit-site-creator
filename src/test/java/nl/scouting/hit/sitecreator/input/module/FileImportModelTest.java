package nl.scouting.hit.sitecreator.input.module;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import nl.scouting.hit.sitecreator.input.module.FileImportModel.FileImportModelListener;

import org.junit.Test;

public class FileImportModelTest {

	final File oldFile = new File("mooh");
	final File newFile = new File("baah");
	final String oldEncoding = "UTF-8";
	final String newEncoding = "ISO-8859-1";

	final FileImportModel model = new FileImportModel(oldFile, oldEncoding);
	final List<Integer> flag = new ArrayList<Integer>(1);

	@Test
	public void wijzigen_van_file_doet_triggeren() throws Exception {
		model.addModelListener(new FileImportModelListener() {
			@Override
			public void modelChanged(final PropertyChangeEvent event) {
				assertNotNull(event);
				assertEquals("file", event.getPropertyName());
				assertEquals(oldFile, event.getOldValue());
				assertEquals(newFile, event.getNewValue());
				flag.add(1);
			}
		});
		assertTrue(flag.isEmpty());
		model.setFile(newFile);
		assertFalse(flag.isEmpty());
	}

	@Test
	public void wijzigen_van_encoding_doet_triggeren() throws Exception {
		model.addModelListener(new FileImportModelListener() {
			@Override
			public void modelChanged(final PropertyChangeEvent event) {
				assertNotNull(event);
				assertEquals("encoding", event.getPropertyName());
				assertEquals(oldEncoding, event.getOldValue());
				assertEquals(newEncoding, event.getNewValue());
				flag.add(1);
			}
		});
		assertTrue(flag.isEmpty());
		model.setEncoding(newEncoding);
		assertFalse(flag.isEmpty());
	}

	@Test
	public void removen_van_listener_doet_niet_meer_triggeren()
			throws Exception {
		final FileImportModelListener l = new FileImportModelListener() {
			@Override
			public void modelChanged(final PropertyChangeEvent event) {
				flag.add(1);
			}
		};
		model.addModelListener(l);
		assertTrue(flag.isEmpty());
		model.setFile(newFile);
		assertFalse(flag.isEmpty());
		flag.clear();
		model.removeModelListener(l);
		model.setFile(oldFile);
		assertTrue(flag.isEmpty());

	}
}
