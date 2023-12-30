package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.panel.pnStudent;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class pnStudentListener implements ActionListener {
	private pnStudent pnStudentV;
	
	public pnStudentListener(pnStudent pnStudentV) {
		this.pnStudentV = pnStudentV;
	}

	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case " Add":
			pnStudentV.add();
			break;
		case " Update":
			pnStudentV.update();
			break;
		case " Delete":
			pnStudentV.delete();
			break;
		case " Clear form":
			pnStudentV.clearForm();
			break;
		case " Reset data":
			pnStudentV.resetData();
			break;
		case " Search":
			pnStudentV.search();
			break;
		default:
			break;
		}
	}

}