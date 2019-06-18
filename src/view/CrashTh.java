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
	
	// �� ��ư�� �浹 ���� �ľ�: �浹�� ������ �� ��ư�� �浹���θ� �Ѱ��ֱ�
	public boolean isCrash(JButton b1, AnimalButton b2) {
		// b1���� ����ϴ� ��ü(��ɲ�|��ɰ�|trap)��, b2���� ����(��ɰ�|����)�� ��
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
				if(aniB.getAni().getActionInfo().getName().equals("�ӽ÷�")) {
					aniB.getMush().heal(Hunter.getInstance()); // ��ɲ��� speed ����
					System.out.println("������ crash �߻�"); // test
				}
				else if((aniB.getAni().getActionInfo().getName().equals("Deer") || 
						aniB.getAni().getActionInfo().getName().equals("Rabbit") || 
						aniB.getAni().getActionInfo().getName().equals("Tiger") || 
						aniB.getAni().getActionInfo().getName().equals("Lion")) 
						&& !aniB.isCaptured()) { // �̹� ���� ���� ��� crash Ž���ϴ� �� ����
					Hunter.getInstance().catchAni(aniB.getAni()); // �ش� AnimalButton�� ������ �ִ� animal�� ���
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
							aniB.getMush().heal(Hunter.getInstance()); // ��ɲ��� speed ����
							System.out.println("������ crash �߻�"); // test
						}
						else if((aniB.getAni().getActionInfo().getName().equals("Deer") || 
								aniB.getAni().getActionInfo().getName().equals("Rabbit") || 
								aniB.getAni().getActionInfo().getName().equals("Tiger") || 
								aniB.getAni().getActionInfo().getName().equals("Lion")) 
								&& !aniB.isCaptured()) { // �̹� ���� ���� ��� crash Ž���ϴ� �� ����
							Hunter.getInstance().catchAni(aniB.getAni()); // �ش� AnimalButton�� ������ �ִ� animal�� ���
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