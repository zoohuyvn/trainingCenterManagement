package trainingCenterManagement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import bases.variables;
import model.user;
import view.authorizationView;
import view.mainView;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class main extends JFrame {
	public static int role = -2;
	public static user user = null;
	JPanel pnNorth = new JPanel();
	JPanel pnSouth = new JPanel();
	JPanel pnWest = new JPanel();
	JPanel pnEast = new JPanel();
	
	public main() {
		super("Training Center Management Program");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Beautiful new UI win 10
		try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
	    catch (Exception e) { e.printStackTrace(); }
		
		// Set default font
	    UIDefaults defaults = UIManager.getLookAndFeelDefaults();
	    Font font14 = new Font("Roboto", Font.PLAIN, 14);
	    Font font15 = new Font("Roboto", Font.PLAIN, 15);
	    Font font16 = new Font("Roboto", Font.PLAIN, 16);
	    defaults.put("Label.font", font16);
	    defaults.put("Menu.font", font15);
	    defaults.put("MenuItem.font", font15);
	    defaults.put("TabbedPane.font", font16);
	    defaults.put("TableHeader.font", font15);
	    defaults.put("Table.font", font14);
	    defaults.put("ComboBox.font", font15);
	    defaults.put("TextField.font", font15);
	    defaults.put("Button.font", font16);
	    defaults.put("RadioButton.font", font15);
	    
	    // Main handle
	    showLoginView();
	    // Skip login
//	    role = 2;
//	    handleLoginSuccess();

	    // Set properties for UI
		this.setIconImage(new ImageIcon(main.class.getResource("/assets/icons/building.png")).getImage());
		this.setSize(1366, 768);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void showLoginView() {
		// Container layout init
	    getContentPane().setLayout(new BorderLayout());
	    getContentPane().add(pnNorth, BorderLayout.NORTH);
	    getContentPane().add(pnSouth, BorderLayout.SOUTH);
	    getContentPane().add(pnWest, BorderLayout.WEST);
	    getContentPane().add(pnEast, BorderLayout.EAST);
	    pnNorth.setBackground(Color.decode(variables.primaryColorLight));
	    pnSouth.setBackground(Color.decode(variables.primaryColorLight));
	    pnWest.setBackground(Color.decode(variables.primaryColorLight));
	    pnEast.setBackground(Color.decode(variables.primaryColorLight));
	    authorizationView x = new authorizationView();
		getContentPane().add(x);
	}
	
	public void handleLoginSuccess() {
		if (role >= 0) {
			// Remove all in container
			getContentPane().removeAll();
			// Container layout reinit
		    getContentPane().setLayout(new BorderLayout());
		    getContentPane().add(pnNorth, BorderLayout.NORTH);
		    getContentPane().add(pnSouth, BorderLayout.SOUTH);
		    getContentPane().add(pnWest, BorderLayout.WEST);
		    getContentPane().add(pnEast, BorderLayout.EAST);
		    // change main border bg
		    pnNorth.setBackground(Color.decode(variables.primaryColorDark));
		    pnSouth.setBackground(Color.decode(variables.primaryColorDark));
		    pnWest.setBackground(Color.decode(variables.primaryColorDark));
		    pnEast.setBackground(Color.decode(variables.primaryColorDark));
            getContentPane().add(new mainView());
			this.setJMenuBar(mainView.menuBar);
			// Refresh frame
			revalidate();
	        repaint();
        }
	}
	
	public static void main(String[] args) {
		new main();
	}
	
}