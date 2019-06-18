package view;

import java.util.ArrayList;
import javax.swing.JButton;

import model.active.Hunter;

public class CrashTh implements Runnable {
	JButton h;
	ArrayList<AnimalButton> animals;
	
	public CrashTh(JButton h, ArrayList<AnimalButton> animals) {
		this.h = h;
		this.animals = animals;
	}
	
	// 두 버튼의 충돌 여부 파악: 충돌을 감지해 각 버튼에 충돌여부를 넘겨주기
	public boolean isCrash(JButton b1, AnimalButton b2) {
		// b1에는 사냥하는 주체(사냥꾼|사냥개|trap)가, b2에는 동물(사냥감|버섯)이 들어감
		int b1LowerLeftX = b1.getLocation().x;
		int b1LowerLeftY = b1.getLocation().y - b1.getHeight();
		int b2LowerLeftX = b2.getLocation().x;
		int b2LowerLeftY = b2.getLocation().y - b2.getHeight();
		
		if(b1LowerLeftX < (b2LowerLeftX + b2.getWidth()) && 
			(b1LowerLeftX + b1.getWidth())  > b2LowerLeftX &&	
			b1LowerLeftY < (b2LowerLeftY + b2.getHeight()) &&
			(b1LowerLeftY + b1.getHeight()) > b2LowerLeftY) {	
			b2.setCaptured(true); 
			return true;
		}
		else return false;
	}
	
	/*
	public void checkCrash() {
		for(AnimalButton aniB: animals) {
			if(isCrash(h, aniB)) {
				if(aniB.getAni().getActionInfo().getName().equals("머시룸")) {
					aniB.getMush().heal(Hunter.getInstance()); // 사냥꾼의 speed 증가
					System.out.println("버섯과 crash 발생"); // test
				}
				else if((aniB.getAni().getActionInfo().getName().equals("Deer") || 
						aniB.getAni().getActionInfo().getName().equals("Rabbit") || 
						aniB.getAni().getActionInfo().getName().equals("Tiger") || 
						aniB.getAni().getActionInfo().getName().equals("Lion")) 
						&& !aniB.isCaptured()) { // 이미 잡은 동물 계속 crash 탐지하는 것 방지
					Hunter.getInstance().catchAni(aniB.getAni()); // 해당 AnimalButton이 가지고 있는 animal을 잡기
					System.out.println("crash with animal"); // test
				}
			}
		}
	}
	*/
	@Override
	public void run() {
		try {
			while(true) {
				for(AnimalButton aniB: animals) {
					if(isCrash(h, aniB)) {
						if(aniB.getAni().getActionInfo().getName().equals("Mushroom")) {
							aniB.getMush().heal(Hunter.getInstance()); // 사냥꾼의 speed 증가
							System.out.println("버섯과 crash 발생"); // test
						}
						else if((aniB.getAni().getActionInfo().getName().equals("Deer") || 
								aniB.getAni().getActionInfo().getName().equals("Rabbit") || 
								aniB.getAni().getActionInfo().getName().equals("Tiger") || 
								aniB.getAni().getActionInfo().getName().equals("Lion")) 
								&& !aniB.isCaptured()) { // 이미 잡은 동물 계속 crash 탐지하는 것 방지
							Hunter.getInstance().catchAni(aniB.getAni()); // 해당 AnimalButton이 가지고 있는 animal을 잡기
							aniB.setCaptured(true);
							Hunter.getInstance().getPrison().add(aniB.getAni());
							aniB.setVisible(false);
							aniB.setEnabled(false);
							System.out.println("crash with animal"); // test
						}
						else { System.out.println("case 1"); Thread.yield();}
					} else { System.out.println("case 2"); Thread.yield(); }
				}
			}
		} catch(Exception e) {}
	}
}