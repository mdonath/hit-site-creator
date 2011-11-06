package nl.scouting.hit.sitecreator.input.module.xml;

import javax.swing.filechooser.FileNameExtensionFilter;

import nl.scouting.hit.sitecreator.Application;
import nl.scouting.hit.sitecreator.input.InputModule;
import nl.scouting.hit.sitecreator.input.module.AbstractFileImportPanel;

public class XmlInputPanel extends AbstractFileImportPanel {
	private static final long serialVersionUID = 1L;

	public XmlInputPanel(final Application application) {
		super(application, "XML", new FileNameExtensionFilter(
				"Bestand met alle kampgegevens", "xml"));
	}

	@Override
	protected final InputModule createInputModule() {
		return new XmlInputModule();
	}

}