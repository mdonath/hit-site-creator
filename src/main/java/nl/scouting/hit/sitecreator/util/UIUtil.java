package nl.scouting.hit.sitecreator.util;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class UIUtil {
	public static final GroupLayout createGroupLayout(JPanel panel) {
		GroupLayout layout = new GroupLayout(panel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		panel.setLayout(layout);
		return layout;
	}

	public static final JTabbedPane createTab(JPanel... tabs) {
		JTabbedPane tabPane = new JTabbedPane();
		for (JPanel tab : tabs) {
			tabPane.add(tab);
		}
		return tabPane;
	}
}