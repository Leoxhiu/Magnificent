package java_assignment;

import java.awt.*;
import javax.swing.*;

public class Menu_button extends JButton{
	
	public Menu_button(String label) {
		super(label);
	}
	
	public void standard() {
		super.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		super.setBorder(null);
		super.setFont(new Font("SansSerif", Font.PLAIN, 24));
		super.setBackground(new Color(191, 215, 237));
		super.setFocusPainted(false);
	}
	
	public void setColor_white() {
		super.setForeground(Color.white);
	}
}
