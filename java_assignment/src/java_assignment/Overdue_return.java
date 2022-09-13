package java_assignment;

import java.awt.*;
import java.awt.Dialog.*;
import java.awt.event.*;
import javax.swing.*;

public class Overdue_return extends JDialog{
	
	private JPanel pnContent;
	
	private JLabel lblTitle;

	private JLabel lblContent1;
	private JLabel lblContent2;
	private JLabel lblContent3;
	private JLabel lblContent4;
	private JLabel lblContent5;
	private JLabel lblContent6;
	
	private JLabel lblExpireDate;
	private JLabel lblRealExpireDate;
	private JLabel lblCurrentDate;
	private JLabel lblRealCurrentDate;
	private JLabel lblOverdue;
	private JLabel lblRealOverdue;
	private JLabel lblCurrentFine;
	private JLabel lblRealCurrentFine;
	private JLabel lblMsg;
	private JLabel lblRealBorrowDate;
	private JLabel lblBorrowDate;
	
	private JButton btnReturn;
	private JLabel lblCancel;
	
	private L_current_borrowing behind;
	
	//Get borrow date, current date and days of overdue from Book Handling
	public Overdue_return(String idU, String idB ,String b, String e, String c, String o ) {
		setTitle("Magnificent (Overdue)");
		ImageIcon image = new ImageIcon("logo.png");
		setIconImage(image.getImage());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setSize(574, 650);
		getContentPane().setBackground(new Color(250, 250, 250));
		
		setResizable(false);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);

		behind = new L_current_borrowing();
		
		//Transfer into GUI
		InitGUI(idU, idB, b, e , c, o);
		setModal(true);
		setModalityType (ModalityType.APPLICATION_MODAL);
		setVisible(true);

	}

	private void InitGUI(String idUser, String idBook, String borrowDate, String expireDate, String currentDate, String overdue) {
	
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		pnContent = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, pnContent, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, pnContent, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, pnContent, 611, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, pnContent, 558, SpringLayout.WEST, getContentPane());
		pnContent.setBackground(new Color(250, 250, 250));
		getContentPane().add(pnContent);
		pnContent.setLayout(null);
		
		btnReturn = new JButton("Return");		
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				//Find the book with id and change its status to Available
				Update_book isAvailable = new Update_book(idBook, "Available");
				
				//Insert id book and new status into update favorite
				Update_favourite updateFavourite = new Update_favourite(idBook, "Available");
				updateFavourite.updateDatabase();
				
				//Using id book to delete record in borrowed book
				Borrowed_book delete = new Delete_borrowed(idBook);
				
				//Add record in returned book of the user
				Returned_book record = new Returned_book(idUser, idBook, borrowDate, currentDate , "Yes");
				
				dispose();
				
				L_current_borrowing reopen = new L_current_borrowing();
				behind.dispose();
				Return_success notification = new Return_success();
				dispose();
			}
		});
		btnReturn.setFocusPainted(false);
		btnReturn.setBorder(null);
		btnReturn.setBounds(46, 506, 478, 59);
		btnReturn.setBackground(new Color(0, 59, 115));
		btnReturn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReturn.setForeground(Color.WHITE);
		btnReturn.setFont(new Font("SansSerif", Font.PLAIN, 24));
		pnContent.add(btnReturn);
		
		lblTitle = new JLabel("Overdue Return");
		lblTitle.setFont(new Font("SansSerif", Font.PLAIN, 32));
		lblTitle.setBounds(38, 24, 285, 42);
		pnContent.add(lblTitle);
		
		lblContent1 = new JLabel("The book is");
		lblContent1.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblContent1.setBounds(43, 88, 121, 32);
		pnContent.add(lblContent1);
		
		lblContent2 = new JLabel("overdue!");
		lblContent2.setForeground(new Color(0, 116, 183));
		lblContent2.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblContent2.setBounds(164, 88, 89, 32);
		pnContent.add(lblContent2);
		
		lblContent3 = new JLabel("Current");
		lblContent3.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblContent3.setBounds(258, 88, 70, 32);
		pnContent.add(lblContent3);
		
		lblContent4 = new JLabel("fine rate");
		lblContent4.setForeground(new Color(0, 116, 183));
		lblContent4.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblContent4.setBounds(335, 88, 78, 32);
		pnContent.add(lblContent4);
		
		lblContent5 = new JLabel("is");
		lblContent5.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblContent5.setBounds(422, 88, 16, 32);
		pnContent.add(lblContent5);
		
		lblContent6 = new JLabel("RM 1 per day.");
		lblContent6.setForeground(new Color(0, 116, 183));
		lblContent6.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblContent6.setBounds(45, 123, 137, 32);
		pnContent.add(lblContent6);
		
		lblExpireDate = new JLabel("Expiry Date");
		lblExpireDate.setForeground(new Color(116, 116, 116));
		lblExpireDate.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblExpireDate.setBounds(43, 241, 137, 32);
		pnContent.add(lblExpireDate);
		
		lblRealExpireDate = new JLabel(expireDate);
		lblRealExpireDate.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblRealExpireDate.setBounds(210, 241, 312, 32);
		pnContent.add(lblRealExpireDate);
		
		lblCurrentDate = new JLabel("Current date:");
		lblCurrentDate.setForeground(new Color(116, 116, 116));
		lblCurrentDate.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblCurrentDate.setBounds(43, 284, 137, 32);
		pnContent.add(lblCurrentDate);
		
		lblRealCurrentDate = new JLabel(currentDate);
		lblRealCurrentDate.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblRealCurrentDate.setBounds(210, 284, 312, 32);
		pnContent.add(lblRealCurrentDate);
		
		lblOverdue = new JLabel("Overdue:");
		lblOverdue.setForeground(new Color(116, 116, 116));
		lblOverdue.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblOverdue.setBounds(42, 343, 137, 32);
		pnContent.add(lblOverdue);
		
		lblRealOverdue = new JLabel(overdue + " day");
		lblRealOverdue.setForeground(new Color(0, 116, 183));
		lblRealOverdue.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblRealOverdue.setBounds(209, 343, 314, 32);
		pnContent.add(lblRealOverdue);
		
		lblCurrentFine = new JLabel("Current fine:");
		lblCurrentFine.setForeground(new Color(116, 116, 116));
		lblCurrentFine.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblCurrentFine.setBounds(42, 381, 137, 32);
		pnContent.add(lblCurrentFine);
		
		lblRealCurrentFine = new JLabel("RM " + overdue);
		lblRealCurrentFine.setForeground(new Color(0, 116, 183));
		lblRealCurrentFine.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblRealCurrentFine.setBounds(209, 381, 314, 32);
		pnContent.add(lblRealCurrentFine);
		
		lblMsg = new JLabel("** Please ensure that the borrower paid the fine.");
		lblMsg.setForeground(new Color(0, 116, 183));
		lblMsg.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblMsg.setBounds(47, 480, 477, 24);
		pnContent.add(lblMsg);
		
		lblRealBorrowDate = new JLabel(borrowDate);
		lblRealBorrowDate.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblRealBorrowDate.setBounds(210, 185, 312, 32);
		pnContent.add(lblRealBorrowDate);
		
		lblBorrowDate = new JLabel("Borrow date:");
		lblBorrowDate.setForeground(new Color(116, 116, 116));
		lblBorrowDate.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblBorrowDate.setBounds(43, 185, 137, 32);
		pnContent.add(lblBorrowDate);
		
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
		lblCancel.setBounds(258, 572, 49, 24);
		pnContent.add(lblCancel);
		
	}
}
