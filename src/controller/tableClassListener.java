package controller;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;
import view.panel.pnClass;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class tableClassListener implements MouseListener {
	private pnClass pnClassV;
	private JTable tableClassV;
	
	public tableClassListener(pnClass pnClassV, JTable tableClassV) {
		this.pnClassV = pnClassV;
		this.tableClassV = tableClassV;
	}

	public void mouseClicked(MouseEvent e) {
		int selectedRow = tableClassV.getSelectedRow();
        if (selectedRow != -1) {
        	String code = tableClassV.getValueAt(selectedRow, 0).toString();
        	String name = tableClassV.getValueAt(selectedRow, 1).toString();
        	String startDay = tableClassV.getValueAt(selectedRow, 2).toString();
        	String price = tableClassV.getValueAt(selectedRow, 3).toString();
        	String price0d = "";
        	int length = price.length();
            int posOfCurSymbol = price.indexOf("₫");
            if (posOfCurSymbol != -1 && posOfCurSymbol < length) {
            	price0d = price.substring(0, posOfCurSymbol - 1);
            }
        	pnClassV.tableDataToForm(code, name, startDay, price0d);
        }
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}