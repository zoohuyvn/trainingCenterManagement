package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import bases.variables;
import controller.menuBarListener;
import view.panel.pnBody;
import view.panel.pnSlidebar;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class mainView extends JPanel {
	// Init menuBar
	public static JMenuBar menuBar = new JMenuBar() {
		protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setColor(Color.decode(variables.primaryColorLight));
	        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

	    }
	};
	private JMenu menuFile;
	private JMenuItem menuItemSave, menuItemExit;
	JMenu menuHelp;
	JMenuItem menuItemAbout, menuItemContact;
	
	// Init splitPanel
	JSplitPane splitPane;
	JPanel pnSlidebar, pnBody;
	
	public mainView() {
		this.setLayout(new BorderLayout());
		ActionListener acl = new menuBarListener();
		
		// menuBar
		menuFile = new JMenu("File");
		menuItemSave = new JMenuItem("Export data");
		menuItemExit = new JMenuItem("Exit");
		menuItemSave.setIcon(new ImageIcon(mainView.class.getResource("/assets/icons/export.png")));
		menuItemExit.setIcon(new ImageIcon(mainView.class.getResource("/assets/icons/out.png")));
		menuItemSave.addActionListener(acl);
		menuItemExit.addActionListener(acl);
		menuFile.add(menuItemSave);
		menuFile.addSeparator();
		menuFile.add(menuItemExit);
		menuHelp = new JMenu("Help");
		menuItemAbout = new JMenuItem("About");
		menuItemContact = new JMenuItem("Contact");
		menuItemAbout.setIcon(new ImageIcon(mainView.class.getResource("/assets/icons/info.png")));
		menuItemContact.setIcon(new ImageIcon(mainView.class.getResource("/assets/icons/profile.png")));
		menuItemAbout.addActionListener(acl);
		menuItemContact.addActionListener(acl);
		menuHelp.add(menuItemAbout);
		menuHelp.add(menuItemContact);
		menuBar.add(menuFile);
		menuBar.add(menuHelp);

		// splitPane
		pnSlidebar = new pnSlidebar();
		pnBody = new pnBody();
		pnBody.setBackground(Color.decode(variables.primaryColorDark));
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnSlidebar, pnBody);
		splitPane.setDividerLocation(220);
		splitPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		splitPane.setEnabled(false);
	    Dimension minimumSize = new Dimension(220, 0);
	    pnSlidebar.setMinimumSize(minimumSize);
	    pnBody.setMinimumSize(minimumSize);
	    
	    // Add component
	    this.add(splitPane, BorderLayout.CENTER);
	}
	
}