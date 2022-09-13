package java_assignment;

public abstract class Borrowed_book extends Book{

	private String borrowDate;
	private String expireDate;
	private String renewDate;
	private int renewChance = 1;
	
	private final String borrowedPath = "borrowed_book.txt";

	public Borrowed_book() {
		borrowDate = "";
		expireDate = "";
		renewDate = "";
	}
	
	public Borrowed_book(String id ,String r, String e, int rc) {
		super.setID(id);
		renewDate = r;
		expireDate = e;
		renewChance = rc;
	}
	
	public String getBorrowDate() {
		return borrowDate;
	}
	
	public String getExpireDate() {
		return expireDate;
	}
	
	public String getRenewDate() {
		return renewDate;
	}
	
	public int getRenewChance() {
		return renewChance;
	}
	
	@Override
	public String getFilePath() {
		return borrowedPath;
	}
	
	public String getBookPath() {
		return super.getFilePath();
	}
	
	public void setBorrowDate(String a) {
		borrowDate = a ;
	}
		
	public void setExpireDate(String b) {
		expireDate = b ;
	}
	
	public void setRenewDate(String r) {
		renewDate = r;
	}
	
	public void setRenewChance(int s) {
		renewChance = s;
	}
}
	
	
	
