package java_assignment;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Login extends JFrame{
	
	private JPanel logo;
	private JPanel pnContent;
	
	private JLabel lblID;
	private JLabel lblPassword;
	private JLabel lblInvalid;
	
	private JTextField txtID;
	private JButton btnSignIn;
	private JPasswordField txtPassword;
	private JCheckBox chckbxShowPassword;
	
	public Login() {
		setTitle("Magnificent");
		ImageIcon image = new ImageIcon("logo.png");
		setIconImage(image.getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setSize(574, 768);
		getContentPane().setBackground(new Color(250, 250, 250));
		setResizable(false);
		
		InitGUI();
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	private void InitGUI() {
		
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		logo = new Login_paint_logo();
		getContentPane().add(logo);
		
		pnContent = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, pnContent, 2, SpringLayout.SOUTH, logo);
		springLayout.putConstraint(SpringLayout.WEST, pnContent, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, pnContent, 499, SpringLayout.SOUTH, logo);
		springLayout.putConstraint(SpringLayout.EAST, pnContent, 0, SpringLayout.EAST, logo);
		pnContent.setBackground(new Color(250, 250, 250));
		getContentPane().add(pnContent);
		pnContent.setLayout(null);
		
		txtID = new JTextField();
		txtID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				if(Character.isSpaceChar(c) || !(Character.isLetterOrDigit(c)) || txtID.getText().trim().length() == 8){
					e.consume();
				}
			}
		});
		txtID.setFont(new Font("SansSerif", Font.PLAIN, 24));
		txtID.setColumns(8);
		txtID.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));
		txtID.setBackground(new Color(191, 215, 237));
		txtID.setBounds(74, 82, 411, 68);
		pnContent.add(txtID);
		
		lblID = new JLabel("ID:");
		lblID.setForeground(new Color(136, 136, 136));
		lblID.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblID.setBounds(74, 52, 22, 24);
		pnContent.add(lblID);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setForeground(new Color(136, 136, 136));
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblPassword.setBounds(74, 171, 84, 24);
		pnContent.add(lblPassword);
		
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("SansSerif", Font.PLAIN, 24));
		txtPassword.setColumns(30);
		txtPassword.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));
		txtPassword.setBackground(new Color(191, 215, 237));
		txtPassword.setBounds(74, 201, 411, 68);
		pnContent.add(txtPassword);
		
		btnSignIn = new JButton("Sign In");
		btnSignIn.setFocusPainted(false);
		btnSignIn.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				
				if(txtID.getText().trim().isEmpty() && txtPassword.getText().trim().isEmpty()) {
					lblInvalid.setText("**Blank ID and password, please fill in the blank.");
					lblInvalid.setVisible(true);
				}
				else if(txtID.getText().trim().isEmpty()) {
					lblInvalid.setText("**Blank ID, please fill in the blank.");
					lblInvalid.setVisible(true);
				}
				else if(txtPassword.getText().trim().isEmpty()){
					lblInvalid.setText("**Blank password, please fill in the blank.");
					lblInvalid.setVisible(true);
				}
				else if(!(txtID.getText().trim().length() == 4) && !(txtID.getText().trim().length() == 8) ) {
					lblInvalid.setText("**Invalid ID, please try again.");
					lblInvalid.setVisible(true);
				}
				else {
			
					B_verify_login userVerify = new B_verify_login(txtID.getText().trim().toLowerCase(), txtPassword.getText().trim());
					
					if(userVerify.getFound() == true) {
						
						dispose();
						Borrow_book nav = new Borrow_book();
						
					}
					else if (userVerify.getFound() == false){
						
						L_verify_login libVerify = new L_verify_login(txtID.getText().trim().toLowerCase(), txtPassword.getText().trim());
						
						if(libVerify.getFound() == true) {
							
							dispose();
							L_manage_book open = new L_manage_book();
							
						}else if(libVerify.getFound() == false) {
							
						lblInvalid.setText("**Invalid ID or password, please try again.");
						lblInvalid.setVisible(true);
							
						}
						
						
					}
					
					
				}
				
			}
		});
		btnSignIn.setForeground(Color.WHITE);
		btnSignIn.setFont(new Font("SansSerif", Font.PLAIN, 24));
		btnSignIn.setBorder(null);
		btnSignIn.setBackground(new Color(0, 59, 115));
		btnSignIn.setBounds(74, 355, 411, 68);
		btnSignIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnContent.add(btnSignIn);
		
		
		chckbxShowPassword = new JCheckBox("Show Password");
		chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(chckbxShowPassword.isSelected()) {
					txtPassword.setEchoChar((char)0);
				}
				else {
					txtPassword.setEchoChar('*');
				}
					
			}
		});
		chckbxShowPassword.setBounds(365, 276, 126, 23);
		chckbxShowPassword.setBackground(new Color(250, 250, 250));
		chckbxShowPassword.setForeground(new Color(136, 136, 136));
		chckbxShowPassword.setFont(new Font("SansSerif", Font.PLAIN, 14));
		pnContent.add(chckbxShowPassword);
		
		lblInvalid = new JLabel("Invalid");
		lblInvalid.setForeground(Color.red);
		lblInvalid.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblInvalid.setBounds(74, 326, 411, 24);
		lblInvalid.setVisible(false);
		pnContent.add(lblInvalid);
		
	}
	
}
