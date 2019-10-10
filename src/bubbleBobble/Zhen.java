package bubbleBobble;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Zhen extends JLabel implements Runnable {
	private int x = 480;
	private int y = 178;

	Boolean left = false;
	Boolean right = false;
	Boolean down = false;
	Boolean jump = false;

	Stage1 stage1;
	Random random;

	ImageIcon zhenR; // 오른쪽 이미지
	ImageIcon zhenL; // 왼쪽 이미지

	public Zhen(Stage1 stage1) {
		zhenR = new ImageIcon("image/zhenR.png");
		zhenL = new ImageIcon("image/zhenL.png");
		random = new Random();
		setIcon(zhenL);
		this.setSize(50, 50);
		this.setLocation(x, y);
		this.stage1 = stage1;
		Thread move = new Thread(this);
		move.start();
		Thread jth = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					while(true) {
						Thread.sleep(3000);
						jump = true;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		jth.start();
	}
	
	
	
	
	public void timeout() {

	}

	public void held() {
//		this.setIcon("갇힌 아이콘")
	}
	
	public void left() {
		if (stage1.cal.ZdownCheck(this)) {
			this.setY(this.getY() + 2);
		} else {
			this.setX(this.getX() - 2);
		}
		this.setLocation(this.getX(), this.getY());
	}
	
	public void right() {
		if (stage1.cal.ZdownCheck(this)) {
			this.setY(this.getY() + 2);
		} else {
			this.setX(this.getX() + 2);
		}
		this.setLocation(this.getX(), this.getY());
	}
	
	public void leftJump() {
		for (int i = 0; i < 40; i++) {
			this.setX(this.getX() - 1);
			this.setY(this.getY() - 3);
			this.setLocation(this.getX(), this.getY());
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		for (int i = 0; i < 40; i++) {
			this.setX(this.getX() - 1);
			this.setY(this.getY() + 3);
			this.setLocation(this.getX(), this.getY());
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		jump = false;
	}
	
	public void rightJump() {
		for (int i = 0; i < 40; i++) {
			this.setX(this.getX() + 1);
			this.setY(this.getY() - 3);
			this.setLocation(this.getX(), this.getY());
			repaint();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 0; i < 40; i++) {
			this.setX(this.getX() + 1);
			this.setY(this.getY() + 3);
			this.setLocation(this.getX(), this.getY());
			repaint();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		jump = false;
	}

	public void run() {
		while (true) {
			try {
				if (random.nextInt(2) == 0) {
					while (stage1.cal.ZwallCheck(this) != 1) {
						left();
						Thread.sleep(2);
						if(jump && stage1.cal.ZdownCheck(this) == false) {
							leftJump();
						}
					}
					while (stage1.cal.ZwallCheck(this) != 2) {
						right();
						Thread.sleep(2);
						if(jump && stage1.cal.ZdownCheck(this) == false) {
							rightJump();
						}
					}
				} else {
					while (stage1.cal.ZwallCheck(this) != 2) {
						right();
						Thread.sleep(2);
						if(jump && stage1.cal.ZdownCheck(this) == false) {
							rightJump();
						}
						
					}
					while (stage1.cal.ZwallCheck(this) != 1) {
						left();
						Thread.sleep(2);
						if(jump && stage1.cal.ZdownCheck(this) == false) {
							leftJump();
						}
					}
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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