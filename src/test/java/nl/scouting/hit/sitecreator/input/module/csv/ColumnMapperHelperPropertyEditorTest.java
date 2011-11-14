package nl.scouting.hit.sitecreator.input.module.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import nl.scouting.hit.sitecreator.input.module.csv.ColumnMapperHelper.LocalTimePropertyEditor;

import org.joda.time.LocalTime;
import org.junit.Test;

public class ColumnMapperHelperPropertyEditorTest {

	@Test
	public void localTimePropertyEditor_setAsText_ok() throws Exception {
		final LocalTimePropertyEditor editor = new ColumnMapperHelper.LocalTimePropertyEditor();
		editor.setAsText("13:37");
		final LocalTime time = (LocalTime) editor.getValue();
		assertEquals("juiste uur", 13, time.getHourOfDay());
		assertEquals("juiste minuten", 37, time.getMinuteOfHour());
	}

	@Test
	public void localTimePropertyEditor_setAsText_empty() throws Exception {
		final LocalTimePropertyEditor editor = new ColumnMapperHelper.LocalTimePropertyEditor();
		editor.setAsText("");
		assertNull("moet null zijn", editor.getValue());
	}

	@Test
	public void localTimePropertyEditor_setAsText_null() throws Exception {
		final LocalTimePropertyEditor editor = new ColumnMapperHelper.LocalTimePropertyEditor();
		editor.setAsText(null);
		assertNull("moet null zijn", editor.getValue());
	}

	@Test
	public void localTimePropertyEditor_getAsText() throws Exception {
		final LocalTimePropertyEditor editor = new ColumnMapperHelper.LocalTimePropertyEditor();
		editor.setValue(new LocalTime("13:37"));
		assertEquals("13:37", editor.getAsText());
	}

}
