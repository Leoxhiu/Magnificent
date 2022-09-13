package java_assignment;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Add_history_borrowed extends History_borrowed_book{

	private static Scanner scanner;
	
	//Add record in history of borrowed book 
		public Add_history_borrowed(String id, String borrowDate) {
			
			super.setID(id);
			super.setBorrowDate(borrowDate);
		
			//Get real image path, book name , book type from borrowed_book.txt
					try (FileReader fr = new FileReader(super.getBorrowedPath())){
						
						scanner = new Scanner (fr);
						String line;
						String[] lineArr;
							
						while((line = scanner.nextLine())!=null) {
							lineArr = line.split(",");
								
							if(lineArr[0].equals(super.getID().toString().toLowerCase())){
									
							super.setImg(lineArr[2].toString()); 
							super.setName(lineArr[3].toString());
							super.setType(lineArr[4].toString());
				
							break;
							}
					}
						scanner.close();
						fr.close();
				
					}
					catch (Exception e) {

					}
					
					//Calculate expire date by adding 14days to borrow date
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Calendar c = Calendar.getInstance();
					
					try {
						c.setTime(sdf.parse(super.getBorrowDate()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					c.add(Calendar.DAY_OF_MONTH, 14);
					
					super.setExpireDate(sdf.format(c.getTime()));
					super.setRenewDate("-");
			
			File f = new File(super.getFilePath());
			
			String newLine = 
					super.getID().toString().toLowerCase() + "," 
					+ Borrower.getID().toString().toLowerCase() + "," 	
					+ super.getImg().toString()+ "," 
					+ super.getName().toString() + "," 
					+ super.getType().toString() + "," 
					+ super.getBorrowDate() + "," 
					+ super.getExpireDate() + ","
					+ super.getRenewDate() + ","
					+ super.getNewExpire();
			
			try {
					PrintWriter pw = new PrintWriter(new FileWriter(f, true));
		            pw.println(newLine);
		            pw.flush();
		            pw.close();
					
		        } 
				catch (Exception e) {
		            System.out.println("Error Occured");
				}
			
		}
	
}
