package nl.scouting.hit.sitecreator.input;

import javax.swing.JPanel;

public abstract class AbstractInputPanel extends JPanel implements
		InputModuleUI {
	private static final long serialVersionUID = 1L;

	protected AbstractInputPanel() {
		getProcessor();
	}
}
