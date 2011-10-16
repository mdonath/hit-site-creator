package nl.scouting.hit.sitecreator.input.module.xml;

import javax.swing.filechooser.FileNameExtensionFilter;

import nl.scouting.hit.sitecreator.input.InputModule;
import nl.scouting.hit.sitecreator.input.module.AbstractFileImportPanel;

public class XmlInputPanel extends AbstractFileImportPanel {
	private static final long serialVersionUID = 1L;

	private XmlInputModule inputModule;

	public XmlInputPanel() {
		super("XML", new FileNameExtensionFilter(
				"Bestand met alle kampgegevens", "xml"));
	}

	@Override
	public InputModule getProcessor() {
		if (this.inputModule == null) {
			this.inputModule = new XmlInputModule();
			addPropertyChangeListener("file", this.inputModule);
			addPropertyChangeListener("jaar", this.inputModule);
		}
		return this.inputModule;
	}

}