package java_assignment;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Delete_book extends Book{

private static Scanner scanner;
	
	//Delete book from book.txt
		public Delete_book(String id) {
		
			super(id);
			delete();
		}
		
		private void delete () {
			
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
