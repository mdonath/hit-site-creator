package nl.scouting.hit.sitecreator.model;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import nl.scouting.hit.sitecreator.util.IOUtil;

public class ImageUrl {

	private HitKamp kamp;
	private int volgnummer;
	private final URL url;

	public ImageUrl(final String url) throws MalformedURLException {
		this(new URL(url));
	}

	@Override
	public String toString() {
		if (url == null) {
			return "";
		}
		return url.toString();
	}

	public ImageUrl(final URL url) {
		this.url = url;
	}

	public File getOutputFile(final File outDir) {
		return new File(outDir, getOutputFileName());
	}

	public String getOutputFileName() {
		return String.format("img-%d-%d.%s", kamp.getDeelnemersnummer(),
				volgnummer, getExtension());
	}

	public File getScaledOutputFile(final File outDir) {
		return new File(outDir, getScaledOutputFileName());
	}

	public String getScaledOutputFileName() {
		return String.format("img-%d-%d-b.%s", kamp.getDeelnemersnummer(),
				volgnummer, getExtension());
	}

	public boolean isHeeftEenYoutubeFilmpje() {
		return url.getHost().toLowerCase().contains("youtube.com");
	}

	public boolean isDownloadable() {
		return (url != null) //
				&& !isHeeftEenYoutubeFilmpje() //
				&& (getExtension().equals("png") //
						|| getExtension().equals("jpg") //
				|| getExtension().equals("gif")) //
		;
	}

	public String getExtension() {
		final String urlString = url.toString().toLowerCase();
		final int extPos = urlString.lastIndexOf(".");
		String ext = urlString.substring(extPos + 1);
		if (ext.equals("jpeg")) {
			ext = "jpg";
		}
		return ext;
	}

	public URL getUrl() {
		return url;
	}

	public InputStream openStream() throws IOException {
		return url.openStream();
	}

	public void download(final File outDir) throws IOException {
		final InputStream is = url.openStream();
		final OutputStream os = new FileOutputStream(getOutputFile(outDir));
		IOUtil.copy(is, os);
		os.close();
	}

	public void scale(final File outDir, final int paramWidth,
			final int paramHeight) throws IOException {
		final File outFile = getOutputFile(outDir);

		final File scaledOutFile = getScaledOutputFile(outDir);

		final BufferedImage srcImg = ImageIO.read(outFile);
		final int currWidth = srcImg.getWidth();
		final int currHeight = srcImg.getHeight();
		final int destWidth = paramWidth;
		int destHeight = paramHeight;

		//
		// currWidth x currHeight
		//
		// destWidth x destHeight
		//

		if (paramHeight == -1) {
			destHeight = (paramWidth * currHeight) / currWidth;
		}
		if (paramWidth == -1) {
			destHeight = (destWidth * currHeight) / currWidth;
		}
		final BufferedImage destImg = new BufferedImage(destWidth, destHeight,
				BufferedImage.TYPE_INT_RGB);
		final Graphics2D g = destImg.createGraphics();
		final AffineTransform at = AffineTransform.getScaleInstance(
				(double) destWidth / currWidth, (double) destHeight
						/ currHeight);

		g.drawRenderedImage(srcImg, at);

		ImageIO.write(destImg, getExtension().toUpperCase(), scaledOutFile);
	}

	public HitKamp getKamp() {
		return kamp;
	}

	public void setKamp(final HitKamp kamp) {
		this.kamp = kamp;
	}

	public int getVolgnummer() {
		return volgnummer;
	}

	public void setVolgnummer(final int volgnummer) {
		this.volgnummer = volgnummer;
	}
}
