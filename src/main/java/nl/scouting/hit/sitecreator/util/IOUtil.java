package nl.scouting.hit.sitecreator.util;

import java.io.File;
import java.io.IOException;

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

	private IOUtil() {
		// private constructor.
	}
}
