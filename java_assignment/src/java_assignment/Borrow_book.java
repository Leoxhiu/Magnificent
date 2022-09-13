package java_assignment;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.logging.Logger;

import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class Borrow_book extends JFrame{

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
	
	private JButton btnBorrow;
	
	private JLabel lblTitle;
	
	private JPanel pnContent;
	private JTable tblBorrowBook;
	private Table_model model;
	private JLabel imgBook;
	private String search_text;
	
	private SimpleDateFormat timeFormat;
	private String show_time;
	private String show_date;
	
	private Clock_timer timer;
	private Search_bar txtSearchBook;
	private JLabel lblFavourite;
	private JScrollPane scrollPane;
	private JLabel lblTips;

	public Borrow_book() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				   String filepath = "book.txt";
	                File file = new File(filepath);
	                
	                try {
	                    FileReader fr = new FileReader(file);
	                    BufferedReader br = new BufferedReader(fr);
	                    
	                    Object[] lines = br.lines().toArray();
	                    
	                    for(int i = 0; i < lines.length; i++) {
	                        String[] row = new String[4];
	                        row = lines[i].toString().split(",");
	                        
	                    	ImageIcon img[] = {new ImageIcon(row[1].toString())};  	
	                        
	                        for (ImageIcon selected_img : img)
	                        {
	                            imgBook = new JLabel();
	                            Image resized_img = selected_img.getImage().getScaledInstance(235, 300, Image.SCALE_SMOOTH);
	                            imgBook.setIcon(new ImageIcon(resized_img));
	                            model.insertRow(0, new Object[] {row[0].toString(),imgBook, row[2].toString(), row[3].toString(), row[4].toString()});
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
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHistory.setBackground(new Color(167, 197, 225));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				if(btnBorrowedBook.isVisible() && btnReturnedBook.isVisible()) {
					btnHistory.setBackground(new Color(167, 197, 225));
				}
				else {
				btnHistory.setBackground(new Color(191, 215, 237));
				}
			}
		});
		btnHistory.standard();
		btnHistory.setBounds(0, 480, 326, 81);
		pnMenu.add(btnHistory);
		
		btnFavouriteBook = new Menu_button("Favourite Book");
		btnFavouriteBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Favourite_book favouritePage = new Favourite_book();
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
		btnBorrowBook.standard();
		btnBorrowBook.setBackground(new Color(96, 163, 217));
		btnBorrowBook.setColor_white();
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
		btnBorrowedBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBorrowedBook.setBackground(new Color(167, 197, 225));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnBorrowedBook.setBackground(new Color(191, 215, 237));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				History_borrowed openThis = new History_borrowed();
				dispose();
			}
		});
		btnBorrowedBook.setBounds(0, 481, 243, 81);
		btnBorrowedBook.standard();
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
		
		tblBorrowBook = new JTable();
		tblBorrowBook.setBorder(new EmptyBorder(1, 1, 1, 1));
		model = new Table_model();
		Object[] column = {"ID","Preview","Name","Type","Status"};
		model.setColumnIdentifiers(column);
		tblBorrowBook.setModel(model);
		tblBorrowBook.setRowHeight(300);
		tblBorrowBook.setFont(new Font("SansSerif", Font.PLAIN, 18));
		tblBorrowBook.getColumn("Preview").setCellRenderer(new Pic_render());
		TableColumnModel columnModel = tblBorrowBook.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(10);
		columnModel.getColumn(1).setPreferredWidth(180);
		columnModel.getColumn(2).setPreferredWidth(300);
		columnModel.getColumn(3).setPreferredWidth(100);
		columnModel.getColumn(4).setPreferredWidth(100);
		tblBorrowBook.getTableHeader().setResizingAllowed(false);
		tblBorrowBook.getTableHeader().setReorderingAllowed(false);
		tblBorrowBook.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 15));
		tblBorrowBook.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblBorrowBook.setAutoCreateRowSorter(true);
		tblBorrowBook.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				btnBorrow.setVisible(true);
				lblFavourite.setVisible(true);
			}
	    });
		scrollPane.setViewportView(tblBorrowBook);
		
		
		lblTitle = new JLabel("Book List");
		lblTitle.setFont(new Font("SansSerif", Font.PLAIN, 32));
		lblTitle.setBounds(36, 84, 376, 49);
		pnContent.add(lblTitle);
		
		btnBorrow = new JButton("Borrow");
		btnBorrow.setFocusPainted(false);
		btnBorrow.setVisible(false);
		btnBorrow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBorrow.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				if(tblBorrowBook.getValueAt(tblBorrowBook.getSelectedRow(), 4).toString().equals("Borrowed")) {
					Book_is_borrowed open = new Book_is_borrowed();
				}
				else {
	
					dispose();

					//Transfer id book and current date to T&C
					Terms_and_conditions tc = new Terms_and_conditions(tblBorrowBook.getValueAt(tblBorrowBook.getSelectedRow(), 0).toString(), show_date);
					
				}
				
			}
		});
		btnBorrow.setBorder(null);
		btnBorrow.setFont(new Font("SansSerif", Font.PLAIN, 24));
		btnBorrow.setBounds(707, 624, 281, 59);
		btnBorrow.setBackground(new Color(0, 59, 115));
		btnBorrow.setForeground(Color.white);
		pnContent.add(btnBorrow);
		
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
				tblBorrowBook.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search_text));
				
				try {
				tblBorrowBook.setRowSelectionInterval(0, 0);
				}
				catch(Exception e1){
					
				}
			}
		});
		txtSearchBook.setBounds(71, 141, 341, 26);
		pnContent.add(txtSearchBook);
		txtSearchBook.setColumns(30);
		
		lblFavourite = new JLabel("<HTML><U>Set as Favourite</U></HTML>");
		lblFavourite.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				//Set the selected book details with current date and store as favorite book later
				Favourite setFavourite = new Add_favourite(
						tblBorrowBook.getValueAt(tblBorrowBook.getSelectedRow(), 0).toString(),
						tblBorrowBook.getValueAt(tblBorrowBook.getSelectedRow(), 1).toString(),
						tblBorrowBook.getValueAt(tblBorrowBook.getSelectedRow(), 2).toString(),
						tblBorrowBook.getValueAt(tblBorrowBook.getSelectedRow(), 3).toString(),
						tblBorrowBook.getValueAt(tblBorrowBook.getSelectedRow(), 4).toString(),
						show_date
						);

				if(setFavourite.getExist() == true) {
					
					Is_favourite notify1 = new Is_favourite(setFavourite.getPreFavDate().toString());
					
				}
				else {
					
					Favourite_is_set notify2 = new Favourite_is_set();
					
				}
			}
		});
		lblFavourite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblFavourite.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblFavourite.setForeground(new Color(0, 59, 115));
		lblFavourite.setBounds(857, 141, 131, 26);
		lblFavourite.setVisible(false);
		pnContent.add(lblFavourite);
		
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
