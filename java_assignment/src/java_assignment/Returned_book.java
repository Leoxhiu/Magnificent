package java_assignment;

import java.io.*;
import java.util.*;

public class Returned_book extends Book{

	private String return_borrowDate;
	private String returnDate;
	private String overdue;
	private final String ReB_path = "returned_book.txt";
	
	private static Scanner scanner;
	
	public Returned_book(String idUser, String idBook, String borrowDate, String currentDate , String over){
		
		super(idBook);

		return_borrowDate = borrowDate;
		returnDate = currentDate;
		overdue = over;	
		
		insertData(idUser);
	}
	
	private void insertData(String idUser) {
		
		try (FileReader fr = new FileReader(super.getFilePath())){
					
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
				fr.close();
			
			}
			catch (Exception e) {
		
			}
				
				File f = new File(ReB_path);
				
				String newLine = 
						super.getID().toString().toLowerCase() + "," 
						+ idUser.toLowerCase() + "," 
						+ super.getImg() + "," 
						+ super.getName() + "," 
						+ super.getType() + "," 
						+ return_borrowDate + ","
						+ returnDate + "," +
						overdue;
				
			try {
					
				PrintWriter pw = new PrintWriter(new FileWriter(f, true));
			    pw.println(newLine);
			    pw.close();	       	
						
			    } 
				catch (Exception e) {
					System.out.println("Error Occured");
				}
				
			}
	
}
