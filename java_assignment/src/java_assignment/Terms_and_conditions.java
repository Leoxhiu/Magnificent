package java_assignment;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Terms_and_conditions extends JDialog{

	private JLabel lblTitle;
	private JLabel lblCancel;
	
	private JButton btnAgree;
	private JLabel lblNumber1;
	private JLabel lblNumber1_content1;
	private JLabel lblNumber1_content2;
	private JLabel lblNumber2;
	private JLabel lblNumber2_content1;
	private JLabel lblNumber2_content2;
	private JLabel lblNumber2_content3;
	private JLabel lblNumber2_content4;
	private JLabel lblNumber2_content5;
	private JLabel lblNumber3;
	private JLabel lblNumber3_content1;
	private JLabel lblNumber3_content2;
	private JLabel lblNumber3_content3;
	private JLabel lblNumber4;
	private JLabel lblNumber4_content1;
	private JLabel lblNumber4_content2;
	private JLabel lblNumber4_content3;
	private JLabel lblNumber4_content4;
	
	private Borrow_book behind;

	//Get id book and current date from Borrow_book
	public Terms_and_conditions(String idBook, String date) {
		
		setTitle("Magnificent (Terms & Conditions)");
		ImageIcon image = new ImageIcon("logo.png");
		setIconImage(image.getImage());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setSize(574, 650);
		getContentPane().setBackground(new Color(250, 250, 250));
		
		setResizable(false);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		
		behind = new Borrow_book();
		
		//Transfer into GUI
		InitGUI(idBook, date);
		setModal(true);
		setModalityType (ModalityType.APPLICATION_MODAL);
		setVisible(true);
		
	}

	//Get data from constructor
	private void InitGUI(String id, String date) {
		
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JPanel pnContent = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, pnContent, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, pnContent, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, pnContent, 611, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, pnContent, 558, SpringLayout.WEST, getContentPane());
		pnContent.setBackground(new Color(250, 250, 250));
		getContentPane().add(pnContent);
		pnContent.setLayout(null);
		
		btnAgree = new JButton("I Agree");
		btnAgree.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				//Insert id book and new status into update book to call super( Book )
				Update_book updateBook = new Update_book(id, "Borrowed");
				
				//Insert id book and new status into update favorite
				Update_favourite updateFavourite = new Update_favourite(id, "Borrowed");
				updateFavourite.updateDatabase();
				
				//Insert id book to add current borrowed book, date is borrowing date
				//and calculating returning date
				Borrowed_book addBorrowedBook = new Add_borrowed(id, date);
				
				//Insert id book and add record in the history
				History_borrowed_book addInHistory = new Add_history_borrowed(id, date);
				
				dispose();
				
				Borrow_book reopen = new Borrow_book();
				behind.dispose();
				Borrow_success notification = new Borrow_success();
				
			}
		});
		btnAgree.setFocusPainted(false);
		btnAgree.setBorder(null);
		btnAgree.setBounds(38, 506, 478, 59);
		btnAgree.setBackground(new Color(0, 59, 115));
		btnAgree.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAgree.setForeground(Color.WHITE);
		btnAgree.setFont(new Font("SansSerif", Font.PLAIN, 24));
		pnContent.add(btnAgree);
		
		lblTitle = new JLabel("Terms & Conditions");
		lblTitle.setFont(new Font("SansSerif", Font.PLAIN, 32));
		lblTitle.setBounds(38, 24, 285, 42);
		pnContent.add(lblTitle);
		
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
		lblCancel.setBounds(251, 570, 49, 24);
		pnContent.add(lblCancel);
		
		lblNumber1 = new JLabel("1.");
		lblNumber1.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblNumber1.setBounds(38, 88, 18, 32);
		pnContent.add(lblNumber1);
		
		lblNumber1_content1 = new JLabel("Every borrowed book needs to be");
		lblNumber1_content1.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblNumber1_content1.setBounds(66, 88, 420, 32);
		pnContent.add(lblNumber1_content1);
		
		lblNumber1_content2 = new JLabel("returned within 14 days.");
		lblNumber1_content2.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblNumber1_content2.setForeground(new Color(0, 116, 183));
		lblNumber1_content2.setBounds(66, 119, 450, 32);
		pnContent.add(lblNumber1_content2);
		
		lblNumber2 = new JLabel("2.");
		lblNumber2.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblNumber2.setBounds(38, 170, 18, 32);
		pnContent.add(lblNumber2);
		
		lblNumber2_content1 = new JLabel("Borrower may");
		lblNumber2_content1.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblNumber2_content1.setBounds(66, 170, 136, 32);
		pnContent.add(lblNumber2_content1);
		
		lblNumber2_content2 = new JLabel("renew");
		lblNumber2_content2.setForeground(new Color(0, 116, 183));
		lblNumber2_content2.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblNumber2_content2.setBounds(212, 170, 57, 32);
		pnContent.add(lblNumber2_content2);
		
		lblNumber2_content3 = new JLabel("the book");
		lblNumber2_content3.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblNumber2_content3.setBounds(279, 170, 83, 32);
		pnContent.add(lblNumber2_content3);
		
		lblNumber2_content4 = new JLabel("(maximum once)");
		lblNumber2_content4.setForeground(new Color(0, 116, 183));
		lblNumber2_content4.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblNumber2_content4.setBounds(372, 170, 159, 32);
		pnContent.add(lblNumber2_content4);
		
		lblNumber2_content5 = new JLabel("to extend loan duration before the book expired.");
		lblNumber2_content5.setForeground(new Color(0, 116, 183));
		lblNumber2_content5.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblNumber2_content5.setBounds(66, 205, 465, 32);
		pnContent.add(lblNumber2_content5);
		
		lblNumber3 = new JLabel("3.");
		lblNumber3.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblNumber3.setBounds(38, 257, 18, 32);
		pnContent.add(lblNumber3);
		
		lblNumber3_content1 = new JLabel("Borrower will be charged if");
		lblNumber3_content1.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblNumber3_content1.setBounds(66, 257, 257, 32);
		pnContent.add(lblNumber3_content1);
		
		lblNumber3_content2 = new JLabel("overdue,");
		lblNumber3_content2.setForeground(new Color(0, 116, 183));
		lblNumber3_content2.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblNumber3_content2.setBounds(328, 257, 89, 32);
		pnContent.add(lblNumber3_content2);
		
		lblNumber3_content3 = new JLabel("RM1 per day.");
		lblNumber3_content3.setForeground(new Color(0, 116, 183));
		lblNumber3_content3.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblNumber3_content3.setBounds(66, 287, 148, 32);
		pnContent.add(lblNumber3_content3);
		
		lblNumber4 = new JLabel("4.");
		lblNumber4.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblNumber4.setBounds(38, 334, 18, 32);
		pnContent.add(lblNumber4);
		
		lblNumber4_content1 = new JLabel("Borrower");
		lblNumber4_content1.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblNumber4_content1.setBounds(66, 334, 89, 32);
		pnContent.add(lblNumber4_content1);
		
		lblNumber4_content2 = new JLabel("will be charged if");
		lblNumber4_content2.setForeground(new Color(0, 116, 183));
		lblNumber4_content2.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblNumber4_content2.setBounds(163, 334, 160, 32);
		pnContent.add(lblNumber4_content2);
		
		lblNumber4_content3 = new JLabel("the borrowed");
		lblNumber4_content3.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblNumber4_content3.setBounds(331, 334, 126, 32);
		pnContent.add(lblNumber4_content3);
		
		lblNumber4_content4 = new JLabel("book took any damage.");
		lblNumber4_content4.setForeground(new Color(0, 116, 183));
		lblNumber4_content4.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblNumber4_content4.setBounds(66, 365, 257, 32);
		pnContent.add(lblNumber4_content4);
		
	}

}
