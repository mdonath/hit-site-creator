package nl.scouting.hit.sitecreator.output.module.images;

import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitProject;
import nl.scouting.hit.sitecreator.model.ImageUrl;
import nl.scouting.hit.sitecreator.output.module.AbstractProgressOutputModule;

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
	public void save(final HitProject hit) throws IOException {
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
			final ImageUrl image) throws IOException {
		if (isDownloadable(image)) {
			try {
				image.download(outDir);
				image.scale(outDir, 180, -1);
				System.out.println(image + " - " + image.getOutputFile(outDir));
			} catch (final Exception e) {
				final String msg = e.getClass() + ": " + e.getMessage();
				System.out.println(image + " - " + msg);
			}
		}
	}

	protected static boolean isDownloadable(final ImageUrl url) {
		return (url != null) && url.isDownloadable();
	}

}
