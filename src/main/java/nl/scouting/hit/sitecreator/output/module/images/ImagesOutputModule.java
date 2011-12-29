package nl.scouting.hit.sitecreator.output.module.images;

import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import nl.scouting.hit.sitecreator.model.HitKamp;
import nl.scouting.hit.sitecreator.model.HitProject;
import nl.scouting.hit.sitecreator.model.ImageUrl;
import nl.scouting.hit.sitecreator.output.module.AbstractProgressOutputModule;

/**
 * Module die afbeeldingen kan downloaden en deze schaalt naar een absolute
 * horizontale afmeting. De verticale afmeting blijft proportioneel.
 * 
 * @author martijn
 */
public final class ImagesOutputModule extends AbstractProgressOutputModule {
	private File outDir;
	private Integer horizontalSize = Integer.valueOf(180);

	public void setOutDir(final File outDir) {
		this.outDir = outDir;
	}

	public void setHorizontalSize(final Integer horizontalSize) {
		this.horizontalSize = horizontalSize;
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		final String propertyName = evt.getPropertyName();
		if ("outDir".equals(propertyName)) {
			outDir = (File) evt.getNewValue();
		} else if ("horizontalSize".equals(propertyName)) {
			horizontalSize = (Integer) evt.getNewValue();
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

	private void downloadImages(final HitKamp kamp) {
		download(kamp.getWebadresFoto1());
		download(kamp.getWebadresFoto2());
		download(kamp.getWebadresFoto3());
	}

	private void download(final ImageUrl imageUrl) {
		if (isDownloadable(imageUrl)) {
			try {
				imageUrl.download(outDir);
				imageUrl.scale(outDir, horizontalSize, -1);
				System.out.println(imageUrl + " - "
						+ imageUrl.getOutputFile(outDir));
			} catch (final Exception e) {
				final String msg = e.getClass() + ": " + e.getMessage();
				System.out.println(imageUrl + " - " + msg);
			}
		}
	}

	protected static boolean isDownloadable(final ImageUrl imageUrl) {
		return (imageUrl != null) && imageUrl.isDownloadable();
	}

}
