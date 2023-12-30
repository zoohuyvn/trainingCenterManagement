package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.panel.pnClass;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class pnClassListener implements ActionListener {
	private pnClass pnClassV;
	
	public pnClassListener(pnClass pnClassV) {
		this.pnClassV = pnClassV;
	}

	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case " Add":
			pnClassV.add();
			break;
		case " Update":
			pnClassV.update();
			break;
		case " Delete":
			pnClassV.delete();
			break;
		case " Clear form":
			pnClassV.clearForm();
			break;
		case " Reset data":
			pnClassV.resetData();
			break;
		case " Search":
			pnClassV.search();
			break;
		default:
			break;
		}
	}

}