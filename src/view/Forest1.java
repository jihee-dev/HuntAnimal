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
	private JButton hb; // HunterButton 생성
	//키보드 입력 처리를 위한 변수들
	private boolean KeyUp = false; 
	private boolean KeyDown = false;
	private boolean KeyLeft = false;
	private boolean KeyRight = false;
	// 타이머 관련 필드
	private JLabel timerLabel;
//	Timer timer; // 타이머 스레드 클래스 
	Thread timerTh; // 실제 타이머 스레드	
	Thread th; // 움직임 스레드

	private ArrayList<AnimalButton> animals; 

	private JButton trap; // 트랩 (임시)
	
	// CONSTRUCTOR ///////////////////////////////////////////////////////////////////
	public Forest1(ViewController f) {
		this.setLayout(null);
		this.setSize(1280,720);
		this.F = f;
		
		// Setting HunterButton
		hb = new HunterButton(Hunter.getInstance());
		hb.setSize(180,280);
		hb.setLocation(100,100);
		hb.addKeyListener(this); // 키보드 이벤트 실행
		this.add(hb);
		
		// 버튼 움직임 스레드
		th = new Thread(this);  // 스레드 생성
	    th.start();  // 스레드 실행
			
		// 타이머 스레드 관련 초기화
		timerLabel = new JLabel();
		timerLabel.setForeground(Color.RED);
		timerLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.BOLD, 30));
		timerLabel.setBounds(10, 10, 80, 40);
		this.add(timerLabel);
//		timer = new Timer(timerLabel);
//		timerTh = new Thread(timer);
	//	timerTh.start(); // 스레드 달고 시작
		
		// 아이템 관련 초기화
//		trap = new ItemButton(new Trap()); // Trap 생성자가 private으로 선언되어있음!
//		trap.setSize(가로, 세로);
//		trap.addKeyListener(this); // 키보드 이벤트 실행
// 		방향키로 이동하다가 ENTER키를 누르는 곳에 setLocation하기
//		this.add(trap);
//		this.setVisible(true);


	}
	//////////////////////////////////////////////////////////////////////////////////
	
	
	// 움직임 관련 메소드 //////////////////////////////////////////////////////////////////
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
	// 위에서 받아들인 키값을 바탕으로 5만큼 이동시킴
	public void KeyProcess(){
		 if(KeyUp == true) hb.setBounds(hb.getX(),hb.getY()-5, hb.getWidth(),hb.getHeight());
		 if(KeyDown == true) hb.setBounds(hb.getX(),hb.getY()+5, hb.getWidth(),hb.getHeight());
		 if(KeyLeft == true) hb.setBounds(hb.getX()-5,hb.getY(), hb.getWidth(),hb.getHeight());
		 if(KeyRight == true) hb.setBounds(hb.getX()+5,hb.getY(), hb.getWidth(),hb.getHeight());
	 }
	// AnimalButton들을 랜덤으로 움직이게 하는 메소드
	public void SetAniMvt(JButton p) { 
		int rand = (int) (Math.random() * 100 + 1); // 0.25의 확률로 상하좌우로 움직이도록 설정
		if(rand <= 25) p.setLocation(p.getX(), p.getY() - 5); 						// UP
		else if(rand > 25 && rand <= 50) p.setLocation(p.getX(), p.getY() + 5); 	// DOWN
		else if(rand > 50 && rand <=75) p.setLocation(p.getX() - 5, p.getY());		// LEFT
		else p.setLocation(p.getX() + 5, p.getY()); 								// RIGHT
	}  
	// HunterButton 이동 스레드
	public void run() {
		try {
			while(true){ 
				KeyProcess(); // 키보드 입력처리를 하여 x,y 갱신
				Thread.sleep(20); // 20 대신 hunter의 speed가 들어감
				// Thread.sleep(hb.h.getSpeed()) ??
			}
		} catch (Exception e){}
	}
	// HunterButton을 제외한 나머지 버튼들을 자동으로 이동시키는 스레드
	public void run(JButton prey) { 
		try {
			SetAniMvt(prey);
			Thread.sleep(100); // 100 대신 prey의 speed가 들어감
			// Thread.sleep(prey.p.getSpeed()) ??
		} catch(Exception e) {}
	}
	
	
	// 사냥하기 ////////////////////////////////////////////////////////////////////////
	
	// 0. 두 버튼의 충돌 여부 파악하기
	// JButton은 화면에 띄우기 전까지 좌표와 크기가 없으므로 각 버튼클래스 내부에서 좌표비교를 통해 충돌을 파악할 수 X
	// --> 숲에서 충돌을 감지하고 각 버튼에 충돌여부를 넘겨주기
	// 최종 action은 숲에서 처리
	public boolean isCrash(JButton b1, AnimalButton b2) {
		// b1에는 사냥하는 주체(사냥꾼|사냥개|trap)가, b2에는 사냥감이 들어감
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
	
	// Case 1. 사냥꾼이 동물을 직접 사냥: HunterButton과 동물버튼의 crash가 일어날 때
	public void hunt() { 
		for(AnimalButton aniB: animals) {
			if(isCrash(hb, aniB)) {
				if(aniB.getTag().equals("mushroom")) { // 사냥꾼이 버섯이랑 만날경우
					// 사냥꾼의 speed 증가
				}
				else if(aniB.getTag().equals("prey")) { // 사냥꾼이 사냥감과 만날경우
					// hb.h.catchAni(aniB.ani); 해당 AnimalButton이 가지고 있는 animal을 잡기
				}
			}			
			// 2-1. 트랩을 사용해 사냥
			if(isCrash(trap, aniB)) {
				// 
			}
		}
	}
	
//	Case 2. 사냥꾼이 아이템을 이용해 동물을 직접 사냥
	// hb.h.useItem(trap)을 선택하면
//	2-2. 그물을 사용해 사냥
//			사냥꾼의 range를 넓혀줌
	
//	2-3. 총을 사용해 사냥
//				

	
//	사냥 성공 시 잡힌 prey의 버튼이 사라지게 하기
	

}