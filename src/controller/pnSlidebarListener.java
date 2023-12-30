package controller;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.tree.DefaultMutableTreeNode;
import view.panel.pnSlidebar;
import view.panel.pnTabManagement;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class pnSlidebarListener implements MouseListener {
	private pnSlidebar pnSlidebarV;
	private pnTabManagement pnTabManagementV;
	public static String nodeStr = "";

	public pnSlidebarListener(pnSlidebar pnSlidebarV, pnTabManagement pnTabManagementV) {
		this.pnSlidebarV = pnSlidebarV;
		this.pnTabManagementV = pnTabManagementV;
	}
	
	public void mouseClicked(MouseEvent e) {
	    Object obj = pnSlidebarV.tree.getLastSelectedPathComponent();
	    DefaultMutableTreeNode node = (DefaultMutableTreeNode) obj;
	    nodeStr = node != null ? node.toString() : "";
	    switch (nodeStr) {
			case " Students":
				pnTabManagementV.showPnStudent();
				break;
			case " Advisors":
				pnTabManagementV.showPnAdvisor();
				break;
			case " Class":
				pnTabManagementV.showPnClass();
				break;
			default:
				pnTabManagementV.showPnStudent();
				break;
		}
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}