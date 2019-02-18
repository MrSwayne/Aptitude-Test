package ie.swayne.test.quiz;

import java.awt.Image;

import javax.swing.ImageIcon;

public class PictureTestQuestion extends TestQuestion {

	protected ImageIcon pic;
	protected String path;
	
	public PictureTestQuestion(String rawString) {
		super(rawString);
		// TODO Auto-generated constructor stub
	}
	
	public ImageIcon getPicture() {
		return this.pic;
	}
	
	public static ImageIcon getScaledImage(ImageIcon img, int w, int h) {
		
		Image temp = img.getImage();
		Image newImg = temp.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newImg);
		return img;
	}
}
