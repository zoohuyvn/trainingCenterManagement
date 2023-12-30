package view;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import bases.variables;
import controller.authorizationListener;
import view.panel.pnLogin;
import view.panel.pnRegister;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class authorizationView extends JPanel {
	static JPanel pnForm;
	static CardLayout cardLayout;
	public pnLogin pnLogin = new pnLogin();
	public pnRegister pnRegister = new pnRegister();
	
	public authorizationView() {
	    this.setLayout(new BorderLayout());
	    
	    // Handle change form
		ActionListener acl = new authorizationListener(this);
		pnLogin.btnRegister.addActionListener(acl);
		pnRegister.btnLoginNow.addActionListener(acl);
		
		// Handle main task
		pnLogin.btnLogin.addActionListener(acl);
		pnRegister.btnRegister.addActionListener(acl);
	    
	    // pnGif
	    JPanel pnGif = new JPanel(new BorderLayout());
		pnGif.setPreferredSize(new Dimension(800, 500));
	    pnGif.setBackground(Color.decode(variables.primaryColorLight));
		JLabel test = new JLabel("", SwingConstants.CENTER);
		test.setIcon(new ImageIcon(authorizationView.class.getResource("/assets/images/happy-student.gif")));
		JPanel pnWelcomeLabel = new JPanel();
		JLabel welcomeLabel = new JLabel("", SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Roboto", Font.PLAIN, 45));
		welcomeLabel.setText("<html><body>Zoohuy Training Center<br>Management Program</body></html>");
		welcomeLabel.setForeground(Color.decode(variables.primaryColor));
		welcomeLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		pnWelcomeLabel.setBackground(Color.decode(variables.primaryColorLight));
		pnWelcomeLabel.setPreferredSize(new Dimension(800, 180));
		pnWelcomeLabel.add(welcomeLabel);
		pnGif.add(pnWelcomeLabel, BorderLayout.NORTH);
		pnGif.add(test, BorderLayout.CENTER);
		
		// pnForm
		pnForm = new JPanel();
		cardLayout = new CardLayout();
		pnForm.setLayout(cardLayout);
		pnForm.add(pnLogin, "pnLogin");
		pnForm.add(pnRegister, "pnRegister");
		
		// Add component
		this.add(pnForm, BorderLayout.CENTER);
		this.add(pnGif, BorderLayout.EAST);
	}
	
	public static void showPnLogin() {
		cardLayout.show(pnForm, "pnLogin");
	}
	
	public void showPnRegister() {
		cardLayout.show(pnForm, "pnRegister");
	}
	
}