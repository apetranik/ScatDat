package scatalogObjects;


public class Name {
	private String fname; 
	private String lname;
	
	public Name(String fname, String lname) {
		super();
		this.fname = fname;
		this.lname = lname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
		//// DATABASE ////
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
		//// DATABASE ////
	}
	
	public Name(String fname, String lname) {
		this.fname = fname;
		this.lname = lname;
	}
	
}
