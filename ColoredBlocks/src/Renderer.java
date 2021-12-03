
import javax.swing.JPanel;

import java.awt.Graphics;

public class Renderer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2076458284057457740L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Item.item.repaint(g);
	}
}