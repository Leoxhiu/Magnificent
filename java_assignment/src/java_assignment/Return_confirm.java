package java_assignment;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Return_confirm extends JDialog{

	private JPanel pnContent;
	
	private JLabel lblTitle;
	private JLabel lblContent1;
	private JLabel lblContent2;
	
	private JButton btnConfirm;
	private JLabel lblCancel;
	
	private L_current_borrowing behind;
	private JLabel lblThis;
	
	//Get id user, id book, book name, borrow date and current date
	public Return_confirm(String idU, String idB, String bn, String bd, String cd) {
		
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
		
		InitGUI(idU, idB, bn, bd, cd);
		setModal(true);
		setModalityType (ModalityType.APPLICATION_MODAL);
		setVisible(true);
		
	}

	private void InitGUI(String idUser,String idBook, String bookName, String borrowDate, String currentDate) {
		
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
	
		
		lblContent2 = new JLabel("wants to return");
		lblContent2.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblContent2.setForeground(new Color(0, 116, 183));
		lblContent2.setBounds(330, 118, 146, 32);
		pnContent.add(lblContent2);
		
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setFocusPainted(false);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Find the book with id and change its status to Available
				Update_book isAvailable = new Update_book(idBook, "Available");
				
				//Insert id book and new status into update favorite
				Update_favourite updateFavourite = new Update_favourite(idBook, "Available");
				updateFavourite.updateDatabase();
				
				//Using id book to delete record in borrowed book
				Borrowed_book delete = new Delete_borrowed(idBook);
				
				//Add record in returned book of the user
				Returned_book record = new Returned_book(idUser, idBook, borrowDate, currentDate , "No");
				
				dispose();
				
				L_current_borrowing reopen = new L_current_borrowing();
				behind.dispose();
				Return_success notification = new Return_success();
				
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
		
		lblThis = new JLabel(bookName + "?");
		lblThis.setVerticalAlignment(SwingConstants.TOP);
		lblThis.setForeground(new Color(0, 116, 183));
		lblThis.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblThis.setBounds(30, 152, 487, 71);
		pnContent.add(lblThis);
		
	}

}
