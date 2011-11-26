package nl.scouting.hit.sitecreator.input.module.soap;

import javax.swing.JPanel;

import nl.scouting.hit.sitecreator.input.InputModuleUI;

public abstract class AbstractInputPanel extends JPanel implements
		InputModuleUI {
	private static final long serialVersionUID = 1L;

	protected AbstractInputPanel() {
		getProcessor();
	}

}
