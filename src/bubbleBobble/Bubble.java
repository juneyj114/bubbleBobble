package bubbleBobble;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bubble extends JLabel{
	
	private int x;
	private int y;

	ImageIcon bubbleIcon;
	Image bubbleImage;
	ImageIcon bubbled = new ImageIcon("image/bubbled.png");
	
	public Bubble(int x, int y) {
		bubbleIcon = new ImageIcon("image/bubble.png");
		bubbleImage = bubbleIcon.getImage();
		this.x = x;
		this.y = y;

		setIcon(bubbleIcon);
		this.setSize(50, 50);
		this.setLocation(x, y);
	}
	
	public void hold(Zhen zhen) {
		zhen.isBubbed = true;
		moveBubble(zhen);
	}
	
	public void moveBubble(Zhen zhen) {
		try {
			for (int i = 0; i < 400; i++) {
				zhen.setIcon(bubbled);
				zhen.setY(zhen.getY() - 1);
				zhen.setLocation(zhen.getX(), zhen.getY());
				Thread.sleep(10);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
