package java_assignment;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Search_bar extends JTextField{
	
	public Search_bar() {
		super();
	}
	
	public void standard() {
		
		super.setForeground(new Color(116, 116, 116));
		super.setFont(new Font("SansSerif", Font.PLAIN, 18));
		super.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(116, 116, 116)));
		super.setOpaque(false);
		super.setText("Search");
		
	}
	
	public void borderColor_standard() {
		super.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(116, 116, 116)));
	}
	
	public void borderColor_black() {
		super.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.black));
	}
	
}
