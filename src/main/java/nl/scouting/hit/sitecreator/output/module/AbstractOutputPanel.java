package nl.scouting.hit.sitecreator.output.module;

import javax.swing.JPanel;

import nl.scouting.hit.sitecreator.output.OutputModuleUI;

public abstract class AbstractOutputPanel extends JPanel implements
		OutputModuleUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected AbstractOutputPanel() {
		getProcessor();
	}
}
