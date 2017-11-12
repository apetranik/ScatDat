package scatalogObjects;

public class Badge {
	private String name;
	private String image;
	
	public Badge(String name, String image) {
		this.name = name;
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		// DATABASE
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
		// DATABASE
	}
	
}
