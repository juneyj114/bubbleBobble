package bubbleBobble;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bubble extends JLabel{
	
	private int x;
	private int y;

	ImageIcon bubbleIcon;
	Image bubbleImage;

	public Bubble(int x, int y) {
		bubbleIcon = new ImageIcon("image/bubble.png");
		bubbleImage = bubbleIcon.getImage();
		this.x = x;
		this.y = y;

		setIcon(bubbleIcon);
		this.setSize(50, 50);
		this.setLocation(x, y);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
