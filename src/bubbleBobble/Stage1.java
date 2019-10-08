package bubbleBobble;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Stage1 extends JFrame {

	Player player;
	Zhen[] zhen = new Zhen[3]; // 스테이지 1에는 3마리

	// 배경그리기
	public void draw_bg() {

	}

	// 버블그리기
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

	// 몹 그리기
	public void draw_zhen() {
		for (Zhen z : zhen) {
			add(z);
		}
	}

	// 플레이어 그리기
	public void draw_player() {
		add(player);
	}

	// 초기화
	public void init() {
		player = new Player(); // 플레이어 생성
		for (int i = 0; i < zhen.length; i++) { // 몹 생성
			zhen[i] = new Zhen();
		}
	}

	// 생성자
	public Stage1() {
		setSize(1000, 600);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();

		// 플레이어 그리기
		draw_player();

		// 키 리스너
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
