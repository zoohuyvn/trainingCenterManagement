package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utils.exportToExcel;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class menuBarListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Export data")) {
			exportToExcel.export();
		} else if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
		}
	}

}