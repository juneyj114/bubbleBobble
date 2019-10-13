package bubbleBobble;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

class B extends JLabel {
	int x;
	int y;
	ImageIcon bubbled;
	ImageIcon bubble;
	A a;
	Boolean status;
	
	public void init() {
		 bubbled = new ImageIcon("image/bubbled.png");
		 bubble = new ImageIcon("image/bubble.png");
		 status = true;
	}
	
	public B(int x, int y) {
		init();
		this.x = x;
		this.y = y;
		setIcon(bubble);
		this.setSize(50, 50);
		this.setLocation(x, y);
		
	}
	public void hold(Zhen zhen){
		new Thread(() -> {
			this.x = -50;
			this.y = -50;
			zhen.isBubbed = true;
			zhen.setIcon(bubbled);
			moveZhen(zhen);
		}).start();
		
	}
	private void moveZhen(Zhen zhen) {
		try {
			for (int i = 0; i < zhen.getY()+50; i++) {
				zhen.setY(zhen.getY() - 1);
				zhen.setLocation(zhen.getX(), zhen.getY());
				Thread.sleep(10);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void leftB(A a) {
		new Thread(() -> {
			try {
				for (int i = 0; i < 300; i++) {
					this.setLocation(this.x, this.y);
					this.x -= 1;
					Thread.sleep(5);
				}
				status = false;
				for (int i = 0; i < 170; i++) {
					this.setLocation(this.x, this.y);
					this.y -= 3;
					Thread.sleep(16);
				}
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}).start();
	}
	
	public void rightB(A a) {
		new Thread(() -> {
			try {
				for (int i = 0; i < 300; i++) {
					this.setLocation(this.x, this.y);
					this.x += 1;
					Thread.sleep(5);
				}
				status = false;
				for (int i = 0; i < 170; i++) {
					this.setLocation(this.x, this.y);
					this.y -= 3;
					Thread.sleep(16);
				}
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}).start();
	}
	
	
}

class Cal extends Thread {
	A a;
	Test test;
	Zhen[] zhen;

	public Cal(A a, Test test, Zhen[] zhen) {
		this.a = a;
		this.test = test;
		this.zhen = zhen;
	}

	@Override
	public void run() {
		while (true) {
			try {
				// 벽 좌표 계산 시작
				if (a.x <= 55) {
					a.x = 55;
				}
				if (a.x >= 885) {
					a.x = 885;
				}
				if (a.y >= 536) {
					a.down = false;
					a.y = 536;
				}
				// 벽 좌표 계산 끝
				// 구멍 계산 시작
				if (a.getX() >= 110 && a.getX() <= 170 && a.getY() <= 420 && a.up == false) {
					a.downMove();
				} else if (a.getX() >= 780 && a.getX() <= 830 && a.getY() <= 420 && a.up == false) {
					a.downMove();
					// 구멍 계산 끝
				} else {
					// 2층
					if (a.getY() == 415) {
						a.down = false;
					}
					// 3층
					if (a.getY() == 295) {
						a.down = false;
					}
					// 4층
					if (a.getY() == 177) {
						a.down = false;
					}
				}

				// 버블과 젠 충돌 계산
				try {
					for (int i = 0; i < test.bList.size(); i++) {
						for (int j = 0; j < zhen.length; j++) {
							if (test.bList.get(i).status) {
								if (test.bList.get(i).getX() >= zhen[j].getX() - 50 && test.bList.get(i).getX() <= zhen[j].getX() + 50
										&& test.bList.get(i).getY() > zhen[j].getY() - 50 && test.bList.get(i).getY() < zhen[j].getY() + 50
										&& zhen[j].isBubbed == false)
								{
									System.out.println("충돌");
									test.bList.get(i).hold(zhen[j]);
								}
							} else if(test.bList.get(i).getY() <= 0) {
								test.bList.remove(i);
							}
						}
						
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				// 젠과 플레이어 충돌 계산
				try {
					for (int i = 0; i < zhen.length; i++) {
						if (zhen[i] != null) {
							if (a.getX() >= zhen[i].getX() - 50 && a.getX() <= zhen[i].getX() + 50
									&& a.getY() > zhen[i].getY() - 50 && a.getY() < zhen[i].getY() + 50) {
								a.attack(zhen[i]);
							}
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				Thread.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class A extends JLabel {
	boolean right = false;
	boolean left = false;
	boolean up = false;
	boolean down = false;
	int x = 55;
	int y = 55;
	ImageIcon playerR = new ImageIcon("image/playerR.png");
	ImageIcon playerL = new ImageIcon("image/playerL.png");
	Test test;
	int direction = 2;

	Thread rightTh;

	public A(Test test) {
		setIcon(playerR);
		this.setSize(50, 50);
		this.setLocation(x, y);
		this.test = test;
	}

	public void attack(Zhen zhen) {
		System.out.println("충돌");
		if (zhen.isBubbed) {
		test.remove(zhen);
		test.repaint();
		zhen.setX(-50);
		zhen.setY(-50);
		}

	}

	public void rightMove() {
		if (right == false) {
			this.setIcon(playerR);
			this.right = true;
			this.direction = 2;
			
			new Thread(() -> {
				System.out.println("우측이동 스레드가 생성되었습니다.");
				while (this.right) {
					if (this.up || this.down) { // 하강 혹은 상승중일 때는 이동속도가 떨어진다.
						try {
							this.setLocation(this.x + 1, this.y);
							this.x++;
							Thread.sleep(10);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					} else { // 평상시 이동속도
						try {
							this.setLocation(this.x + 1, this.y);
							this.x++;
							Thread.sleep(5);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

	public void leftMove() {
		if (left == false) {			
			this.setIcon(playerL);
			this.left = true;
			this.direction = 1;
			new Thread(() -> {
				System.out.println("좌측이동 스레드가 생성되었습니다.");
				while (this.left) {
					if (this.up || this.down) { // 하강 혹은 상승중일 때는 이동속도가 떨어진다.
						try {
							this.setLocation(this.x - 1, this.y);
							this.x--;
							Thread.sleep(10);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					} else { // 평상시 이동속도
						try {
							this.setLocation(this.x - 1, this.y);
							this.x--;
							Thread.sleep(5);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				}
			}).start();
		}

	}

	public void upMove() {
		if (up == false) {
			this.up = true;
			this.direction = 1;
			new Thread(() -> {
				System.out.println("상승 스레드가 생성되었습니다.");
				while (this.up) {
					for (int i = 0; i < 130; i++) {
						try {
							this.setLocation(this.x, this.y);
							this.y--;
							Thread.sleep(5);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					} // 상승 끝
					this.up = false;
					this.downMove();
				}
			}).start();
		}
	}

	public void downMove() {
		if (this.down == false && this.y < 536) {
			this.down = true;
			new Thread(() -> {
				System.out.println("하강 스레드가 생성되었습니다.");
				while (this.down) {
					try {
						this.setLocation(this.x, this.y + 1);
						this.y++;
						Thread.sleep(10);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}).start();
		}
	}

}

public class Test extends JFrame {

	A a;
	Cal cal;
	Bubble[] bubble;
	Zhen[] zhen;
	JLabel jl;
	ArrayList<B> bList;

	public void init() {
		jl = new JLabel(new ImageIcon("image/bg.png"));
		a = new A(this);
		zhen = new Zhen[3];
		for (int i = 0; i < zhen.length; i++) {
			zhen[i] = new Zhen();
			System.out.println(zhen[i].getX());
		}
		bList = new ArrayList<B>();
		cal = new Cal(a, this, zhen);

	}

	public Test() {
		setSize(1000, 639);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		setContentPane(jl);

		add(a);
		add(zhen[0]);
		add(zhen[1]);
		add(zhen[2]);
		zhen[1].setX(380);
		zhen[2].setX(280);
		cal.start();

		setVisible(true);

		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					a.rightMove();
					break;
				case KeyEvent.VK_LEFT:
					a.leftMove();
					break;
				case KeyEvent.VK_UP:
					if (a.down == false) {
						a.upMove();
					}
					break;
				case KeyEvent.VK_SPACE:
					if (a.direction == 1) {
						B bubble = new B(a.getX() - 50, a.getY());
						bubble.leftB(a);
						add(bubble);
						bList.add(bubble);
						
					} else {
						B bubble = new B(a.getX() + 50, a.getY());
						bubble.rightB(a);
						add(bubble);
						bList.add(bubble);
						
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					a.right = false;
					break;
				case KeyEvent.VK_LEFT:
					a.left = false;
					break;
				}
			}
		});
	}

	public static void main(String[] args) {
		new Test();
	}

}
