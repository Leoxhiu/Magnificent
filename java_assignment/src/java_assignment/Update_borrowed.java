package java_assignment;

import java.io.*;
import java.util.*;

public class Update_borrowed extends Borrowed_book{

	private static Scanner scanner;
	
	//Update for renew (change expire date, add renew date , change renew chance)
		public Update_borrowed(String id, String rd, String newEx, int rc) {
			
			super(id, rd, newEx, rc);
			updateData();
		}
	
		private void updateData() {
			
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
								super.getExpireDate() + "," +
								super.getRenewChance() + "," +
								lineArr[7] + "," +
								super.getRenewDate()
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
