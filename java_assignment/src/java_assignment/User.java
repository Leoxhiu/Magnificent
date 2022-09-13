package java_assignment;

public abstract class User {

	private String filepath;
	private static String id;
	private static String name;
	private static String password;
	
	public User(){	
		id = "";
		name = "";
		password = "";
		filepath = "";

	}
	
	public static String getID() {
		return id;
	}
	
	public static String getName() {
		return name;
	}
	
	public static String getPassword() {
		return password;
	}
	
	public abstract String getFilePath();
	
	public static void setID(String i) {
		id = i;
	}
	
	public static void setName(String n) {
		name = n;
	}
	
	public static void setPassword(String p) {
		password = p;
	}
	
}
