package java_assignment;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Renew_confirm extends JDialog{

	private JPanel pnContent;
	
	private JLabel lblTitle;
	private JLabel lblContent1;
	private JLabel lblContent2;
	
	private JButton btnConfirm;
	private JLabel lblCancel;
	
	private L_current_borrowing behind;
	private JLabel lblBookName;
	
	
	public Renew_confirm( String idU, String idB, String bn , String rd, String ne, String nc ,String bd) {
		
		setTitle("Magnificent (Notification)");
		ImageIcon image = new ImageIcon("logo.png");
		setIconImage(image.getImage());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setSize(574, 413);
		getContentPane().setBackground(new Color(250, 250, 250));
		
		setResizable(false);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		
		behind = new L_current_borrowing();
		
		InitGUI(idU, idB, bn, rd, ne, nc , bd);
		setModal(true);
		setModalityType (ModalityType.APPLICATION_MODAL);
		setVisible(true);
		
	}

	private void InitGUI(String idUser,String idBook, String bookName, String renewDate, String newExpire, String newChance , String borrowDate) {
		
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
		
		lblContent1 = new JLabel("Are you sure that the borrower");
		lblContent1.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblContent1.setBounds(30, 118, 290, 32);
		pnContent.add(lblContent1);
	
		
		lblContent2 = new JLabel("wants to renew");
		lblContent2.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblContent2.setForeground(new Color(0, 116, 183));
		lblContent2.setBounds(330, 118, 187, 32);
		pnContent.add(lblContent2);
		
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setFocusPainted(false);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				int chance = Integer.parseInt(newChance);
				
				//Insert id book, renew date, new expire date , new chance (0) to update record in borrowed book
				Borrowed_book renewUpdate = new Update_borrowed(idBook, renewDate, newExpire, chance);
				
				//Insert id book, current date, renew date, new expire date to update record in the history
				History_borrowed_book updateHistory = new Update_history_borrowed(idUser, idBook, borrowDate ,renewDate, newExpire);

				dispose();
				
				L_current_borrowing reopen = new L_current_borrowing();
				behind.dispose();
				Renew_success notification = new Renew_success();
				
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
		
		lblBookName = new JLabel(bookName + "?");
		lblBookName.setVerticalAlignment(SwingConstants.TOP);
		lblBookName.setForeground(new Color(0, 116, 183));
		lblBookName.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblBookName.setBounds(30, 152, 487, 71);
		pnContent.add(lblBookName);
		
	}

}
