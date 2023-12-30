package view.panel;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class pnTabManagement extends JPanel {
	// Init card panel
	CardLayout cardLayout;
	JPanel pnStudent, pnAdvisor, pnClass;
	
	public pnTabManagement() {
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		
		pnStudent = new pnStudent();
		pnAdvisor = new pnAdvisor();
		pnClass = new pnClass();
		
		// Add component
		this.add(pnStudent, "pnStudent");
		this.add(pnAdvisor, "pnAdvisor");
		this.add(pnClass, "pnClass");
	}
	
	public void showPnStudent() {
		cardLayout.show(this, "pnStudent");
	}
	
	public void showPnAdvisor() {
		cardLayout.show(this, "pnAdvisor");
	}
	
	public void showPnClass() {
		cardLayout.show(this, "pnClass");
	}
	
}