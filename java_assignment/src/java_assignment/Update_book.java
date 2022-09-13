package java_assignment;

import java.io.*;
import java.util.*;

public class Update_book extends Book{

private static Scanner scanner;
	
	//Update status book in database
	public Update_book(String id, String s){	
		
		super(id);
		super.setStatus(s);

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
							super.getStatus()
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
	
	
	//Edit book details in database
	public Update_book(String id, String img , String name ,String type) {
			
			super(id, img , name ,type);

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
								super.getImg() + "," +
								super.getName() + "," +
								super.getType() + "," +
								lineArr[4]
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


