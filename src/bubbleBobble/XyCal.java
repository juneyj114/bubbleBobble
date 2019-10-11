package bubbleBobble;

public class XyCal {
	Bubble bubble;
	Zhen[] zhen;
	Player player;

	public XyCal() {

	}

	public XyCal(Zhen[] zhen, Player player) {
		super();
		this.zhen = zhen;
		this.player = player;
	}

	public void PwallCheck() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (player.getY() >= 536) {
			player.down = false;
			if (player.getY() >= 536) {
				player.setY(535);
			}
		}
		if (player.getX() <= 55) {
			player.left = false;
			if (player.getX() <= 55) {
				player.setX(55);
			}

		}
		if (player.getX() >= 885) {
			player.right = false;
			if (player.getX() >= 885) {
				player.setX(885);
			}
		}
	}

	public int ZwallCheck(Zhen zhen) {
		try {

			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (zhen.getY() >= 536) {
			zhen.down = false;
			if (zhen.getY() >= 536) {
				zhen.setY(535);
			}
			return 0;
		}
		if (zhen.getX() <= 55) {
			zhen.left = false;
			if (zhen.getX() <= 55) {
				zhen.setX(55);
			}
			return 1;

		}
		if (zhen.getX() >= 885) {
			zhen.right = false;
			if (zhen.getX() >= 885) {
				zhen.setX(885);
			}
			return 2;
		}
		return -1;
	}

	public int PfloorCheck() {

		// ±¸¸Û
		if (player.getX() >= 110 && player.getX() <= 170 && player.getY() <= 420) {
			return 2;
		}
		if (player.getX() >= 780 && player.getX() <= 830 && player.getY() <= 420) {
			return 2;
		}
		// 2Ãş
		if (player.getY() >= 414 && player.getY() <= 416) {
//			player.setY(415);
			return 1;
		}
		// 3Ãş
		if (player.getY() >= 294 && player.getY() <= 296) {
//			player.setY(295);
			return 1;
		}
		// 4Ãş
		if (player.getY() >= 174 && player.getY() <= 176) {
//			player.setY(175);
			return 1;
		}
		return -1;

	}

	public Boolean ZdownCheck(Zhen zhen) {
		if (zhen.getX() >= 136 && zhen.getX() <= 168 && zhen.getY() <= 534) {
			return true;
		}
		if (zhen.getX() >= 770 && zhen.getX() <= 840 && zhen.getY() <= 534) {
			return true;
		}
		return false;
	}

	public Boolean bubbleNZhen(Bubble bubble, Stage1 stage1) {
		try {
			for (int i = 0; i < zhen.length; i++) {
				if (bubble.getX() >= zhen[i].getX() && bubble.getX() <= zhen[i].getX() + 50
						&& bubble.getY() > zhen[i].getY() - 50 && bubble.getY() < zhen[i].getY() + 50
						&& zhen[i].isBubbed == false) {
					stage1.remove(bubble);
					stage1.repaint();
					bubble.hold(zhen[i]);
					return true;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public Boolean playerNZhen(Stage1 stage1) {
		for (int i = 0; i < zhen.length; i++) {
			if (zhen[i] != null) {
				if (player.getX() >= zhen[i].getX() && player.getX() <= zhen[i].getX() + 50
						&& player.getY() > zhen[i].getY() - 50 && player.getY() < zhen[i].getY() + 50) {
					player.attack(zhen[i], stage1);
					return true;
				}
			}
		}

		return false;
	}

}