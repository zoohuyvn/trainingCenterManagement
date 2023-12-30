package bases;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

// Class to overwrite and fill color for tree bg
public class treeRenderer extends DefaultTreeCellRenderer {

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
        boolean expanded, boolean leaf, int row, boolean hasFocus) {
    	String nodeLabel = ((DefaultMutableTreeNode) value).toString();
        JLabel label = new JLabel(nodeLabel);
        label.setForeground(Color.white);
        label.setFont(new Font("Roboto", Font.PLAIN, 17));
        if (nodeLabel.equals(" Zoohuy Training Center")) {
            label.setIcon(new ImageIcon(treeRenderer.class.getResource("/assets/icons/folder.png")));
        } else if (nodeLabel.equals(" Students")) {
        	label.setIcon(new ImageIcon(treeRenderer.class.getResource("/assets/icons/user.png")));
        } else if (nodeLabel.equals(" Advisors")) {
        	label.setIcon(new ImageIcon(treeRenderer.class.getResource("/assets/icons/user.png")));
        } else if (nodeLabel.equals(" Class")) {
        	label.setIcon(new ImageIcon(treeRenderer.class.getResource("/assets/icons/school.png")));
        }
        return label;
    }
    
}