package java_assignment;

import java.awt.*;
import javax.swing.*;

public class Set_icon extends JLabel{

	public Set_icon() {
		super();
	}
	
	public void searchIcon() {
		super.setBounds(36, 141, 35, 26);
		super.setIcon(new ImageIcon("search.png"));
	}
	
	public void standard_searchIcon() {
		super.setIcon(new ImageIcon("search.png"));
	}
	
	public void black_searchIcon() {
		super.setIcon(new ImageIcon("black_search.png"));
	}

	public void backIcon() {
		super.setBounds(36, 40, 35, 26);
		super.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		super.setIcon(new ImageIcon("back.png"));
	}
	
}

