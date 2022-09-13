package java_assignment;

import java.io.*;
import java.util.*;

public class Update_history_borrowed extends History_borrowed_book{

	private static Scanner scanner;
	
	//Update renew date and new expire date
		public Update_history_borrowed (String idUser ,String idBook, String borrowDate , String renewDate, String newExpireDate) {

			super.setID(idBook);
			super.setBorrowDate(borrowDate);
			super.setRenewDate(renewDate);
			super.setNewExpire(newExpireDate);
			
			updateDatabase(idUser);
		}
		
		private void updateDatabase(String idUser) {
			
			ArrayList<String> tempArray = new ArrayList<>();
			
			try (FileReader fr = new FileReader(super.getFilePath())){
				scanner = new Scanner (fr);
				String line;
				String[] lineArr;
				
				while((line = scanner.nextLine())!=null) {
					lineArr = line.split(",");
					
					if(lineArr[0].equals(super.getID()) && lineArr[1].equals(idUser.toString().toLowerCase()) && 
						lineArr[5].equals(super.getBorrowDate())) {
						tempArray.add(
								lineArr[0] + "," + 
								lineArr[1] + "," +
								lineArr[2] + "," +
								lineArr[3] + "," +
								lineArr[4] + "," +
								lineArr[5] + "," +
								lineArr[6] + "," +
								super.getRenewDate() + "," +
								super.getNewExpire()
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
