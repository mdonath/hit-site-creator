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

import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitProject;
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
	public void save(final HitProject hit) throws IOException {
		genereerJson(hit);
		genereerHitCourant(hit);
		genereerKiesActiviteit(hit);
		genereerKampPaginas(hit);
	}

	private void genereerJson(final HitProject hit) throws IOException {
		genereerEnkel(hit, "json.stg", "json", "hit-data-json.js");
	}

	// TODO: hit2011_menu.html

	private void genereerHitCourant(final HitProject hit) throws IOException {
		genereerEnkel(hit, "hitcourant.stg", "hitcourant", "hitcourant.html");
	}

	private void genereerKiesActiviteit(final HitProject hit)
			throws IOException {
		genereerEnkel(hit, "kieseenactiviteit.stg", "hitkieskamp",
				"hitkieskamp.html");
	}

	private void genereerKampPaginas(final HitProject hit) throws IOException {
		final String groupFile = "kamponderdeel.stg";
		final String templateName = "hitkamp";
		final List<HitKamp> kampen = hit.getKampenGesorteerd();

		genereerPerKamp(hit, groupFile, templateName, kampen);
	}

	protected void genereerPerKamp(final HitProject hit,
			final String groupFile, final String templateName,
			final List<HitKamp> kampen) throws IOException {
		final STGroup kampTemplate = getTemplate(groupFile);
		int counter = 0;
		for (final HitKamp kamp : kampen) {
			final ST template = kampTemplate.getInstanceOf(templateName);
			template.add("hit", hit);
			template.add("kamp", kamp);

			final String outputFile = kamp.getHtmlFileNaam();
			writeToFile(outputFile, template);
			fireProgressListenerEvent(++counter, kampen.size());
		}
	}

	protected void genereerEnkel(final HitProject hit, final String groupFile,
			final String templateName, final String outputFile)
			throws FileNotFoundException, UnsupportedEncodingException,
			IOException {
		final STGroup group = getTemplate(groupFile);
		final ST template = group.getInstanceOf(templateName);
		template.add("hit", hit);
		writeToFile(outputFile, template);
	}

	private static STGroup getTemplate(final String file) {
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

	private void writeToFile(final String outputFile, final ST template)
			throws FileNotFoundException, UnsupportedEncodingException,
			IOException {
		final File file = new File(outDir, outputFile);
		final OutputStream os = new FileOutputStream(file);
		final Writer writer = new OutputStreamWriter(os, encoding);
		writer.append(template.render());
		writer.close();
	}

}
