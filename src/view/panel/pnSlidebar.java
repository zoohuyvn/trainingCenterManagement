package view.panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import bases.treeRenderer;
import bases.variables;
import controller.pnSlidebarListener;
import trainingCenterManagement.main;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class pnSlidebar extends JPanel {
	// Init tree
	public JTree tree;
	DefaultMutableTreeNode root, nullNode1, nullNode2,
	nullNode3, stManagement, avsManagement, clManagement;
	
	// pnRoleAndVersion init
	JPanel pnRoleAndVersion;
	
	// Init versionLabel
	JLabel roleLabel;
	JLabel versionLabel;
	
	// pnTabManagement
	pnTabManagement pnTabManagement;
	
	public pnSlidebar() {
		this.setLayout(new BorderLayout());
	    this.setBackground(Color.decode(variables.primaryColorDark));
		pnTabManagement = (pnTabManagement) pnBody.pnTabManagement;
		pnSlidebarListener ml = new pnSlidebarListener(this, pnTabManagement);
		
		// Tree
		root = new DefaultMutableTreeNode(" Zoohuy Training Center");
	    tree = new JTree(root);
	    tree.setBackground(Color.decode(variables.primaryColorDark));
	    tree.setCellRenderer(new treeRenderer());
	    nullNode1 = new DefaultMutableTreeNode();
	    nullNode2 = new DefaultMutableTreeNode();
	    nullNode3 = new DefaultMutableTreeNode();
	    stManagement = new DefaultMutableTreeNode(" Students");
	    avsManagement = new DefaultMutableTreeNode(" Advisors");
	    clManagement = new DefaultMutableTreeNode(" Class");
	    root.add(nullNode1);
	    root.add(stManagement);
	    root.add(nullNode2);
	    root.add(avsManagement);
	    root.add(nullNode3);
	    root.add(clManagement);
	    expandAllNodes(tree, 0, tree.getRowCount());
	    tree.addMouseListener(ml);
	    
	    // boxRoleAndVersion
	    pnRoleAndVersion = new JPanel(new BorderLayout());
	    pnRoleAndVersion.setBackground(Color.decode(variables.primaryColorDark));
	    pnRoleAndVersion.setPreferredSize(new Dimension(0, 50));
	    
	    // versionLabel
	    versionLabel = new JLabel("Version 3.1.2 by Zoohuy");
	    versionLabel.setForeground(Color.white);
	    versionLabel.setIcon(new ImageIcon(pnSlidebar.class.getResource("/assets/icons/copyright.png")));
	    if (main.role == 0) {
		    roleLabel = new JLabel("Role: Student");
		    roleLabel.setIcon(new ImageIcon(pnSlidebar.class.getResource("/assets/icons/student-role.png")));
	    } else if (main.role == 1) {
		    roleLabel = new JLabel("Role: Advisor");
		    roleLabel.setIcon(new ImageIcon(pnSlidebar.class.getResource("/assets/icons/advisor-role.png")));
	    } else if (main.role == 2) {
		    roleLabel = new JLabel("Role: Admin");
		    roleLabel.setIcon(new ImageIcon(pnSlidebar.class.getResource("/assets/icons/admin-role.png")));
	    }
	    roleLabel.setForeground(Color.white);
	    
	    pnRoleAndVersion.add(roleLabel, BorderLayout.NORTH);
	    pnRoleAndVersion.add(versionLabel, BorderLayout.SOUTH);
	    
	    // Add component
	    this.add(tree, BorderLayout.CENTER);
	    this.add(pnRoleAndVersion, BorderLayout.SOUTH);
	}
	
	// Method to expand all nodes of tree
	private void expandAllNodes(JTree tree, int startingIndex, int rowCount) {
	    for (int i = startingIndex; i < rowCount; i++) {
	        tree.expandRow(i);
	    }
	    if (tree.getRowCount() != rowCount) {
	        expandAllNodes(tree, rowCount, tree.getRowCount());
	    }
	}
	
}