package java_assignment;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.table.*;

public class L_current_borrowing extends JFrame{

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
	private Menu_button btnCurrentBorrowing;
	private Menu_button btnManageBook;
	private Menu_button btnHistoryBorrowedBook;
	private Menu_button btnReturnedBook;
	
	private JButton btnReturn;
	private JButton btnRenew;
	
	private JLabel lblTitle;
	
	private JPanel pnContent;
	private JTable tblBorrowedBook;
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
	
	public L_current_borrowing() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				   String filepath = "borrowed_book.txt";
	                File file = new File(filepath);
	                
	                try {
	                    FileReader fr = new FileReader(file);
	                    BufferedReader br = new BufferedReader(fr);
	                    
	                    Object[] lines = br.lines().toArray();
	                    
	                    for(int i = 0; i < lines.length; i++) {
	                        String[] row = new String[7];
	                        row = lines[i].toString().split(",");
	                        
	                      
	                        	ImageIcon img[] = {new ImageIcon(row[2].toString())};  	
		                        
		                        for (ImageIcon selected_img : img)
		                        {
		                            imgBook = new JLabel();
		                            Image resized_img = selected_img.getImage().getScaledInstance(160, 200, Image.SCALE_SMOOTH);
		                            imgBook.setIcon(new ImageIcon(resized_img));
		                            
		                            	model.insertRow(0, new Object[] {
		                            			row[1].toString().toUpperCase(),
		                            			row[0].toString(),
		                            			imgBook,
		                            			row[3].toString(),   
		                            			row[7].toString(), 
		                            			row[5].toString(), 
		                            			row[6].toString()});
		                            
		                           
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
		btnCurrentBorrowing.standard();
		btnCurrentBorrowing.setBackground(new Color(96, 163, 217));
		btnCurrentBorrowing.setColor_white();
		btnCurrentBorrowing.setBounds(0, 307, 326, 81);
		pnMenu.add(btnCurrentBorrowing);
		
		btnManageBook = new Menu_button("Manage Book");
		btnManageBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			
			L_manage_book openManage = new L_manage_book();
			dispose();
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnManageBook.setBackground(new Color(167, 197, 225));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnManageBook.setBackground(new Color(191, 215, 237));
			}
		});
		btnManageBook.standard();
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
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 178, 952, 416);
		pnContent.add(scrollPane);
		
		tblBorrowedBook = new JTable();
		tblBorrowedBook.setBorder(new EmptyBorder(1, 1, 1, 1));
		model = new Table_model();
		Object[] column = { "ID Borrower", "ID Book","Preview","Name", "Borrow Date", "Expiry Date" , "Renew chance"};
		Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
		tblBorrowedBook.setModel(model);
		tblBorrowedBook.setRowHeight(200);
		tblBorrowedBook.setFont(new Font("SansSerif", Font.PLAIN, 18));
		tblBorrowedBook.getColumn("Preview").setCellRenderer(new Pic_render());
		TableColumnModel columnModel = tblBorrowedBook.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(20);
		columnModel.getColumn(1).setPreferredWidth(20);
		columnModel.getColumn(2).setPreferredWidth(80);
		columnModel.getColumn(3).setPreferredWidth(140);
		columnModel.getColumn(4).setPreferredWidth(40);
		columnModel.getColumn(5).setPreferredWidth(40);
		columnModel.getColumn(6).setPreferredWidth(50);
		tblBorrowedBook.getTableHeader().setResizingAllowed(false);
		tblBorrowedBook.getTableHeader().setReorderingAllowed(false);
		tblBorrowedBook.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 15));
		tblBorrowedBook.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblBorrowedBook.setAutoCreateRowSorter(true);
		tblBorrowedBook.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				btnReturn.setVisible(true);
				btnRenew.setVisible(true);
			}
	    });
		scrollPane.setViewportView(tblBorrowedBook);
		
		lblTitle = new JLabel("Current Borrowing Book");
		lblTitle.setFont(new Font("SansSerif", Font.PLAIN, 32));
		lblTitle.setBounds(36, 84, 376, 49);
		pnContent.add(lblTitle);
		
		btnReturn = new JButton("Return");
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				
				String currentDate = show_date;
				String borrowDate = tblBorrowedBook.getValueAt(tblBorrowedBook.getSelectedRow(), 4).toString();
				String expireDate = tblBorrowedBook.getValueAt(tblBorrowedBook.getSelectedRow(), 5).toString();
				
				LocalDate date1 = LocalDate.parse(currentDate, dtf);
				LocalDate date2 = LocalDate.parse(expireDate, dtf);
				
				int duration = (int)ChronoUnit.DAYS.between(date1, date2);
				
				if (duration < 0) {
					duration *= -1;
					
					String idUser = tblBorrowedBook.getValueAt(tblBorrowedBook.getSelectedRow(), 0).toString().toLowerCase();
					String idBook = tblBorrowedBook.getValueAt(tblBorrowedBook.getSelectedRow(), 1).toString();
					String overdue = String.valueOf(duration);
					dispose();
					Overdue_return isOverdue = new Overdue_return(idUser, idBook, borrowDate, expireDate, currentDate, overdue);
					
					
				}
				else {
					
					String idUser = tblBorrowedBook.getValueAt(tblBorrowedBook.getSelectedRow(), 0).toString().toLowerCase();
					String idBook = tblBorrowedBook.getValueAt(tblBorrowedBook.getSelectedRow(), 1).toString();
					String bookName = tblBorrowedBook.getValueAt(tblBorrowedBook.getSelectedRow(), 3).toString();
					
					dispose();
					
					Return_confirm openThis = new Return_confirm(idUser, idBook, bookName, borrowDate, currentDate);
	
				}
				
				
			}
		});
		btnReturn.setFocusPainted(false);
		btnReturn.setVisible(false);
		btnReturn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReturn.setBorder(null);
		btnReturn.setFont(new Font("SansSerif", Font.PLAIN, 24));
		btnReturn.setBounds(707, 624, 281, 59);
		btnReturn.setBackground(new Color(0, 59, 115));
		btnReturn.setForeground(Color.white);
		pnContent.add(btnReturn);
		
		btnRenew = new JButton("Renew");
		btnRenew.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				int chance =  Integer.parseInt(tblBorrowedBook.getValueAt(tblBorrowedBook.getSelectedRow(), 6).toString());
				
				//check if user still have renew chance for the book
				if(chance == 0) {
					
					Renew_used noMoreRenew = new Renew_used();
					
				}
				else {
					
					//if still have chance, check if the book is overdue
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					
					String currentDate = show_date;
					String borrowDate = tblBorrowedBook.getValueAt(tblBorrowedBook.getSelectedRow(), 4).toString();
					String expireDate = tblBorrowedBook.getValueAt(tblBorrowedBook.getSelectedRow(), 5).toString();
					
					LocalDate date1 = LocalDate.parse(currentDate, dtf);
					LocalDate date2 = LocalDate.parse(expireDate, dtf);
					
					int duration = (int)ChronoUnit.DAYS.between(date1, date2);
					
					if(!(duration <0)) {
						
						String idUser = tblBorrowedBook.getValueAt(tblBorrowedBook.getSelectedRow(), 0).toString().toLowerCase();
						String idBook = tblBorrowedBook.getValueAt(tblBorrowedBook.getSelectedRow(), 1).toString();
						String bookName = tblBorrowedBook.getValueAt(tblBorrowedBook.getSelectedRow(), 3).toString();
						String newChance = "0";
						String renewDate = show_date;
						String oldExpire = tblBorrowedBook.getValueAt(tblBorrowedBook.getSelectedRow(), 5).toString();
						
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						Calendar c = Calendar.getInstance();
						
						try {
							c.setTime(sdf.parse(oldExpire));
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						c.add(Calendar.DAY_OF_MONTH, 3);
						
						String newExpire = sdf.format(c.getTime()); 
						
						dispose();
						Renew_confirm confirmation = new Renew_confirm(idUser ,idBook, bookName ,renewDate, newExpire, newChance , borrowDate);
						
					}
					else {
						duration *= -1;
						
						String idUser = tblBorrowedBook.getValueAt(tblBorrowedBook.getSelectedRow(), 0).toString().toLowerCase();
						String idBook = tblBorrowedBook.getValueAt(tblBorrowedBook.getSelectedRow(), 1).toString();
						String overdue = String.valueOf(duration);
						dispose();
						Overdue_return isOverdue = new Overdue_return(idUser, idBook, borrowDate, expireDate, currentDate, overdue);
					}				
				}
				
			}
		});
		btnRenew.setFocusPainted(false);
		btnRenew.setVisible(false);
		btnRenew.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRenew.setBorder(null);
		btnRenew.setFont(new Font("SansSerif", Font.PLAIN, 24));
		btnRenew.setBounds(391, 624, 281, 59);
		btnRenew.setBackground(new Color(191, 215, 237));
		btnRenew.setForeground(Color.white);
		pnContent.add(btnRenew);
		
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
				tblBorrowedBook.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search_text));
				
				try {
					tblBorrowedBook.setRowSelectionInterval(0, 0);
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
		
		
		lblRealID.setText(Librarian.getID());
		lblRealName.setText(Librarian.getName());
	}

}