package java_assignment;

import java.io.FileReader;
import java.util.Scanner;

public class Check_book extends Book{

	private boolean exist = false;
	
	private static Scanner scanner;
	
	//Check if the book existed in the database
	public Check_book(String idBook) {
		
		super(idBook);
		check();
	}
	
	private void check() {
		
		try (FileReader fr = new FileReader(super.getFilePath())){
					
					scanner = new Scanner (fr);
					String line;
					String[] lineArr;
						
					while((line = scanner.nextLine())!=null) {
						lineArr = line.split(",");
							
						if(lineArr[0].equals(super.getID().toString().toLowerCase())){
						
						exist = true;
						
						break;
						}
				}
				fr.close();
			
			}
			catch (Exception e) {
		
			}
			
	}
	
	
	public boolean getExist() {
		return exist;
	}
}
