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
import java.util.List;

import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.output.module.AbstractProgressOutputModule;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import org.stringtemplate.v4.StringRenderer;

public class HtmlOutputModule extends AbstractProgressOutputModule {

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
		genereerHitCourant(hit);
	}

	// TODO: hit2011_menu.html

	private void genereerHitCourant(final Hit hit)
			throws FileNotFoundException, UnsupportedEncodingException,
			IOException {
		final STGroup group = getTemplate("hitcourant.stg");
		final ST template = group.getInstanceOf("hitcourant");
		template.add("hit", hit);
		writeToFile(new File(outDir, "hitcourant.html"), template.render());
	}

	private void genereerKiesActiviteit(final Hit hit)
			throws FileNotFoundException, UnsupportedEncodingException,
			IOException {
		final STGroup group = getTemplate("kieseenactiviteit.stg");
		final ST template = group.getInstanceOf("hitkieskamp");
		template.add("hit", hit);
		writeToFile(new File(outDir, "hitkieskamp.html"), template.render());
	}

	private void genereerKampPaginas(final Hit hit) throws IOException {
		final STGroup kampTemplate = getTemplate("kamponderdeel.stg");
		final List<HitKamp> kampen = hit.getKampenGesorteerd();
		int counter = 0;
		for (final HitKamp kamp : kampen) {
			final ST st = kampTemplate.getInstanceOf("hitkamp");
			st.add("hit", hit);
			st.add("kamp", kamp);

			writeToFile(kamp, st.render());
			fireProgressListenerEvent(++counter, kampen.size());
		}
	}

	private STGroup getTemplate(final String file) {
		final STGroup group;
		if (new File(file).exists()) {
			group = new STGroupFile(file, '$', '$');
		} else {
			group = new STGroupFile(
					"nl/scouting/hit/sitecreator/output/module/html/" + file,
					'$', '$');
		}
		group.registerRenderer(Date.class, new DateRenderer());
		group.registerRenderer(LocalDateTime.class, new LocalDateTimeRenderer());
		group.registerRenderer(LocalDate.class, new LocalDateRenderer());
		group.registerRenderer(String.class, new StringRenderer());
		return group;
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
