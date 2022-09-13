package java_assignment;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;


public class History_borrowed extends JFrame{

	private Paint_logo logo;
	private Set_icon imgSearch;
	private JPanel pnMenu;
	
	private JLabel lblRealName;
	private JLabel lblRealID;
	private JLabel lblID;
	private JLabel lblName; 

	private JLabel lblTime;
	private JLabel lblDate;
	
	private Menu_button btnLogout;
	private Menu_button btnHistory;
	private Menu_button btnFavouriteBook;
	private Menu_button btnCurrentBorrowed;
	private Menu_button btnBorrowBook;
	private Menu_button btnBorrowedBook;
	private Menu_button btnReturnedBook;
	
	private JLabel lblTitle;
	
	private JPanel pnContent;
	private JTable tblHistoryBorrowedBook;
	private Table_model model;
	private JLabel imgBook;
	private String search_text;
	
	private SimpleDateFormat timeFormat;
	private String show_time;
	private String show_date;
	
	private Clock_timer timer;
	private Search_bar txtSearchBook;
	private JScrollPane scrollPane;
	private JLabel lblTips;

	public History_borrowed() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				   String filepath = "history_borrowed_book.txt";
	                File file = new File(filepath);
	                
	                try {
	                    FileReader fr = new FileReader(file);
	                    BufferedReader br = new BufferedReader(fr);
	                    
	                    Object[] lines = br.lines().toArray();
	                    
	                    for(int i = 0; i < lines.length; i++) {
	                        String[] row = new String[8];
	                        row = lines[i].toString().split(",");
	                        
	                      
	                        	ImageIcon img[] = {new ImageIcon(row[2].toString())};  	
		                        
		                        for (ImageIcon selected_img : img)
		                        {
		                            imgBook = new JLabel();
		                            Image resized_img = selected_img.getImage().getScaledInstance(160, 200, Image.SCALE_SMOOTH);
		                            imgBook.setIcon(new ImageIcon(resized_img));
		                            
		                            if(Borrower.getID().toString().toLowerCase().equals(row[1].toString())) {
		                            	model.insertRow(0, new Object[] {
		                            			row[0].toString(),
		                            			imgBook, 
		                            			row[3].toString(),  
		                            			row[5].toString(),
		                            			row[6].toString(),
		                            			row[7].toString(),
		                            			row[8].toString()});
		                            }
		                           
		                        }
	                                    
	                        fr.close();
                            br.close();
	                    }
	                    
	                } 
	                catch (Exception ex) {
	                    Logger.getLogger(filepath);
	                }
			}
		});
	
		setTitle("Magnificent");
		ImageIcon image = new ImageIcon("logo.png");
		setIconImage(image.getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setSize(1366, 768);
		getContentPane().setBackground(new Color(250, 250, 250));
		setResizable(false);
		
		InitGUI();
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

	private void InitGUI() {
	
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
				btnLogout.setBackground(new Color(191, 215, 237));
			}
		});
		btnLogout.standard();
		btnLogout.setBounds(0, 566, 326, 81);
		pnMenu.add(btnLogout);
		
		btnHistory = new Menu_button("History");
		btnHistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				if(btnBorrowedBook.isVisible() && btnReturnedBook.isVisible()) {
					btnBorrowedBook.setVisible(false);
					btnReturnedBook.setVisible(false);
				}
				else {
					btnBorrowedBook.setVisible(true);
					btnReturnedBook.setVisible(true);
				}
				
			}
		});
		btnHistory.standard();
		btnHistory.setBackground(new Color(96, 163, 217));
		btnHistory.setColor_white();
		btnHistory.setBounds(0, 480, 326, 81);
		pnMenu.add(btnHistory);
		
		btnFavouriteBook = new Menu_button("Favourite Book");
		btnFavouriteBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				Favourite_book change = new Favourite_book();
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnFavouriteBook.setBackground(new Color(167, 197, 225));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnFavouriteBook.setBackground(new Color(191, 215, 237));
			}
		});
		btnFavouriteBook.standard();
		btnFavouriteBook.setBounds(0, 394, 326, 81);
		pnMenu.add(btnFavouriteBook);
		
		btnCurrentBorrowed = new Menu_button("Current Borrowed");
		btnCurrentBorrowed.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				Current_borrowed change_page = new Current_borrowed();
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCurrentBorrowed.setBackground(new Color(167, 197, 225));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnCurrentBorrowed.setBackground(new Color(191, 215, 237));
			}
		});
		btnCurrentBorrowed.standard();
		btnCurrentBorrowed.setBounds(0, 307, 326, 81);
		pnMenu.add(btnCurrentBorrowed);
		
		btnBorrowBook = new Menu_button("Borrow Book");
		btnBorrowBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Borrow_book open_this = new Borrow_book();
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBorrowBook.setBackground(new Color(167, 197, 225));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnBorrowBook.setBackground(new Color(191, 215, 237));
			}
		});
		btnBorrowBook.standard();
		btnBorrowBook.setBounds(0, 219, 326, 81);
		pnMenu.add(btnBorrowBook);
		
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
		
		btnBorrowedBook = new Menu_button("Borrowed Book");
		btnBorrowedBook.setBounds(0, 481, 243, 81);
		btnBorrowedBook.standard();
		btnBorrowedBook.setBackground(new Color(96, 163, 217));
		btnBorrowedBook.setColor_white();
		btnBorrowedBook.setVisible(false);
		pnContent.add(btnBorrowedBook);
		
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
				History_returned historyReturned = new History_returned();
				dispose();
			}
		});
		btnReturnedBook.setBounds(0, 562, 243, 81);
		btnReturnedBook.standard();
		btnReturnedBook.setVisible(false);
		pnContent.add(btnReturnedBook);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 178, 952, 416);
		pnContent.add(scrollPane);
		
		tblHistoryBorrowedBook = new JTable();
		tblHistoryBorrowedBook.setBorder(new EmptyBorder(1, 1, 1, 1));
		model = new Table_model();
		Object[] column = {"ID","Preview","Name", "Borrow Date", "Initial Expiry Date" , "Renew Date", "Renewed Expiry Date" };
		Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
		tblHistoryBorrowedBook.setModel(model);
		tblHistoryBorrowedBook.setRowHeight(200);
		tblHistoryBorrowedBook.setFont(new Font("SansSerif", Font.PLAIN, 18));
		tblHistoryBorrowedBook.getColumn("Preview").setCellRenderer(new Pic_render());
		TableColumnModel columnModel = tblHistoryBorrowedBook.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(30);
		columnModel.getColumn(1).setPreferredWidth(133);
		columnModel.getColumn(2).setPreferredWidth(150);
		columnModel.getColumn(3).setPreferredWidth(100);
		columnModel.getColumn(4).setPreferredWidth(100);
		columnModel.getColumn(5).setPreferredWidth(100);
		columnModel.getColumn(6).setPreferredWidth(130);
		tblHistoryBorrowedBook.getTableHeader().setResizingAllowed(false);
		tblHistoryBorrowedBook.getTableHeader().setReorderingAllowed(false);
		tblHistoryBorrowedBook.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 15));
		tblHistoryBorrowedBook.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblHistoryBorrowedBook.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(tblHistoryBorrowedBook);
		
		
		lblTitle = new JLabel("History - Borrowed Book");
		lblTitle.setFont(new Font("SansSerif", Font.PLAIN, 32));
		lblTitle.setBounds(36, 84, 376, 49);
		pnContent.add(lblTitle);
			
		txtSearchBook = new Search_bar();
		txtSearchBook.standard();
		txtSearchBook.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtSearchBook.getText().equals("Search")) {
					txtSearchBook.setText("");
					txtSearchBook.setForeground(Color.black);
					txtSearchBook.borderColor_black();
					imgSearch.black_searchIcon();
					lblTips.setVisible(true);
					
				}
				else {
					txtSearchBook.setForeground(Color.black);
					txtSearchBook.borderColor_black();
					imgSearch.black_searchIcon();
					lblTips.setVisible(true);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (txtSearchBook.getText().equals("")) {
					txtSearchBook.setText("Search");
					txtSearchBook.setForeground(new Color(116, 116, 116));
					txtSearchBook.borderColor_standard();
					imgSearch.standard_searchIcon();
					lblTips.setVisible(false);
				}
				else {
					txtSearchBook.setForeground(new Color(116, 116, 116));
					txtSearchBook.borderColor_standard();
					imgSearch.standard_searchIcon();
					lblTips.setVisible(false);
				}
			}
		});
		
		txtSearchBook.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search_text = txtSearchBook.getText();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
				tblHistoryBorrowedBook.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search_text));
				
				try {
				tblHistoryBorrowedBook.setRowSelectionInterval(0, 0);
				}
				catch(Exception e1){
					
				}
			}
		});
		txtSearchBook.setBounds(71, 141, 341, 26);
		pnContent.add(txtSearchBook);
		txtSearchBook.setColumns(30);
		
		imgSearch = new Set_icon();
		imgSearch.searchIcon();
		pnContent.add(imgSearch);
		
		lblTips = new JLabel("**Case Sensitive");
		lblTips.setVisible(false);
		lblTips.setForeground(Color.RED);
		lblTips.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblTips.setBounds(422, 148, 122, 19);
		pnContent.add(lblTips);
		
		
		lblRealID.setText(Borrower.getID());
		lblRealName.setText(Borrower.getName());
		
	}

}
