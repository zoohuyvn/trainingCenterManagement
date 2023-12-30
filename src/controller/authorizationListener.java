package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.authorizationView;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class authorizationListener implements ActionListener {
	private authorizationView authorizationViewV;
	
	public authorizationListener(authorizationView authorizationViewV) {
		this.authorizationViewV = authorizationViewV;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Register as student.")) {
			authorizationViewV.showPnRegister();
		} else if (e.getActionCommand().equals("Login now.")) {
			authorizationViewV.showPnLogin();
		} else if (e.getActionCommand().equals("LOGIN")) {
			authorizationViewV.pnLogin.authorizationLogin();
		} else if (e.getActionCommand().equals("REGISTER")) {
			authorizationViewV.pnRegister.createRegister();
		}
	}

}