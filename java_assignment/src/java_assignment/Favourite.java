package java_assignment;

public abstract class Favourite extends Book{

	private String favDate;
	private String pre_favDate;
	private final String FavB_path = "favourite_book.txt";
	
	private boolean exist;
	
	public Favourite() {
		
		pre_favDate = "";
		
	}
	
	
	public Favourite(String id, String img, String n, String t, String s) {
		
		super.setID(id);
		super.setImg(img);
		super.setName(n);
		super.setType(t);
		super.setStatus(s);
		
	}
	
	public Favourite(String id, String n) {
		
		super.setID(id);
		super.setName(n);
		
	}

	public String getBookPath() {
		return super.getFilePath();
	}
	
	@Override
	public String getFilePath() {
		return FavB_path;
	}
	
	public boolean getExist() {
		return exist;
	}
	
	public String getFavDate() {
		return favDate;
	}
	
	public String getPreFavDate() {
		return pre_favDate;
	}
	
	public void setExist(boolean t) {
		exist = t;
	}
	
	public void setFavDate(String d) {
		favDate = d;
	}
	
	public void setPreFavDate(String e) {
		pre_favDate = e;
	}
}
