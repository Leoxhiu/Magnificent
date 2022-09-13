package java_assignment;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.*;

public class L_edit_book extends JFrame{
	
	private Paint_logo logo;
	private Set_icon imgBack;
	private JPanel pnMenu;
	
	private JLabel lblRealName;
	private JLabel lblRealID;
	private JLabel lblID;
	private JLabel lblName; 

	private JLabel lblTime;
	private JLabel lblDate;
	private Menu_button btnLogout;
	private Menu_button btnHistory;
	private Menu_button btnCurrentBorrowing;
	private Menu_button btnManageBook;
	private Menu_button btnHistoryBorrowedBook;
	private Menu_button btnReturnedBook;
	
	private JButton btnSave;
	
	private JLabel lblTitle;
	
	private JLabel ImgUpload;
	private String selectedImgPath;
	
	private JPanel pnContent;
	
	private SimpleDateFormat timeFormat;
	private String show_time;
	private String show_date;
	
	private Clock_timer timer;
	private JLabel lblAddBook;
	private JLabel lblError;
	private JTextField txtBookID;
	private JTextField txtBookName;
	private JLabel lblBookID;
	private JComboBox<Object> CBXbookType;
	private JLabel lblBookName;
	private JLabel lblBookType;
	private boolean imgUploaded = false;
	
	private File selectedImg;
	private String imgPath;
	private String bookPath = "book.txt";
	private static Scanner scanner;
	
	public L_edit_book(String id, String name ,String type) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try (FileReader fr = new FileReader(bookPath)){
					
					scanner = new Scanner (fr);
					String line;
					String[] lineArr;
						
					while((line = scanner.nextLine())!=null) {
						lineArr = line.split(",");
							
						if(lineArr[0].equals(id)){
								
						imgPath = lineArr[1].toString();
						break;
						}
				}
				fr.close();
			
			}
			catch (Exception e1) {

			}
				
				ImageIcon img = new ImageIcon (imgPath);
				Image resizeImg = img.getImage().getScaledInstance(ImgUpload.getWidth(), ImgUpload.getHeight(), Image.SCALE_SMOOTH);
				
				ImgUpload.setIcon(new ImageIcon(resizeImg));
				
				txtBookID.setText(id);
				txtBookName.setText(name);
				CBXbookType.setSelectedItem(type);
				
			}
		});
		
		setTitle("Magnificent");
		ImageIcon image = new ImageIcon("logo.png");
		setIconImage(image.getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setSize(1366, 768);
		getContentPane().setBackground(new Color(250, 250, 250));
		setResizable(false);
		
		InitGUI(id, name, type);
		setVisible(true);
		setLocationRelativeTo(null);
		
		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Date date = new Date();
				timeFormat = new SimpleDateFormat("E, hh:mm:ss a");
				show_time = timeFormat.format(date);
				lblTime.setText(show_time);
				
				timeFormat = new SimpleDateFormat("dd/MM/yyyy");
				show_date = timeFormat.format(date);
				lblDate.setText(show_date);
			}
			
		};
		
		timer = new Clock_timer(1000, actionListener);
		timer.start();
    } 

	private void InitGUI(String id, String name, String type) {
		
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		logo = new Paint_logo();
		getContentPane().add(logo);
		
		pnMenu = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, pnMenu, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, pnMenu, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, pnMenu, 729, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, pnMenu, 326, SpringLayout.WEST, getContentPane());
		pnMenu.setBackground(new Color(191, 215, 237));
		getContentPane().add(pnMenu);
		pnMenu.setLayout(null);

		lblTime = new JLabel();
		lblTime.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblTime.setBounds(0, 669, 326, 24);
		lblTime.setHorizontalAlignment(lblTime.CENTER);
		pnMenu.add(lblTime);

		lblDate = new JLabel("Date");
		lblDate.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblDate.setBounds(0, 694, 326, 24);
		lblDate.setHorizontalAlignment(lblDate.CENTER);
		pnMenu.add(lblDate);
		
		btnLogout = new Menu_button("Logout");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			dispose();
			Login nav = new Login();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogout.setBackground(new Color(167, 197, 225));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				if(btnHistoryBorrowedBook.isVisible() && btnReturnedBook.isVisible()) {
					btnLogout.setBackground(new Color(167, 197, 225));
				}
				else {
				btnLogout.setBackground(new Color(191, 215, 237));
				}
			}
		});
		btnLogout.standard();
		btnLogout.setBounds(0, 480, 326, 81);
		pnMenu.add(btnLogout);
		
		btnHistory = new Menu_button("History");
		btnHistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				if(btnHistoryBorrowedBook.isVisible() && btnReturnedBook.isVisible()) {
					btnHistoryBorrowedBook.setVisible(false);
					btnReturnedBook.setVisible(false);
				}
				else {
					btnHistoryBorrowedBook.setVisible(true);
					btnReturnedBook.setVisible(true);
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHistory.setBackground(new Color(167, 197, 225));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnHistory.setBackground(new Color(191, 215, 237));
			}
		});
		btnHistory.standard();
		btnHistory.setBounds(0, 394, 326, 81);
		pnMenu.add(btnHistory);
		
		btnCurrentBorrowing = new Menu_button("Current Borrowing");
		btnCurrentBorrowing.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			
				L_current_borrowing openPage = new L_current_borrowing();
				dispose();
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCurrentBorrowing.setBackground(new Color(167, 197, 225));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnCurrentBorrowing.setBackground(new Color(191, 215, 237));
			}
		});
		btnCurrentBorrowing.standard();
		btnCurrentBorrowing.setBounds(0, 307, 326, 81);
		pnMenu.add(btnCurrentBorrowing);
		
		btnManageBook = new Menu_button("Manage Book");
		btnManageBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				L_manage_book goBack = new L_manage_book();
				dispose();
			}
		});
		btnManageBook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnManageBook.standard();
		btnManageBook.setBackground(new Color(96, 163, 217));
		btnManageBook.setColor_white();
		btnManageBook.setBounds(0, 219, 326, 81);
		pnMenu.add(btnManageBook);
		
		lblRealName = new JLabel("Name");
		lblRealName.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblRealName.setBounds(149, 174, 167, 14);
		pnMenu.add(lblRealName);
		
		lblRealID = new JLabel("ID");
		lblRealID.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblRealID.setBounds(149, 146, 167, 17);
		pnMenu.add(lblRealID);
		
		lblID = new JLabel("ID:");
		lblID.setForeground(new Color(136, 136, 136));
		lblID.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblID.setBounds(85, 146, 54, 14);
		pnMenu.add(lblID);
		
		lblName = new JLabel("Name:");
		lblName.setForeground(new Color(136, 136, 136));
		lblName.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblName.setBounds(85, 174, 54, 14);
		pnMenu.add(lblName);
		
		pnContent = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, pnContent, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, pnContent, 0, SpringLayout.EAST, logo);
		springLayout.putConstraint(SpringLayout.SOUTH, pnContent, 0, SpringLayout.SOUTH, pnMenu);
		springLayout.putConstraint(SpringLayout.EAST, pnContent, 1024, SpringLayout.EAST, logo);
		pnContent.setBackground(new Color(250, 250, 250));
		getContentPane().add(pnContent);
		pnContent.setLayout(null);
		
		btnHistoryBorrowedBook = new Menu_button("Borrowed Book");
		btnHistoryBorrowedBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHistoryBorrowedBook.setBackground(new Color(167, 197, 225));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnHistoryBorrowedBook.setBackground(new Color(191, 215, 237));
			}
			@Override
			public void mousePressed(MouseEvent e) {
			
				L_history_borrowed openHistoryBorrowed = new L_history_borrowed();
				dispose();
				
			}
		});
		btnHistoryBorrowedBook.setBounds(0, 395, 243, 81);
		btnHistoryBorrowedBook.standard();
		btnHistoryBorrowedBook.setVisible(false);
		pnContent.add(btnHistoryBorrowedBook);
		
		btnReturnedBook = new Menu_button("Returned Book");
		btnReturnedBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReturnedBook.setBackground(new Color(167, 197, 225));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				btnReturnedBook.setBackground(new Color(191, 215, 237));
			}
			@Override
			public void mousePressed(MouseEvent e) {
			
				L_history_returned openHistoryReturned = new L_history_returned();
				dispose();
				
			}
		});
		btnReturnedBook.setBounds(0, 476, 243, 81);
		btnReturnedBook.standard();
		btnReturnedBook.setVisible(false);
		pnContent.add(btnReturnedBook);

		lblTitle = new JLabel("Editing Existing Book");
		lblTitle.setFont(new Font("SansSerif", Font.PLAIN, 32));
		lblTitle.setBounds(36, 84, 623, 49);
		pnContent.add(lblTitle);
		
		btnSave = new JButton("Save");
		btnSave.setFocusPainted(false);
		btnSave.setVisible(true);
		btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
	
				lblError.setVisible(false);
				
				if(txtBookName.getText().trim().isEmpty() && 
					CBXbookType.getSelectedItem().toString() == "- Select Book Type -") {
					
					lblError.setText("**Please fill in the blank");
					lblError.setVisible(true);
				}
				else if(txtBookName.getText().trim().isEmpty()) {
					
					lblError.setText("**Please fill in the Book Name");
					lblError.setVisible(true);
					
				}else if(CBXbookType.getSelectedItem().toString() == "- Select Book Type -") {
					
					lblError.setText("**Please select a suitable type for the book");
					lblError.setVisible(true);
					
				}else {
					
					try {
	                    BufferedImage bi = ImageIO.read(selectedImg);
	                    imgPath = "JAVA_book_pics\\" + txtBookID.getText().toString().toLowerCase() + ".jpg";
	                    File outputfile = new File("JAVA_book_pics\\" + txtBookID.getText().toString().toLowerCase() + ".jpg");
	                    ImageIO.write(bi, "jpg", outputfile);
	                }
	                catch (Exception e2){
	                	
	                }
					
					//Update book details in the database
					Update_book saveEdit = new Update_book(id, imgPath , txtBookName.getText().toString(), CBXbookType.getSelectedItem().toString());
					
					dispose();
					Save_success notify = new Save_success();
				}
				
			
				
			}
		});
		btnSave.setBorder(null);
		btnSave.setFont(new Font("SansSerif", Font.PLAIN, 24));
		btnSave.setBounds(480, 625, 411, 59);
		btnSave.setBackground(new Color(0, 59, 115));
		btnSave.setForeground(Color.white);
		pnContent.add(btnSave);
		
		lblAddBook = new JLabel("<HTML><U>Upload image</U></HTML>");
		lblAddBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				lblError.setVisible(false);
				
				//browse image
				JFileChooser browseImg = new JFileChooser();
				
				//Setting for choosing images
				FileNameExtensionFilter filter = new FileNameExtensionFilter ("images (jpg only)","jpg","jpeg");
				browseImg.setAcceptAllFileFilterUsed(false);
				browseImg.setFileFilter(filter);
				int showOpenDialogue = browseImg.showOpenDialog(null);
				
				if(showOpenDialogue == JFileChooser.APPROVE_OPTION) {
					
					selectedImg = browseImg.getSelectedFile();
					selectedImgPath = selectedImg.getAbsolutePath();
					
					ImageIcon img = new ImageIcon (selectedImgPath);
					Image resizeImg = img.getImage().getScaledInstance(ImgUpload.getWidth(), ImgUpload.getHeight(), Image.SCALE_SMOOTH);
					
					ImgUpload.setIcon(new ImageIcon(resizeImg));
					
					//check if store pictures folder exists (if not, create one)
					File folder = new File("JAVA_book_pics");
					boolean create = folder.mkdir();
				}
				
			}
		});
		lblAddBook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAddBook.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblAddBook.setForeground(new Color(0, 59, 115));
		lblAddBook.setBounds(144, 594, 113, 26);
		lblAddBook.setVisible(true);
		pnContent.add(lblAddBook);

		lblError = new JLabel("**Error");
		lblError.setVisible(false);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblError.setBounds(480, 600, 411, 19);
		pnContent.add(lblError);
		
		ImgUpload = new JLabel("");
		ImgUpload.setOpaque(true);
		ImgUpload.setHorizontalAlignment(SwingConstants.CENTER);
		ImgUpload.setFont(new Font("SansSerif", Font.PLAIN, 18));
		ImgUpload.setBackground(new Color(191, 215, 237));
		ImgUpload.setBounds(42, 161, 331, 422);
		pnContent.add(ImgUpload);
		
		txtBookID = new JTextField();
		txtBookID.setFont(new Font("SansSerif", Font.PLAIN, 24));
		txtBookID.setColumns(50);
		txtBookID.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));
		txtBookID.setBackground(new Color(215, 215, 215));
		txtBookID.setForeground(Color.black);
		txtBookID.setEditable(false);
		txtBookID.setBounds(480, 191, 411, 68);
		pnContent.add(txtBookID);
		
		lblBookID = new JLabel("Book ID");
		lblBookID.setForeground(new Color(136, 136, 136));
		lblBookID.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblBookID.setBounds(480, 161, 130, 24);
		pnContent.add(lblBookID);
		
		txtBookName = new JTextField();
		txtBookName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				if(!(Character.isLetterOrDigit(c)) && !(Character.isSpaceChar(c))){
					e.consume();
				}
				
			}
		});
		txtBookName.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblError.setVisible(false);
			}
		});
		txtBookName.setFont(new Font("SansSerif", Font.PLAIN, 24));
		txtBookName.setColumns(50);
		txtBookName.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));
		txtBookName.setBackground(new Color(191, 215, 237));
		txtBookName.setBounds(480, 327, 411, 68);
		pnContent.add(txtBookName);
		
		lblBookName = new JLabel("Book Name");
		lblBookName.setForeground(new Color(136, 136, 136));
		lblBookName.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblBookName.setBounds(480, 297, 130, 24);
		pnContent.add(lblBookName);
		
		lblBookType = new JLabel("Book Type");
		lblBookType.setForeground(new Color(136, 136, 136));
		lblBookType.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblBookType.setBounds(480, 433, 130, 24);
		pnContent.add(lblBookType);
		
		CBXbookType = new JComboBox<>();
		CBXbookType.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblError.setVisible(false);
			}
		});
		CBXbookType.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		CBXbookType.setModel(new DefaultComboBoxModel(new String[] {"- Select Book Type -", "Fiction", "Mystery", "Novel", "Horror", "Fantasy", "Romance"}));
		CBXbookType.setUI(new BasicComboBoxUI() {
		  protected ComboPopup createPopup() {
		        BasicComboPopup basicComboPopup = new BasicComboPopup(CBXbookType);
		        basicComboPopup.setBorder(new LineBorder(new Color(191, 215, 237)));
		        return basicComboPopup;
		    }
		  @Override
          protected JButton createArrowButton() {
			  return new BasicArrowButton(
			            BasicArrowButton.SOUTH,
			            new Color(191, 215, 237),  new Color(191, 215, 237),
			            new Color(0, 59, 115),  new Color(191, 215, 237));
          }
		});
		CBXbookType.setBackground(new Color(191, 215, 237));
		CBXbookType.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));
		CBXbookType.setFocusable(false);
		CBXbookType.setFont(new Font("SansSerif", Font.PLAIN, 24));
		CBXbookType.setBounds(480, 463, 411, 68);
		pnContent.add(CBXbookType);
		
		imgBack = new Set_icon();
		imgBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
				L_manage_book goBack = new L_manage_book();
			}
		});
		imgBack.backIcon();
		pnContent.add(imgBack);

		lblRealID.setText(Librarian.getID());
		lblRealName.setText(Librarian.getName());
		
	}
}