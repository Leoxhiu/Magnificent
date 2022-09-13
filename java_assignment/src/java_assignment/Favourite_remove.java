package java_assignment;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Favourite_remove extends Favourite{

	private static Scanner scanner;
	public Favourite_remove(String idB) {
		
		super.setID(idB);

			ArrayList<String> tempArray = new ArrayList<>();
			
			//Find book with id and delete it
			try (FileReader fr = new FileReader(super.getFilePath())){
				scanner = new Scanner (fr);
				String line;
				String[] lineArr;
				
				while((line = scanner.nextLine())!=null) {
                    lineArr = line.split(",");
                    
                    if(!lineArr[0].equals(super.getID().toString().toLowerCase()) && !lineArr[1].equals(Borrower.getID().toString().toLowerCase())) {      

                            tempArray.add(line);
                    }
                    else if (lineArr[0].equals(super.getID().toString().toLowerCase()) && !lineArr[1].equals(Borrower.getID().toString().toLowerCase())){
                        
                            tempArray.add(line);
                        
                    }
                    else if (!lineArr[0].equals(super.getID().toString().toLowerCase()) && lineArr[1].equals(Borrower.getID().toString().toLowerCase())) {
                        
                            tempArray.add(line);
                        }               
                }
				
				scanner.close();
				fr.close();
				
			}
			
			catch (Exception e){
				
			}
			
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

	
}
