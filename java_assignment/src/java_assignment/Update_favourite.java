package java_assignment;

import java.io.*;
import java.util.*;

public class Update_favourite extends Favourite{

	private static Scanner scanner;
	
	public Update_favourite(String idB, String newStatus) {
		
		super.setID(idB);
		super.setStatus(newStatus);
		
	
	}
	
	public void updateDatabase() {
		
		ArrayList<String> tempArray = new ArrayList<>();
		
		
		try (FileReader fr = new FileReader(super.getFilePath())){
			scanner = new Scanner (fr);
			String line;
			String[] lineArr;
			
			while((line = scanner.nextLine())!=null) {
				lineArr = line.split(",");
				
				if(lineArr[0].equals(super.getID().toString().toLowerCase())) {
					tempArray.add(
							lineArr[0] + "," + 
							lineArr[1] + "," +
							lineArr[2] + "," +
							lineArr[3] + "," +
							lineArr[4] + "," +
							super.getStatus() + "," +
							lineArr[6]
							);
				}
				else {
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
