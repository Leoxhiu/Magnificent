package java_assignment;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Renew_used extends JDialog{

	private JPanel pnContent;
	
	private JLabel lblTitle;
	private JLabel lblContent1;
	
	private JButton btnConfirm;
	private JLabel lblContent2;
	private JLabel lblContent3;
	
	
	public Renew_used() {
		
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
		
		lblContent1 = new JLabel("The borrower has");
		lblContent1.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblContent1.setBounds(30, 118, 228, 32);
		pnContent.add(lblContent1);
		
		lblContent2 = new JLabel("used the renew chance before,");
		lblContent2.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblContent2.setForeground(new Color(0, 116, 183));
		lblContent2.setBounds(206, 118, 306, 32);
		pnContent.add(lblContent2);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setFocusPainted(false);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnConfirm.setBorder(null);
		btnConfirm.setBounds(39, 278, 478, 59);
		btnConfirm.setBackground(new Color(0, 59, 115));
		btnConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConfirm.setForeground(Color.WHITE);
		btnConfirm.setFont(new Font("SansSerif", Font.PLAIN, 24));
		pnContent.add(btnConfirm);
		
		lblContent3 = new JLabel("he/she is not allowed to renew again.");
		lblContent3.setFont(new Font("SansSerif", Font.PLAIN, 22));
		lblContent3.setBounds(30, 151, 457, 32);
		pnContent.add(lblContent3);
		
		
	}

}
