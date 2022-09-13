package java_assignment;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Remove_confirm extends JDialog{

	private JPanel pnContent;
	
	private JLabel lblTitle;
	private JLabel lblContent1;
	private JLabel lblContent2;
	
	private JButton btnConfirm;
	private JLabel lblCancel;
	
	private Favourite_book behind;
	private JLabel lblBookName;
	
	public Remove_confirm(String idB, String bn) {
		
		setTitle("Magnificent (Notification)");
		ImageIcon image = new ImageIcon("logo.png");
		setIconImage(image.getImage());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setSize(574, 413);
		getContentPane().setBackground(new Color(250, 250, 250));
		
		setResizable(false);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		
		behind = new Favourite_book();
		
		InitGUI(idB, bn);
		setModal(true);
		setModalityType (ModalityType.APPLICATION_MODAL);
		setVisible(true);

	}

	private void InitGUI(String idBook, String bookName) {
		
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
		
		lblTitle = new JLabel("Confirmation");
		lblTitle.setFont(new Font("SansSerif", Font.PLAIN, 32));
		lblTitle.setBounds(30, 52, 393, 42);
		pnContent.add(lblTitle);
		
		lblContent1 = new JLabel("Are you sure that you");
		lblContent1.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblContent1.setBounds(30, 118, 209, 32);
		pnContent.add(lblContent1);
	
		
		lblContent2 = new JLabel("want to remove");
		lblContent2.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblContent2.setForeground(new Color(0, 116, 183));
		lblContent2.setBounds(241, 118, 264, 32);
		pnContent.add(lblContent2);
		
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setFocusPainted(false);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			Favourite_remove removeIt = new Favourite_remove(idBook);
			
			dispose();
			
			Favourite_book reopen = new Favourite_book();
			behind.dispose();
			Remove_success notification = new Remove_success();
	
			}
		});
		btnConfirm.setBorder(null);
		btnConfirm.setBounds(39, 269, 478, 59);
		btnConfirm.setBackground(new Color(0, 59, 115));
		btnConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConfirm.setForeground(Color.WHITE);
		btnConfirm.setFont(new Font("SansSerif", Font.PLAIN, 24));
		pnContent.add(btnConfirm);
		
		lblCancel = new JLabel("<HTML><U>Cancel</U></HTML>");
		lblCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		lblCancel.setForeground(new Color(96, 163, 217));
		lblCancel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblCancel.setBounds(254, 334, 49, 24);
		pnContent.add(lblCancel);
		
		lblBookName = new JLabel(bookName + " from favourite list?");
		lblBookName.setVerticalAlignment(SwingConstants.TOP);
		lblBookName.setForeground(new Color(0, 116, 183));
		lblBookName.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblBookName.setBounds(30, 152, 487, 71);
		pnContent.add(lblBookName);
		
	}

}
