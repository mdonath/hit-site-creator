package nl.scouting.hit.sitecreator.input.module.xml;

import javax.swing.filechooser.FileNameExtensionFilter;

import nl.scouting.hit.sitecreator.input.InputModule;
import nl.scouting.hit.sitecreator.input.module.AbstractFileImportPanel;

public class XmlInputPanel extends AbstractFileImportPanel {
	private static final long serialVersionUID = 1L;

	public XmlInputPanel() {
		super("XML", new FileNameExtensionFilter(
				"Bestand met alle kampgegevens", "xml"));
	}

	private XmlInputModule inputModule;

	public InputModule getProcessor() {
		if (inputModule == null) {
			inputModule = new XmlInputModule();
			addPropertyChangeListener("file", inputModule);
			addPropertyChangeListener("jaar", inputModule);
 		}
		return inputModule;
	}

}