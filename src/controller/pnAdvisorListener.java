package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.panel.pnAdvisor;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class pnAdvisorListener implements ActionListener {
	private pnAdvisor pnAdvisorV;
	
	public pnAdvisorListener(pnAdvisor pnAdvisorV) {
		this.pnAdvisorV = pnAdvisorV;
	}

	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case " Add":
			pnAdvisorV.add();
			break;
		case " Update":
			pnAdvisorV.update();
			break;
		case " Delete":
			pnAdvisorV.delete();
			break;
		case " Clear form":
			pnAdvisorV.clearForm();
			break;
		case " Reset data":
			pnAdvisorV.resetData();
			break;
		case " Search":
			pnAdvisorV.search();
			break;
		default:
			break;
		}
	}

}