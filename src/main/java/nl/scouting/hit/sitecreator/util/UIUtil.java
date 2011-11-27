package nl.scouting.hit.sitecreator.util;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class UIUtil {
	public static final GroupLayout createGroupLayout(final JPanel panel) {
		final GroupLayout layout = new GroupLayout(panel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		panel.setLayout(layout);
		return layout;
	}

	public static final JTabbedPane createTab(final JComponent... tabs) {
		final JTabbedPane tabPane = new JTabbedPane();
		for (final JComponent tab : tabs) {
			tabPane.add(tab);
		}
		return tabPane;
	}

	public static JComponent wrapShiftToTop(final JComponent c) {
		final JPanel result = new JPanel(new BorderLayout());
		result.add(c, BorderLayout.NORTH);
		result.setName(c.getName());
		return result;
	}

}