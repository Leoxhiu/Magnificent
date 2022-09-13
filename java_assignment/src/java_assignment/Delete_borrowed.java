package java_assignment;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Delete_borrowed extends Borrowed_book{

	private static Scanner scanner;
	
	//Delete record from borrowed_book (because user wants to return the book)
		public Delete_borrowed(String id) {
		
			super.setID(id);
		    
			ArrayList<String> tempArray = new ArrayList<>();
			
			//Find book with id and delete it
			try (FileReader fr = new FileReader(super.getFilePath())){
				scanner = new Scanner (fr);
				String line;
				String[] lineArr;
				
				while((line = scanner.nextLine())!=null) {
					lineArr = line.split(",");
					
					if(!lineArr[0].equals(super.getID().toString().toLowerCase())) {

						tempArray.add(line);
					}
				}
				scanner.close();
				fr.close();
				
			}
			
			catch (Exception e){
				
			}
			
			try {
				try(PrintWriter pr = new PrintWriter(super.getFilePath())){
					for (String str : tempArray) {
						pr.println(str);
					}
					
					pr.flush();
					pr.close();
					
				}
				
				catch(Exception e) {
					
				}
			}
			catch (Exception e) {
				
			}
			
		}
	
}
