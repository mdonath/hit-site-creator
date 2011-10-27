package nl.scouting.hit.sitecreator.output.module.html;

import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;

import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.output.OutputModule;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class HtmlOutputModule implements OutputModule {

	private File outDir;
	private String encoding;

	public void setOutDir(final File outDir) {
		this.outDir = outDir;
	}

	public void setEncoding(final String encoding) {
		this.encoding = encoding;
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		final String propertyName = evt.getPropertyName();
		if ("outDir".equals(propertyName)) {
			outDir = (File) evt.getNewValue();
		} else if ("encoding".equals(propertyName)) {
			encoding = (String) evt.getNewValue();
		}
	}

	@Override
	public void save(final Hit hit) throws IOException {
		final STGroup group = new STGroupFile(
				"nl/scouting/hit/sitecreator/output/module/html/kamponderdeel.stg",
				'$', '$');
		group.registerRenderer(Date.class, new DateRenderer());
		final ST st = group.getInstanceOf("hitkamp");
		for (final HitKamp kamp : hit.getKampenGesorteerd()) {
			st.add("hit", hit);
			st.add("kamp", kamp);

			writeToFile(kamp, st.render());
		}
	}

	private void writeToFile(final HitKamp kamp, final String result)
			throws IOException {
		final File file = new File(outDir, kamp.getHtmlFileNaam());
		final OutputStream os = new FileOutputStream(file);
		final Writer writer = new OutputStreamWriter(os, encoding);
		System.out.println("Writing '" + file + "' using " + encoding);
		writer.append(result);
		writer.close();
	}
}
