package java_assignment;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Login_paint_logo extends JPanel implements ActionListener{
	
	private int panel_width = 558;
	private int panel_height = 230;
	private Image logo;
	private Timer timer;
	private int x = 200;
	private int y = -160;
	private int yVelo = 5;
	
	public Login_paint_logo() {
		
		this.setPreferredSize(new Dimension(panel_width, panel_height));
		this.setBackground(new Color(250, 250, 250));
		logo = new ImageIcon("logo.png").getImage();
		timer = new Timer(10, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(logo, x,  y , null);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(y >= panel_height- logo.getHeight(null) - 15) {
			timer.stop();
			
		}
		y = y + yVelo;
		repaint();
	}
	
	
}