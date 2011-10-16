package nl.scouting.hit.sitecreator.input.module.xml;

import nl.scouting.hit.sitecreator.input.module.AbstractFileImportInputModule;
import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.ModelUtil;

public class XmlInputModule extends AbstractFileImportInputModule {

	@Override
	public Hit load() {
		System.out.println("inlezen met " + getEncoding() + " van " + getFile()
				+ " van het jaar " + getJaar());
		return loadDummy();
	}

	private Hit loadDummy() {
		return ModelUtil.createTestStructure();
	}

}
