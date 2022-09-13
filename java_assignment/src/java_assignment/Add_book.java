package java_assignment;

import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class Add_book extends Book{

	public Add_book(String id, String path, String n, String t, String s) {
		
		super(id, path, n, t, s);
		insertData();
		
	}
	
	private void insertData() {
		String newLine = 
				super.getID().toString().toLowerCase() + "," 
				+ super.getImg().toString() + ","
				+ super.getName().toString() + ","
				+ super.getType().toString() + ","
				+ super.getStatus().toString();
		
		
		try {		
				PrintWriter pw = new PrintWriter(new FileWriter(super.getFilePath(), true));
	            pw.println(newLine);
	            pw.close();
			
	        } 
			catch (Exception e) {
				JOptionPane.showMessageDialog(null,
					    "Some issues are occured in database, please inform the library counter.",
					    "Database Error",
					    JOptionPane.PLAIN_MESSAGE);
			}
	}
	
}
