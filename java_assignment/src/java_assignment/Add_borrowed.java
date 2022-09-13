package java_assignment;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Add_borrowed extends Borrowed_book{

	private static Scanner scanner;
	
	//Add record in borrowed_book, because user wants to borrow the book
		public Add_borrowed(String id, String date) {
			
			super.setID(id);
			super.setBorrowDate(date);
			
			insertData();
		}
		
		private void insertData() {
			
			//Get real image path, book name , book type from book.txt
			try (FileReader fr = new FileReader(super.getBookPath())){
				
				scanner = new Scanner (fr);
				String line;
				String[] lineArr;
					
				while((line = scanner.nextLine())!=null) {
					lineArr = line.split(",");
						
					if(lineArr[0].equals(super.getID().toString().toLowerCase())){
							
					super.setImg(lineArr[1].toString()); 
					super.setName(lineArr[2].toString());
					super.setType(lineArr[3].toString());
		
					break;
					}
			}
				scanner.close();
				fr.close();
		
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null,
					    "Some issues are occured in database, please inform the library counter.",
					    "Database Error",
					    JOptionPane.PLAIN_MESSAGE);
			}

			//Calculate expire date by adding 14days to borrow date
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar c = Calendar.getInstance();
			
			try {
				c.setTime(sdf.parse(super.getBorrowDate()));
			} catch (ParseException e) {
				
				JOptionPane.showMessageDialog(null,
					    "Error occured when calculating expiry date, please inform the library counter.",
					    "Date Error",
					    JOptionPane.PLAIN_MESSAGE);
				
			}
			
			c.add(Calendar.DAY_OF_MONTH, 14);
			
			super.setExpireDate(sdf.format(c.getTime()));
			super.setRenewDate(" ");
			
			//Compile whole record and insert into borrowed_book.txt
			File f = new File(super.getFilePath());
			
			String newLine = 
					super.getID().toString().toLowerCase() + "," 
					+ Borrower.getID().toString().toLowerCase() + "," 	
					+ super.getImg().toString()+ "," 
					+ super.getName().toString() + "," 
					+ super.getType().toString() + "," 
					+ super.getExpireDate() + "," 
					+ super.getRenewChance() + ","
					+ super.getBorrowDate() + ","
					+ super.getRenewDate();
			
			try {
					PrintWriter pw = new PrintWriter(new FileWriter(f, true));
		            pw.println(newLine);
		            pw.flush();
		            pw.close();
					
		        } 
				catch (Exception e) {
					JOptionPane.showMessageDialog(null,
						    "Some issues are occured in database, please inform the library counter.",
						    "Database Error",
						    JOptionPane.PLAIN_MESSAGE);
				}
			
		}
	
}
