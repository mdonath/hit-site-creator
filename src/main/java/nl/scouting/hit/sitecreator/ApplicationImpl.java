package nl.scouting.hit.sitecreator;

import java.awt.BorderLayout;
import java.awt.Container;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import nl.scouting.hit.sitecreator.input.UberInputPanel;
import nl.scouting.hit.sitecreator.model.HitProject;
import nl.scouting.hit.sitecreator.output.OutputPanel;
import nl.scouting.hit.sitecreator.transform.TransformPanel;
import nl.scouting.hit.sitecreator.util.UIUtil;

/**
 * Het hoofdscherm van de applicatie.
 * 
 * @author Martijn Donath
 */
public final class ApplicationImpl extends JFrame implements
		Application<HitProject> {
	private static final long serialVersionUID = 1L;

	private final Map<String, String> configuration;

	private HitProject hit;

	public ApplicationImpl(final String[] arguments) {
		super(ApplicationLabels.getLabel("app.name"));
		configuration = new Parameters(arguments).getConfiguration();
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		final Container content = getContentPane();
		content.setLayout(new BorderLayout());
		content.add(createInputOutputTab(), BorderLayout.NORTH);
		content.add(createTransformPanel(), BorderLayout.CENTER);

		setLookAndFeel();
		pack();

		setLocationByPlatform(true);
	}

	private JComponent createInputOutputTab() {
		return UIUtil.createTab( //
				UIUtil.wrapShiftToTop(createInputPanel()), //
				UIUtil.wrapShiftToTop(createOutputPanel()) //
				);
	}

	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (final Exception ignored) {
			// ignore
		}
		SwingUtilities.updateComponentTreeUI(this);
	}

	private JPanel createInputPanel() {
		final UberInputPanel inputPanel = new UberInputPanel(this);
		inputPanel.setName(ApplicationLabels.getLabel("panel.input"));
		inputPanel.addPropertyChangeListener("hit",
				new PropertyChangeListener() {
					@Override
					public void propertyChange(final PropertyChangeEvent evt) {
						firePropertyChange("hit", evt.getOldValue(),
								evt.getNewValue());
					}
				});
		return inputPanel;
	}

	private TransformPanel createTransformPanel() {
		final TransformPanel transformPanel = new TransformPanel(this);
		addPropertyChangeListener("hit", transformPanel);
		return transformPanel;
	}

	private JPanel createOutputPanel() {
		final OutputPanel result = new OutputPanel(this);
		result.setName(ApplicationLabels.getLabel("panel.output"));

		addPropertyChangeListener("hit", result);
		return result;
	}

	@Override
	public <T> T getConfigurationValue(final ConfigKey<T> key) {
		return key.getValue(configuration);
	}

	@Override
	public <T> boolean hasConfigurationValue(final ConfigKey<T> key) {
		return getConfigurationValue(key) != null;
	}

	@Override
	public void setModel(final HitProject model) {
		hit = model;

	}

	@Override
	public HitProject getModel() {
		return hit;
	}
}
