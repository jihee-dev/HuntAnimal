package view;

public class MovingTh implements Runnable {
	
	// ȭ�� �ȿ��� �������� �����̵��� ���� ���� (upper left ��ǥ ����)
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
//				Thread.sleep(b.getAni().getActionInfo().getDelay()); // �ʹ� ���� ����
				Thread.sleep(500);
			}
		} catch(Exception e) {}
	}

	public void SetAniMvt(AnimalButton p) {
		int rand = (int) (Math.random() * 100 + 1); // 0.25�� Ȯ���� �����¿�� �����̵��� ����
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