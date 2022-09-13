package java_assignment;

import java.io.*;
import java.util.Scanner;

public class B_verify_login extends Borrower{
	
	private boolean found;
	
	private static Scanner scanner;
	
	public B_verify_login(String id, String p){	
		
		super(id, p);
		
		found = false;
		
		String tempID = "";
		String tempName = "";
		String tempPassword = "";
		
		try {
			scanner = new Scanner (new File (super.getFilePath()));
			scanner.useDelimiter("[,\n]");
			
			while(scanner.hasNext() && !found) {
				
				tempID = scanner.next();
				tempName = scanner.next();
				tempPassword = scanner.next();
				
				if(tempID.trim().equals(super.getID().trim()) && tempPassword.trim().equals(super.getPassword().trim())) {
					found = true;
					
					super.setID(tempID.trim().toUpperCase());
					super.setName(tempName.trim().toUpperCase());
					super.setPassword(tempPassword.trim());
					
				}
				else {
					found = false;
				}
				
			}
			scanner.close();
			
			
		} catch (Exception e) {
			found = false;
		}
		
	}
	
	public boolean getFound() {
		return found;
	}
	
}
