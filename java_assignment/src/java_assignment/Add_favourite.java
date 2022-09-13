package java_assignment;

import java.io.*;
import java.util.*;

public class Add_favourite extends Favourite{

	private static Scanner scanner;
	
	//From Borrow_book page
	public Add_favourite(String id, String img, String n, String t, String s, String favourite_date) {
		
		super(id, img, n, t, s);
		super.setFavDate(favourite_date);
		
		try (FileReader fr = new FileReader(super.getBookPath())){
			
				scanner = new Scanner (fr);
				String line;
				String[] lineArr;
					
				while((line = scanner.nextLine())!=null) {
					lineArr = line.split(",");
						
					if(lineArr[0].equals(super.getID().toString().toLowerCase())){
							
					super.setImg(lineArr[1].toString());
					super.setStatus(lineArr[4].toString());
					break;
					}
			}
			fr.close();
		
		}
		catch (Exception e) {

		}
	
		File f = new File(super.getFilePath());
		
		String newLine = 
				super.getID().toString().toLowerCase() + "," 
				+ Borrower.getID().toString().toLowerCase() + "," 
				+ super.getImg() + "," 
				+ super.getName() + "," 
				+ super.getType() + "," 
				+ super.getStatus() + ","
				+ super.getFavDate();
		
		
		try (FileReader fr = new FileReader(super.getFilePath())){
			scanner = new Scanner (fr);
			String line;
			String[] lineArr;
			
			while((line = scanner.nextLine())!=null) {
				lineArr = line.split(",");
				
				if(super.getID().toString().toLowerCase().equals(lineArr[0].toString()) && Borrower.getID().toString().toLowerCase().equals(lineArr[1].toString())){
					
					super.setPreFavDate(lineArr[6].toString());
					super.setExist(true);
					break;
					
				}
				else {
					
					super.setExist(false);
		
			        }
					
				}
			scanner.close();
			fr.close();
			
			
			

		}catch (Exception e) {
			
		}
		
		try {
			if (super.getExist() == false) {
				PrintWriter pw = new PrintWriter(new FileWriter(f, true));
	            pw.println(newLine);
	            pw.close();
				}
	        } 
			catch (Exception e) {
	            System.out.println("Error Occured");
			}
		
		
	}
	
	
	//From Current_borrowed page
	public Add_favourite(String id, String name, String favourite_date) {
		
		super(id, name);
		super.setFavDate(favourite_date);
		
		//Get real image path, book type from book.txt
		try (FileReader fr = new FileReader(super.getBookPath())){
					
			scanner = new Scanner (fr);
			String line;
			String[] lineArr;
						
			while((line = scanner.nextLine())!=null) {
					lineArr = line.split(",");
							
					if(lineArr[0].equals(super.getID().toString().toLowerCase())){
								
					super.setImg(lineArr[1].toString()); 
					super.setType(lineArr[3].toString());
					super.setStatus(lineArr[4].toString());
			
					break;
					}
				}
					scanner.close();
					fr.close();
			
				}
				catch (Exception e) {

				}
		
		File f = new File(super.getFilePath());
		
		String newLine = 
				super.getID().toString().toLowerCase() + "," 
				+ Borrower.getID().toString().toLowerCase() + "," 
				+ super.getImg().toString() + "," 
				+ super.getName().toString() + "," 
				+ super.getType().toString() + "," 
				+ super.getStatus() + ","
				+ super.getFavDate();
		
		
		try (FileReader fr = new FileReader(super.getFilePath())){
			scanner = new Scanner (fr);
			String line;
			String[] lineArr;
			
			while((line = scanner.nextLine())!=null) {
				lineArr = line.split(",");
				
				if(super.getID().toString().toLowerCase().equals(lineArr[0].toString()) && Borrower.getID().toString().toLowerCase().equals(lineArr[1].toString())){
					
					super.setPreFavDate( lineArr[6].toString());
					super.setExist(true);
					break;
					
				}
				else {
					
					super.setExist(false);
		
			        }
					
				}
			scanner.close();
			fr.close();
			
			
			

		}catch (Exception e) {
			
		}
		
		try {
			if (super.getExist() == false) {
				PrintWriter pw = new PrintWriter(new FileWriter(f, true));
	            pw.println(newLine);
	            pw.close();
				}
	        } 
			catch (Exception e) {
	            System.out.println("Error Occured");
			}
		
	}
	
}
