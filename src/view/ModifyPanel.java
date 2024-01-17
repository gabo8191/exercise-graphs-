package view;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ModifyPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModifyPanel() {
	}
	String directionImg=null;

	@Override
	public void paint(Graphics g) {
		Dimension dimension = this.getSize();
		ImageIcon icon = new ImageIcon(getClass().getResource(directionImg));
		g.drawImage(icon.getImage(), 0, 0, dimension.width, dimension.height, null);
		setOpaque(false);
		super.paintChildren(g);
	}

	public String getDirectionImg() {
		return directionImg;
	}

	public void setDirectionImg(String directionImg) {
		this.directionImg = directionImg;
	}
	

}
