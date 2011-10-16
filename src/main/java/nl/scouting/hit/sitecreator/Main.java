package nl.scouting.hit.sitecreator;

import javax.swing.SwingUtilities;

/**
 * Start de applicatie op.
 * 
 * @author Martijn Donath
 */
public class Main {

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public final void run() {
				new Application().setVisible(true);
			}
		});
	}
}
