package scatalogObjects;

public class Badge {
	private String name;
	private String image;
	private int preferredRatingStyle;

	public Badge(String name, String image, int preferredRatingStyle) {
		this.name = name;
		this.image = image;
		this.preferredRatingStyle = preferredRatingStyle;
	}
	public Badge(int preferredRatingStyle) {
		if(preferredRatingStyle == 0) {
			this.name = "try-hard";
			this.image = "insert image";
		}
		else if(preferredRatingStyle == 1) {
			this.name = "?";
			this.image = "lskdjfdls";
		}
		else if(preferredRatingStyle == 2) {
			this.name = "slkdjf";
			this.image = "sldkfj";
		}
		else {
			this.name = "sldkfj";
			this.image = "lskdjf";
		}
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
	public int getPreferredRatingStyle() {
		return preferredRatingStyle;
	}
	public void setPreferredRatingStyle(int preferredRatingStyle) {
		this.preferredRatingStyle = preferredRatingStyle;
	}	
}
