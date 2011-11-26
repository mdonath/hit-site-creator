package nl.scouting.hit.sitecreator.output.module;

import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import nl.scouting.hit.sitecreator.Application;
import nl.scouting.hit.sitecreator.output.OutputModuleUI;
import nl.scouting.hit.sitecreator.output.ProgressListener;

public abstract class AbstractProgressOutputPanel<A> extends JPanel implements
		OutputModuleUI, ProgressListener {
	private static final long serialVersionUID = 1L;

	private JProgressBar progress;
	private final Application<A> application;

	protected AbstractProgressOutputPanel(final Application<A> application) {
		this.application = application;
		initComponents();
		getProcessor();
	}

	private void initComponents() {
		progress = new JProgressBar(SwingConstants.HORIZONTAL);
		progress.setModel(new DefaultBoundedRangeModel(0, 0, 0, 100));
	}

	@Override
	public void progress(final ProgressListenerEvent event) {
		final BoundedRangeModel model = getProgress().getModel();
		model.setValue(event.getIndex());
		model.setMaximum(event.getTotal());
	}

	public Application<A> getApplication() {
		return application;
	}

	protected JProgressBar getProgress() {
		return progress;
	}

}
