package Towers;

import javafx.scene.image.Image;

public class Tower6 extends Tower{

	private String url;
	public Tower6(Image img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
	}
	public void setURL(String str) {
		url = str;
	}
	public String getURL() {
		return url;
	}
}
