package java_assignment;

public abstract class Librarian extends User{
	
	private final String filepath = "librarian.txt";
	
	public Librarian(){	
		super.setID("");
		super.setName("");
		super.setPassword("");

	}
	
	public Librarian(String idLibrarian, String p) {
		
		super.setID(idLibrarian);
		super.setPassword(p);
		
	}

	@Override
	public String getFilePath() {
		return filepath;
	}
}
