package java_assignment;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Book_is_borrowed extends JDialog{
	
	private JPanel pnContent;
	
	private JLabel lblTitle;
	private JLabel lblContent1;
	
	private JButton btnUnderstand;
	private JLabel lblContent2;
	private JLabel lblContent3;
	private JLabel lblContent4;

	public Book_is_borrowed() {
		
		setTitle("Magnificent (Notification)");
		ImageIcon image = new ImageIcon("logo.png");
		setIconImage(image.getImage());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setSize(574, 413);
		getContentPane().setBackground(new Color(250, 250, 250));
		
		setResizable(false);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		
		InitGUI();
		setModal(true);
		setModalityType (ModalityType.APPLICATION_MODAL);
		setVisible(true);
		
	}

	private void InitGUI() {
		
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		pnContent = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, pnContent, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, pnContent, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, pnContent, 374, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, pnContent, 558, SpringLayout.WEST, getContentPane());
		pnContent.setBackground(new Color(250, 250, 250));
		getContentPane().add(pnContent);
		pnContent.setLayout(null);
		
		lblTitle = new JLabel("Opps!");
		lblTitle.setFont(new Font("SansSerif", Font.PLAIN, 32));
		lblTitle.setBounds(30, 52, 116, 42);
		pnContent.add(lblTitle);
		
		lblContent1 = new JLabel("The");
		lblContent1.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblContent1.setBounds(30, 118, 41, 32);
		pnContent.add(lblContent1);
		
		lblContent2 = new JLabel("selected book is borrowed");
		lblContent2.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblContent2.setForeground(new Color(0, 116, 183));
		lblContent2.setBounds(74, 118, 253, 32);
		pnContent.add(lblContent2);
		
		btnUnderstand = new JButton("I Understand");
		btnUnderstand.setFocusPainted(false);
		btnUnderstand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnUnderstand.setBorder(null);
		btnUnderstand.setBounds(39, 278, 478, 59);
		btnUnderstand.setBackground(new Color(0, 59, 115));
		btnUnderstand.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUnderstand.setForeground(Color.WHITE);
		btnUnderstand.setFont(new Font("SansSerif", Font.PLAIN, 24));
		pnContent.add(btnUnderstand);
		
		lblContent3 = new JLabel("by another person,");
		lblContent3.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblContent3.setBounds(337, 118, 180, 32);
		pnContent.add(lblContent3);
		
		lblContent4 = new JLabel("Please come back next time.");
		lblContent4.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblContent4.setBounds(33, 158, 294, 32);
		pnContent.add(lblContent4);
		
	}
	
}
