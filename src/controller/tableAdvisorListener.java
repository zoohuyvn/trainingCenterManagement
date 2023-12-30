package controller;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;
import view.panel.pnAdvisor;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class tableAdvisorListener implements MouseListener {
	private pnAdvisor pnAdvisorV;
	private JTable tableAdvisorV;
	public static String email = "";
	public static String className = "";
	
	public tableAdvisorListener(pnAdvisor pnAdvisorV, JTable tableAdvisorV) {
		this.pnAdvisorV = pnAdvisorV;
		this.tableAdvisorV = tableAdvisorV;
	}

	public void mouseClicked(MouseEvent e) {
		int selectedRow = tableAdvisorV.getSelectedRow();
        if (selectedRow != -1) {
        	String code = tableAdvisorV.getValueAt(selectedRow, 0).toString();
        	String indentity = tableAdvisorV.getValueAt(selectedRow, 1).toString();
        	String name = tableAdvisorV.getValueAt(selectedRow, 2).toString();
        	int gender = tableAdvisorV.getValueAt(selectedRow, 3).toString() == "Nam" ? 1 : 0;
        	String birthday = tableAdvisorV.getValueAt(selectedRow, 4).toString();
        	String phone = tableAdvisorV.getValueAt(selectedRow, 5).toString();
        	email = tableAdvisorV.getValueAt(selectedRow, 6).toString();
        	String address = tableAdvisorV.getValueAt(selectedRow, 7).toString();
        	className = tableAdvisorV.getValueAt(selectedRow, 8).toString();
        	pnAdvisorV.tableDataToForm(code, indentity, name, gender, birthday, phone, email, address, className);
        }
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}