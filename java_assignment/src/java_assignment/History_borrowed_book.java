package java_assignment;

public abstract class History_borrowed_book extends Borrowed_book{
	
	private String newExpire = "-";
	
	private final String historyBorrowedPath = "history_borrowed_book.txt";
	
	public History_borrowed_book() {
		newExpire = "-";
	}
	
	@Override
	public String getFilePath() {
		return historyBorrowedPath;
	}
	
	public String getBorrowedPath() {
		return super.getFilePath();
	}
	
	public String getNewExpire() {
		return newExpire;
	}

	public void setNewExpire(String ne) {
		newExpire = ne;
	}
	
}
