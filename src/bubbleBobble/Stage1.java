package bubbleBobble;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Stage1 extends JFrame {

	Player player;
	Zhen[] zhen = new Zhen[3]; // �������� 1���� 3����
	XyCal cal;
	Thread wall;
	Thread pfloor;
	long time1 = 0;
	long time2 = 0;

	// ���׸���
	public void draw_bg() {
		JLabel jl = new JLabel(new ImageIcon("image/bg.png"));
		setContentPane(jl);
	}

	// ����׸���
	public void draw_bubble() {
		if (player.getDirect() == 2) {
			Bubble bubble = new Bubble(player.getX() + 50, player.getY());
			new Thread(() -> {
				try {
					add(bubble);
					for (int i = 0; i < 100; i++) {
						cal.bubbleNZhen(bubble, this);
						bubble.setLocation(bubble.getX(), bubble.getY());
						bubble.setX(bubble.getX() + 3);
						Thread.sleep(16);
					}
					for (int i = 0; i < 170; i++) {
						bubble.setLocation(bubble.getX(), bubble.getY());
						bubble.setY(bubble.getY() - 3);
						Thread.sleep(16);
					}
					remove(bubble);
					repaint();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}).start();
		} else {
			Bubble bubble = new Bubble(player.getX() - 50, player.getY());
			new Thread(() -> {
				try {
					add(bubble);
					for (int i = 0; i < 100; i++) {
						cal.bubbleNZhen(bubble, this);
						bubble.setLocation(bubble.getX(), bubble.getY());
						bubble.setX(bubble.getX() - 3);
						Thread.sleep(16);
					}

					for (int i = 0; i < 170; i++) {
						bubble.setLocation(bubble.getX(), bubble.getY());
						bubble.setY(bubble.getY() - 3);
						Thread.sleep(16);
					}
					remove(bubble);
					repaint();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}).start();
		}

	}

	// �� �׸���
	public void draw_zhen() {
		new Thread(() -> {
			try {
				for (int i = 0; i < zhen.length; i++) {
					zhen[i] = new Zhen(this);
					add(zhen[i]);
					Thread move = new Thread(zhen[i]);
					move.start();
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

	}

	// �÷��̾� �׸���
	public void draw_player() {
		add(player);
	}

	// �ʱ�ȭ
	public void init() {
		player = new Player(); // �÷��̾� ����
//		draw_zhen();
		cal = new XyCal(zhen, player);
		cal.zhen = zhen;

		wall = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					cal.PwallCheck();
				}
			}
		});
		pfloor = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						if (cal.PfloorCheck() == 2) {
							player.down = true;
							player.left = false;
							player.right = false;

						} else if (cal.PfloorCheck() == 1) {
							player.down = false;
						}
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		Stage1 stage1 = this;
		Thread th = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						if (cal.playerNZhen(stage1)) {

						}
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		wall.start();
		pfloor.start();
		th.start();

	}

	// ������
	public Stage1() {
		setSize(1000, 639);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		draw_bg();
		// �÷��̾� �׸���
		draw_player();
		draw_zhen();

		// Ű ������
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					player.up = true;
					break;
				case KeyEvent.VK_DOWN:
					break;
				case KeyEvent.VK_LEFT:
					player.setDirect(1);
					player.setIcon(player.playerL);
					player.left = true;
					break;
				case KeyEvent.VK_RIGHT:
					player.setDirect(2);
					player.setIcon(player.playerR);
					player.right = true;
					break;
				case KeyEvent.VK_SPACE:
					time1 = System.currentTimeMillis();
					if(time1 - time2 >= 500) {
						draw_bubble();
						time2 = System.currentTimeMillis();
					}
					
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.left = false;
					break;
				case KeyEvent.VK_RIGHT:
					player.right = false;
					break;
				case KeyEvent.VK_UP:
					player.up = false;
					break;
				}
			}
		});

		setVisible(true);
	}

	public static void main(String[] args) {
		new Stage1();
	}

}
