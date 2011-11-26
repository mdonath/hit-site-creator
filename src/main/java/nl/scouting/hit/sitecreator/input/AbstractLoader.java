package nl.scouting.hit.sitecreator.input;

import javax.swing.SwingWorker;

import nl.scouting.hit.sitecreator.input.module.InputModule;
import nl.scouting.hit.sitecreator.model.Hit;
import nl.scouting.hit.sitecreator.model.ModelUtil;

public abstract class AbstractLoader extends SwingWorker<Hit, Void> {
	private final InputModule inputModule;

	public AbstractLoader(final InputModule inputModule) {
		this.inputModule = inputModule;
	}

	@Override
	protected final Hit doInBackground() throws Exception {
		return inputModule.load();
	}

	@Override
	protected final void done() {
		Hit hit;
		try {
			hit = get();
		} catch (final Exception ignore) {
			ignore.printStackTrace();
			hit = ModelUtil.createEmptyStructure();
		}

		loadFinished(hit);
	}

	protected abstract void loadFinished(Hit hit);

}