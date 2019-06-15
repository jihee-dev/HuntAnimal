package view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.active.Hunter;
import model.map.Zoo;

public class ZooView extends JPanel {

	private Zoo zoo;
	private Hunter hunter;
	private ViewController F;
	private Image background1 = new ImageIcon("./resourceFolder/image/store/zoobuff.jpg").getImage(); // ���� �޾��� �� ���
	private Image background2 = new ImageIcon("./resourceFolder/image/store/zoodebuff.jpg").getImage(); // ����� �޾��� �� ���

	public ZooView(ViewController f) {
		this.F = f;
		this.setSize(1280, 720);
		this.setLayout(null);
		this.setVisible(true);
		if (zoo.changeHunterSpeed(hunter) == true) { // false�϶� -> �ӵ� ������ ,true�϶� -> �ӵ� ������
			buff(); //��������Ʈ
		} else
			debuff(); //���������Ʈ
		try {
			Thread.sleep(4000); // 4��
			this.setVisible(false); //��⵵ �����ؾ��� �κ��ϵ�
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//�г��� ������� ���Ƴ���������, ��׶����̹��������� ��� �ϴ��� �� �𸣰ھ �ϴ� �̴�� �ξ����ϴ�..��^��
	public void buff() {
		// background1 �̹����� ��
		// bgm ������
	}

	public void debuff() {
		// background2
		// bgm ��ο��
	}
}