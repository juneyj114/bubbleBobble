package bubbleBobble;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Runnable {
	private int x = 55; // ������ġ x��ǥ
	private int y = 535; // ������ġ y��ǥ

	private int direct = 2; // 1�� ����, 2�� �������� ���� �ִ� ����

	Boolean left = false;
	Boolean right = false;
	Boolean up = false;
	Boolean down = false;

	ImageIcon playerR; // ������ �̹���
	ImageIcon playerL; // ���� �̹���

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
						for (int i = 0; i < 40; i++) {
							this.setX(this.getX() - 1);
							this.setY(this.getY() - 3);
							this.setLocation(this.getX(), this.getY());
							repaint();
							Thread.sleep(15);
						}

						for (int i = 0; i < 40; i++) {
							this.setX(this.getX() - 1);
							this.setY(this.getY() + 3);
							this.setLocation(this.getX(), this.getY());
							repaint();
							Thread.sleep(15);
						}

					}
//					wallCheck();
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
						for (int i = 0; i < 40; i++) {
							this.setX(this.getX() + 1);
							this.setY(this.getY() - 3);
							this.setLocation(this.getX(), this.getY());
							repaint();
							Thread.sleep(15);
						}

						for (int i = 0; i < 40; i++) {
							this.setX(this.getX() + 1);
							this.setY(this.getY() + 3);
							this.setLocation(this.getX(), this.getY());
							repaint();
							Thread.sleep(15);
						}

					}
//					stopCheck(this);
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
				for (int i = 0; i < 130; i++) {
					try {
						this.setY(this.getY() - 1);
						this.setLocation(this.getX(), this.getY());
						repaint();
						Thread.sleep(5);
						
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				this.up = false;
				this.down = true;
				// ��� �� �ϰ� ����
				while (this.down) {
					while (this.down && this.left) {
						try {
//							stopCheck(this);
							this.setIcon(this.playerL);
							this.setX(this.getX() - 1);
							this.setY(this.getY() + 3);
							this.setLocation(this.getX(), this.getY());
//							repaint();
							Thread.sleep(15);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					while (this.down && this.right) {
						try {
//						stopCheck(this);
							this.setIcon(this.playerR);
							this.setX(this.getX() + 1);
							this.setY(this.getY() + 3);
							this.setLocation(this.getX(), this.getY());
//						repaint();
							Thread.sleep(15);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					try {
//					stopCheck(this);
						this.setY(this.getY() + 1);
						this.setLocation(this.getX(), this.getY());
//					repaint();
						Thread.sleep(5);
					} catch (Exception e) {
						// TODO: handle exception
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
