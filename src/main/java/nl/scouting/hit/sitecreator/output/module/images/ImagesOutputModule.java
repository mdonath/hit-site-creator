package nl.scouting.hit.sitecreator.output.module.images;

import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.output.module.AbstractProgressOutputModule;
import nl.scouting.hit.sitecreator.util.IOUtil;

public class ImagesOutputModule extends AbstractProgressOutputModule {
	private File outDir;

	public void setOutDir(final File outDir) {
		this.outDir = outDir;
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		final String propertyName = evt.getPropertyName();
		if ("outDir".equals(propertyName)) {
			outDir = (File) evt.getNewValue();
		}
	}

	@Override
	public void save(final Hit hit) throws IOException {
		final List<HitKamp> kampen = hit.getKampenGesorteerd();
		int counter = 0;
		for (final HitKamp kamp : kampen) {
			downloadImages(kamp);
			fireProgressListenerEvent(++counter, kampen.size());
		}
	}

	private void downloadImages(final HitKamp kamp) throws IOException {
		download(kamp.getDeelnemersnummer(), 1, kamp.getWebadresFoto1());
		download(kamp.getDeelnemersnummer(), 2, kamp.getWebadresFoto2());
		download(kamp.getDeelnemersnummer(), 3, kamp.getWebadresFoto3());
	}

	private void download(final Integer deelnemersnummer, final int nr,
			final URL url) throws IOException {
		if (isDownloadable(url)) {
			final File image = new File(outDir, "img-" + deelnemersnummer + "-"
					+ nr + getExtension(url));
			String msg;
			FileOutputStream os = null;
			try {
				final InputStream is = url.openStream();
				os = new FileOutputStream(image);
				IOUtil.copy(is, os);
				System.out.println(url + " - " + image);
			} catch (final Exception e) {
				msg = e.getClass() + ": " + e.getMessage();
				System.out.println(url + " - " + msg);
			} finally {
				if (os != null) {
					os.close();
				}
			}
		}
	}

	protected boolean isDownloadable(final URL url) {
		return (url != null) //
				&& !url.toString().contains("youtube.com") //
				&& !getExtension(url).equals(".flv") //
				&& !getExtension(url).equals(".nl") //
				&& !getExtension(url).contains("/") //
		;
	}

	protected String getExtension(final URL url) {
		final String urlString = url.toString().toLowerCase();
		final int extPos = urlString.lastIndexOf(".");
		String ext = urlString.substring(extPos);
		if (ext.equals(".jpeg")) {
			ext = ".jpg";
		}
		if (ext.length() > 10) {
			ext = ".jpg";
		}
		return ext;
	}
}
