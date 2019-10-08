package bubbleBobble;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Runnable {
	private int x = 0; // 시작위치 x좌표
	private int y = 500; // 시작위치 y좌표

	private int direct = 2; // 1은 왼쪽, 2는 오른쪽을 보고 있는 상태

	Boolean left = false;
	Boolean right = false;
	Boolean up = false;

	ImageIcon playerR; // 오른쪽 이미지
	ImageIcon playerL; // 왼쪽 이미지

	public Player() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
		setIcon(playerR);
		this.setSize(50, 50);
		this.setLocation(x, y);
		move();

	}

	public void move() {
		Thread th = new Thread(this);
		th.start();
	}

	public void dead() {
		remove(this);
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
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

	@Override
	public void run() {
		while (true) {
			while (this.left) {
				try {
					if (this.left && this.up) {
						for (int i = 0; i < 100; i++) {
							this.setX(this.getX() - 1);
							this.setY(this.getY() - 1);
							this.setLocation(this.getX(), this.getY());
							repaint();
							Thread.sleep(5);
						}
						
						for (int i = 0; i < 100; i++) {
							this.setX(this.getX() - 1);
							this.setY(this.getY() + 1);
							this.setLocation(this.getX(), this.getY());
							repaint();
							Thread.sleep(5);
						}

					}
					this.setIcon(this.playerL);
					this.setX(this.getX() - 1);
					this.setLocation(this.getX(), this.getY());
					repaint();

					Thread.sleep(5);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			while (this.right) {
				try {
					if (this.right && this.up) {
						for (int i = 0; i < 100; i++) {
							this.setX(this.getX() + 1);
							this.setY(this.getY() - 1);
							this.setLocation(this.getX(), this.getY());
							repaint();
							Thread.sleep(5);
						}
						
						for (int i = 0; i < 100; i++) {
							this.setX(this.getX() + 1);
							this.setY(this.getY() + 1);
							this.setLocation(this.getX(), this.getY());
							repaint();
							Thread.sleep(5);
						}
						
					}
					this.setIcon(this.playerR);
					this.setX(this.getX() + 1);
					this.setLocation(this.getX(), this.getY());
					repaint();
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			while (this.up) {
				for (int i = 0; i < 100; i++) {
					try {
						this.setY(this.getY() - 1);
						this.setLocation(this.getX(), this.getY());
						repaint();
						Thread.sleep(5);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}

				// 상승 끝 하강 시작
				for (int i = 0; i < 100; i++) {
					try {
						this.setY(this.getY() + 1);
						this.setLocation(this.getX(), this.getY());
						repaint();
						Thread.sleep(5);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
