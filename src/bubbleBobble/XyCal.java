package bubbleBobble;

public class XyCal implements Runnable{
	Bubble bubble;
	Zhen[] zhen;
	Player player;

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
		if(player.getY() >= 536) {
			player.down = false;
			if(player.getY() >= 536) {
			player.setY(535);
			}
		}
		if(player.getX() <= 55) {
			player.left = false;
			if(player.getX() <= 55) {
				player.setX(55);
			}
			
		}
		if(player.getX() >= 885) {
			player.right = false;
			if(player.getX() >= 885) {
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
		if(zhen.getY() >= 536) {
			zhen.down = false;
			if(zhen.getY() >= 536) {
				zhen.setY(535);
			}
			return 0;
		}
		if(zhen.getX() <= 55) {
			zhen.left = false;
			if(zhen.getX() <= 55) {
				zhen.setX(55);
			}
			return 1;
			
		}
		if(zhen.getX() >= 885) {
			zhen.right = false;
			if(zhen.getX() >= 885) {
				zhen.setX(885);
			}
			return 2;
		}
		return -1;
	}
	
	public Boolean ZdownCheck(Zhen zhen) {
		if(zhen.getX() >=136 && zhen.getX() <= 168 && zhen.getY() <= 534) {
			return true;
		}
		return false;
	}
	
	public void bubbleNZhen(Bubble bubble) {
		if(true) { // Á¶°Ç : ¹öºí°ú Á¨ÀÇ ÁÂÇ¥°¡ °ãÄ¥ ¶§ true
			// ¹öºíÀº ¾ø¾îÁö°í Á¨ÀÇ ¾ÆÀÌÄÜÀº ¹öºí¿¡ °¤Èù ¾ÆÀÌÄÜÀ¸·Î º¯°æ
			// °¤È÷°í³ª¸é À§·Î Á¡Á¡ ÀÌµ¿(Á¨ÀÇ yÁÂÇ¥ °ª º¯°æ)
		}
	}

	@Override
	public void run() {
		
	}
}