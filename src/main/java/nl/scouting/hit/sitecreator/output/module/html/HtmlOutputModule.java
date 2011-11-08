package nl.scouting.hit.sitecreator.output.module.html;

import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Date;

import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.output.OutputModule;

import org.joda.time.LocalDateTime;
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
		genereerKampPaginas(hit);
		genereerKiesActiviteit(hit);
	}

	private void genereerKiesActiviteit(final Hit hit)
			throws FileNotFoundException, UnsupportedEncodingException,
			IOException {
		// Kies een activiteit
		final STGroup kiesTemplate = getTemplate("kieseenactiviteit.stg");
		final ST st = kiesTemplate.getInstanceOf("hitkieskamp");
		st.add("hit", hit);
		writeToFile(new File(outDir, "hitkieskamp.html"), st.render());
	}

	private void genereerKampPaginas(final Hit hit) throws IOException {
		final STGroup kampTemplate = getTemplate("kamponderdeel.stg");
		for (final HitKamp kamp : hit.getKampenGesorteerd()) {
			final ST st = kampTemplate.getInstanceOf("hitkamp");
			st.add("hit", hit);
			st.add("kamp", kamp);

			writeToFile(kamp, st.render());
		}
	}

	private STGroup getTemplate(final String string) {
		final STGroup kampTemplate = new STGroupFile(
				"nl/scouting/hit/sitecreator/output/module/html/" + string,
				'$', '$');
		kampTemplate.registerRenderer(Date.class, new DateRenderer());
		kampTemplate.registerRenderer(LocalDateTime.class,
				new LocalDateTimeRenderer());
		return kampTemplate;
	}

	private void writeToFile(final HitKamp kamp, final String result)
			throws IOException {
		writeToFile(new File(outDir, kamp.getHtmlFileNaam()), result);
	}

	private void writeToFile(final File file, final String result)
			throws FileNotFoundException, UnsupportedEncodingException,
			IOException {
		final OutputStream os = new FileOutputStream(file);
		final Writer writer = new OutputStreamWriter(os, encoding);
		System.out.println("Writing '" + file + "' using " + encoding);
		writer.append(result);
		writer.close();
	}

}
