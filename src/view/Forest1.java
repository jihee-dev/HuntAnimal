package view;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ViewController;

import javax.swing.JLabel;
import java.util.ArrayList;

import model.active.Hunter;
import model.item.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Forest1 extends JPanel implements KeyListener, Runnable {
	
	private ViewController F;
	private JButton hb; // HunterButton ����
	//Ű���� �Է� ó���� ���� ������
	private boolean KeyUp = false; 
	private boolean KeyDown = false;
	private boolean KeyLeft = false;
	private boolean KeyRight = false;
	// Ÿ�̸� ���� �ʵ�
	private JLabel timerLabel;
//	Timer timer; // Ÿ�̸� ������ Ŭ���� 
	Thread timerTh; // ���� Ÿ�̸� ������	
	Thread th; // ������ ������

	private ArrayList<AnimalButton> animals; 

	private JButton trap; // Ʈ�� (�ӽ�)
	
	// CONSTRUCTOR ///////////////////////////////////////////////////////////////////
	public Forest1(ViewController f) {
		this.setLayout(null);
		this.setSize(1280,720);
		this.F = f;
		
		// Setting HunterButton
		hb = new HunterButton(Hunter.getInstance());
		hb.setSize(180,280);
		hb.setLocation(100,100);
		hb.addKeyListener(this); // Ű���� �̺�Ʈ ����
		this.add(hb);
		
		// ��ư ������ ������
		th = new Thread(this);  // ������ ����
	    th.start();  // ������ ����
			
		// Ÿ�̸� ������ ���� �ʱ�ȭ
		timerLabel = new JLabel();
		timerLabel.setForeground(Color.RED);
		timerLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.BOLD, 30));
		timerLabel.setBounds(10, 10, 80, 40);
		this.add(timerLabel);
//		timer = new Timer(timerLabel);
//		timerTh = new Thread(timer);
	//	timerTh.start(); // ������ �ް� ����
		
		// ������ ���� �ʱ�ȭ
//		trap = new ItemButton(new Trap()); // Trap �����ڰ� private���� ����Ǿ�����!
//		trap.setSize(����, ����);
//		trap.addKeyListener(this); // Ű���� �̺�Ʈ ����
// 		����Ű�� �̵��ϴٰ� ENTERŰ�� ������ ���� setLocation�ϱ�
//		this.add(trap);
//		this.setVisible(true);


	}
	//////////////////////////////////////////////////////////////////////////////////
	
	
	// ������ ���� �޼ҵ� //////////////////////////////////////////////////////////////////
	public void keyTyped(KeyEvent e) {
		 
	 }
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP :
	    		KeyUp = true;
	    		break;
	    	case KeyEvent.VK_DOWN :
	    		KeyDown = true;
	    		break;
	    	case KeyEvent.VK_LEFT :
	    		KeyLeft = true;
	    		((HunterButton) hb).PressLeft();
	    		break;
	    	case KeyEvent.VK_RIGHT :
	    		KeyRight = true;
	    		((HunterButton) hb).PressRight();
	    		break;
	    	default:
	    		break;
	    }
	}
	public void keyReleased(KeyEvent e){
		switch(e.getKeyCode()){
	    	case KeyEvent.VK_UP :
	    		KeyUp = false;
	    		break;
	    	case KeyEvent.VK_DOWN :
	    		KeyDown = false;
	    		break;
	    	case KeyEvent.VK_LEFT :
	    		KeyLeft = false;
	    		break;
	    	case KeyEvent.VK_RIGHT :
	    		KeyRight = false;
	    		break;
		}
	}
	// ������ �޾Ƶ��� Ű���� �������� 5��ŭ �̵���Ŵ
	public void KeyProcess(){
		 if(KeyUp == true) hb.setBounds(hb.getX(),hb.getY()-5, hb.getWidth(),hb.getHeight());
		 if(KeyDown == true) hb.setBounds(hb.getX(),hb.getY()+5, hb.getWidth(),hb.getHeight());
		 if(KeyLeft == true) hb.setBounds(hb.getX()-5,hb.getY(), hb.getWidth(),hb.getHeight());
		 if(KeyRight == true) hb.setBounds(hb.getX()+5,hb.getY(), hb.getWidth(),hb.getHeight());
	 }
	// AnimalButton���� �������� �����̰� �ϴ� �޼ҵ�
	public void SetAniMvt(JButton p) { 
		int rand = (int) (Math.random() * 100 + 1); // 0.25�� Ȯ���� �����¿�� �����̵��� ����
		if(rand <= 25) p.setLocation(p.getX(), p.getY() - 5); 						// UP
		else if(rand > 25 && rand <= 50) p.setLocation(p.getX(), p.getY() + 5); 	// DOWN
		else if(rand > 50 && rand <=75) p.setLocation(p.getX() - 5, p.getY());		// LEFT
		else p.setLocation(p.getX() + 5, p.getY()); 								// RIGHT
	}  
	// HunterButton �̵� ������
	public void run() {
		try {
			while(true){ 
				KeyProcess(); // Ű���� �Է�ó���� �Ͽ� x,y ����
				Thread.sleep(20); // 20 ��� hunter�� speed�� ��
				// Thread.sleep(hb.h.getSpeed()) ??
			}
		} catch (Exception e){}
	}
	// HunterButton�� ������ ������ ��ư���� �ڵ����� �̵���Ű�� ������
	public void run(JButton prey) { 
		try {
			SetAniMvt(prey);
			Thread.sleep(100); // 100 ��� prey�� speed�� ��
			// Thread.sleep(prey.p.getSpeed()) ??
		} catch(Exception e) {}
	}
	
	
	// ����ϱ� ////////////////////////////////////////////////////////////////////////
	
	// 0. �� ��ư�� �浹 ���� �ľ��ϱ�
	// JButton�� ȭ�鿡 ���� ������ ��ǥ�� ũ�Ⱑ �����Ƿ� �� ��ưŬ���� ���ο��� ��ǥ�񱳸� ���� �浹�� �ľ��� �� X
	// --> ������ �浹�� �����ϰ� �� ��ư�� �浹���θ� �Ѱ��ֱ�
	// ���� action�� ������ ó��
	public boolean isCrash(JButton b1, AnimalButton b2) {
		// b1���� ����ϴ� ��ü(��ɲ�|��ɰ�|trap)��, b2���� ��ɰ��� ��
		int b1LowerLeftX = b1.getLocation().x;
		int b1LowerLeftY = b1.getLocation().y - b1.getHeight();
		int b2LowerLeftX = b2.getLocation().x;
		int b2LowerLeftY = b2.getLocation().y - b2.getHeight();
		
		if(b1LowerLeftX < b2LowerLeftX + b2.getWidth() && 
			b1LowerLeftX + b1.getWidth()  > b2LowerLeftX &&	
			b1LowerLeftY < b2LowerLeftY + b2.getHeight() &&
			b1LowerLeftY + b1.getHeight() > b2LowerLeftY) {	
			b2.setCaptured(true); 
			return true;
		}
		else return false;
	}
	
	// Case 1. ��ɲ��� ������ ���� ���: HunterButton�� ������ư�� crash�� �Ͼ ��
	public void hunt() { 
		for(AnimalButton aniB: animals) {
			if(isCrash(hb, aniB)) {
				if(aniB.getTag().equals("mushroom")) { // ��ɲ��� �����̶� �������
					// ��ɲ��� speed ����
				}
				else if(aniB.getTag().equals("prey")) { // ��ɲ��� ��ɰ��� �������
					// hb.h.catchAni(aniB.ani); �ش� AnimalButton�� ������ �ִ� animal�� ���
				}
			}			
			// 2-1. Ʈ���� ����� ���
			if(isCrash(trap, aniB)) {
				// 
			}
		}
	}
	
//	Case 2. ��ɲ��� �������� �̿��� ������ ���� ���
	// hb.h.useItem(trap)�� �����ϸ�
//	2-2. �׹��� ����� ���
//			��ɲ��� range�� ������
	
//	2-3. ���� ����� ���
//				

	
//	��� ���� �� ���� prey�� ��ư�� ������� �ϱ�
	

}