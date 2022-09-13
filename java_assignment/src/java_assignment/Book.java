package java_assignment;

public abstract class Book {

	private String id_book;
	private String image;
	private String name;
	private String type;
	private String status;

	private String filepath = "book.txt";
	
	public Book() {
		id_book = "";
		image = "";
		name = "";
		type = "";
		status = "";
		
	}
	
	public Book(String id, String img, String n, String t, String s) {
		id_book = id;
		image = img;
		name = n;
		type = t;
		status = s;
	}
	
	public Book(String id, String img, String n, String t) {
		id_book = id;
		image = img;
		name = n;
		type = t;
	}
	
	public Book(String id) {
		id_book = id;
		
	}
	
	public Book(String id, String n) {
		id_book = id;
		name = n;
				
	}
	
	public String getID() {
		return id_book;
	}
	
	public String getImg() {
		return image;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getFilePath() {
		return filepath;
	}
	
	public void setID(String id) {
		id_book = id;
	}
	
	public void setImg(String img) {
		image = img;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void setType(String t) {
		type = t;
	}
	
	public void setStatus(String s) {
		status = s;
	}
	
	
}
