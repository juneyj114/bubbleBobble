package bubbleBobble;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Stage1 extends JFrame {

	Player player;
	Zhen[] zhen = new Zhen[3]; // �������� 1���� 3����

	// ���׸���
	public void draw_bg() {

	}

	// ����׸���
	public void draw_bubble() {
		
		if(player.getDirect() == 2) {
			Bubble bubble = new Bubble(player.getX()+50, player.getY());
			new Thread(() -> {
				try {
					add(bubble);
					for (int i = 0; i < 100; i++) {
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
			Bubble bubble = new Bubble(player.getX()-50, player.getY());
			new Thread(() -> {

				try {
					add(bubble);
					
					for (int i = 0; i < 100; i++) {
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
		for (Zhen z : zhen) {
			add(z);
		}
	}

	// �÷��̾� �׸���
	public void draw_player() {
		add(player);
	}

	// �ʱ�ȭ
	public void init() {
		player = new Player(); // �÷��̾� ����
		for (int i = 0; i < zhen.length; i++) { // �� ����
			zhen[i] = new Zhen();
		}
	}

	// ������
	public Stage1() {
		setSize(1000, 600);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();

		// �÷��̾� �׸���
		draw_player();

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
					draw_bubble();
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
