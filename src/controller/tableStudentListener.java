package controller;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import view.panel.pnStudent;

public class tableStudentListener implements MouseListener {
	private pnStudent pnStudentV;
	private JTable tableStudentV;
	public static String email = "";
	public static String className = "";
	
	public tableStudentListener(pnStudent pnStudentV, JTable tableStudentV) {
		this.pnStudentV = pnStudentV;
		this.tableStudentV = tableStudentV;
	}

	public void mouseClicked(MouseEvent e) {
		int selectedRow = tableStudentV.getSelectedRow();
        if (selectedRow != -1) {
        	String code = tableStudentV.getValueAt(selectedRow, 0).toString();
        	String indentity = tableStudentV.getValueAt(selectedRow, 1).toString();
        	String name = tableStudentV.getValueAt(selectedRow, 2).toString();
        	int gender = tableStudentV.getValueAt(selectedRow, 3).toString() == "Nam" ? 1 : 0;
        	String birthday = tableStudentV.getValueAt(selectedRow, 4).toString();
        	String phone = tableStudentV.getValueAt(selectedRow, 5).toString();
        	email = tableStudentV.getValueAt(selectedRow, 6).toString();
        	String address = tableStudentV.getValueAt(selectedRow, 7).toString();
        	String job = tableStudentV.getValueAt(selectedRow, 8).toString();
        	className = tableStudentV.getValueAt(selectedRow, 9).toString();
        	String point = tableStudentV.getValueAt(selectedRow, 10).toString();
        	pnStudentV.tableDataToForm(code, indentity, name, gender, birthday, phone, email, address, job, className, point);
        }
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}