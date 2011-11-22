package nl.scouting.hit.sitecreator.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class IOUtil {

	public static final File createTempDir() throws IOException {
		final File temp = File.createTempFile("test", "");
		temp.delete();
		temp.mkdir();
		return temp;
	}

	public static final void deleteDir(final File dir) {
		for (final File f : dir.listFiles()) {
			f.delete();
		}
		dir.delete();
	}

	/**
	 * Copieert van <code>is</code> naar <code>os</code>.
	 * 
	 * @param is
	 *            Wordt geclosed.
	 * @param os
	 *            Wordt <em>NIET</em> geclosed, moet je zelf nog doen.
	 * @throws IOException
	 */
	public static final void copy(final InputStream is, final OutputStream os)
			throws IOException {
		final byte[] buf = new byte[4096];
		int read = -1;

		while ((read = is.read(buf)) != -1) {
			os.write(buf, 0, read);
		}
		is.close();
	}

	private IOUtil() {
		// private constructor.
	}
}
