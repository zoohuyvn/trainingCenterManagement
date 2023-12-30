package controller;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import view.panel.pnClass;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class pnClassKeyboardListener implements KeyListener {
	private pnClass pnClassV;
	
	public pnClassKeyboardListener(pnClass pnClassV) {
		this.pnClassV = pnClassV;
	}

	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
        if (!((c >= '0' && c <= '9') || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
            e.consume();
        }
	}

	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {
		pnClassV.formatCurrency(pnClassV.priceTextField);
	}

}