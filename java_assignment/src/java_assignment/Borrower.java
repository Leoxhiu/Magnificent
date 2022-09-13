package java_assignment;

public abstract class Borrower extends User{

	private final String filepath = "borrower.txt";
	
	public Borrower(){	
		super.setID("");
		super.setName("");
		super.setPassword("");

	}
	
	public Borrower(String idBorrower, String p) {
		
		super.setID(idBorrower);
		super.setPassword(p);
		
	}

	@Override
	public String getFilePath() {
		return filepath;
	}
	
}


