package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.panel.pnTabAnalytic;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class pnAnalyticListener implements ActionListener {
	private pnTabAnalytic pnTabAnalyticV;
	
	public pnAnalyticListener(pnTabAnalytic pnTabAnalyticV) {
		this.pnTabAnalyticV = pnTabAnalyticV;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(" Export report")) {
			pnTabAnalyticV.export();
		} else if (e.getActionCommand().equals(" Refresh")) {
			pnTabAnalyticV.refreshData();
		}
	}

}