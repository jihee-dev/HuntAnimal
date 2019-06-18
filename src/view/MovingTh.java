package view;

public class MovingTh implements Runnable {
	
	// 화면 안에서 동물들이 움직이도록 범위 세팅 (upper left 좌표 기준)
	private static final int UP = 0;
	private static final int DOWN = 350;
	private static final int LEFT = 0;
	private static final int RIGHT = 1200;
	private AnimalButton b;
	
	public MovingTh(AnimalButton b) { this.b = b; }
	
	@Override
	public void run() {
		try {
			while(true) {
				SetAniMvt(b);
//				Thread.sleep(b.getAni().getActionInfo().getDelay()); // 너무 빨라서 버벅
				Thread.sleep(500);
			}
		} catch(Exception e) {}
	}

	public void SetAniMvt(AnimalButton p) {
		int rand = (int) (Math.random() * 100 + 1); // 0.25의 확률로 상하좌우로 움직이도록 설정
		if(rand <= 25) { 												// UP
			if(p.getY() <= UP) p.setLocation(p.getX(), p.getY());
			else p.setLocation(p.getX(), p.getY() - 50);
		}
		else if(rand > 25 && rand <= 50) { 								// DOWN
			if(p.getY() >= DOWN) p.setLocation(p.getX(), p.getY());
			else p.setLocation(p.getX(), p.getY() + 50);
		} 	
		else if(rand > 50 && rand <=75) {								// LEFT
			if(p.getX() <= LEFT) p.setLocation(p.getX(), p.getY());
			else p.setLocation(p.getX() - 50, p.getY());
		}
		else {															// RIGHT
			if(p.getX() >= RIGHT) p.setLocation(p.getX(), p.getY());				
			else p.setLocation(p.getX() + 50, p.getY()); 								
		}
	}  
}