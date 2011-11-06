package nl.scouting.hit.sitecreator;

import javax.swing.SwingUtilities;

/**
 * Start de applicatie op.
 * 
 * @author Martijn Donath
 */
public class Main {

	public static void main(final String[] arguments) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public final void run() {
				new ApplicationImpl(arguments).setVisible(true);
			}
		});
	}

}
