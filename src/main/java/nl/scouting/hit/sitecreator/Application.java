package nl.scouting.hit.sitecreator;

import java.awt.BorderLayout;
import java.awt.Container;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import nl.scouting.hit.sitecreator.input.InputPanel;
import nl.scouting.hit.sitecreator.output.OutputPanel;
import nl.scouting.hit.sitecreator.transform.TransformPanel;

public class Application extends JFrame {
	private static final long serialVersionUID = 1L;

	public Application() {
		super("HIT SiteCreator");
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Container content = getContentPane();
		content.setLayout(new BorderLayout());
		content.add(createInputPanel(), BorderLayout.NORTH);
		content.add(createTransformPanel(), BorderLayout.CENTER);
		content.add(createOutputPanel(), BorderLayout.SOUTH);

		pack();
		setLocationByPlatform(true);
	}

	private InputPanel createInputPanel() {
		InputPanel inputPanel = new InputPanel();
		inputPanel.addPropertyChangeListener("hit",
				new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						firePropertyChange("hit", evt.getOldValue(),
								evt.getNewValue());
					}
				});
		return inputPanel;
	}

	private TransformPanel createTransformPanel() {
		TransformPanel transformPanel = new TransformPanel();
		addPropertyChangeListener("hit", transformPanel);
		return transformPanel;
	}

	private JPanel createOutputPanel() {
		OutputPanel result = new OutputPanel();
		addPropertyChangeListener("hit", result);
		return result;
	}

}
