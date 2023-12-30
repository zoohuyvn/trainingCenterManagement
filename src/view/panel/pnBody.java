package view.panel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class pnBody extends JPanel {
	// Init tab
	JTabbedPane tab;
	public static JPanel pnTabManagement = new pnTabManagement();
	JPanel pnTabAnalytic;
	
	public pnBody() {
		this.setLayout(new BorderLayout());
		
		// tab
		tab = new JTabbedPane();
		pnTabAnalytic = new pnTabAnalytic();
		tab.add(pnTabManagement, " Management ");
		tab.setIconAt(0, new ImageIcon(pnBody.class.getResource("/assets/icons/spreadsheet.png")));
		tab.add(pnTabAnalytic, " Analytic ");
		tab.setIconAt(1, new ImageIcon(pnBody.class.getResource("/assets/icons/column-chart.png")));
		tab.setFocusable(false);
		
		// Add component
		this.add(tab, BorderLayout.CENTER);
	}
}