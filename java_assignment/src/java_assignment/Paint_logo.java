package java_assignment;

import java.awt.*;
import javax.swing.*;

public class Paint_logo extends JPanel {
	
	private int panel_width = 326;
	private int panel_height = 110;
	private Image logo;
	private int x = 125;
	private int y = 40;
	
	public Paint_logo() {
		
		this.setPreferredSize(new Dimension(panel_width, panel_height));
		this.setBackground(new Color(191, 215, 237));
		logo = new ImageIcon("logo2.png").getImage();
	}
	
	public void paint(Graphics g) {
		
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(logo, x,  y , null);
	}

}