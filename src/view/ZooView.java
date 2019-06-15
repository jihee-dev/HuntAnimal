package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.active.Hunter;
import model.map.Zoo;

public class ZooView extends JPanel {

	private Zoo zoo=Zoo.getInstance();
	private Hunter hunter=Hunter.getInstance();
	private ViewController F;
	private Image background1 = new ImageIcon("./resourceFolder/image/store/zoobuff.jpg").getImage(); // ���� �޾��� �� ���
	private Image background2 = new ImageIcon("./resourceFolder/image/store/zoodebuff.jpg").getImage(); // ����� �޾��� �� ���
	private Image bg;
	public ZooView(ViewController f) {
		
		this.setSize(1280, 720);
		this.setLayout(null);
		F = f;
		this.setVisible(true);
		
	}
	
	//�г��� ������� ���Ƴ���������, ��׶����̹��������� ��� �ϴ��� �� �𸣰ھ �ϴ� �̴�� �ξ����ϴ�..��^��
	public void buff() {
		this.bg=this.background1;
		
		// bgm ������
	}

	public void debuff() {
		this.bg=this.background2;
		
		// bgm ��ο��
	}
	
	
	
	
	   public void paintComponent(Graphics g) {
           // Display image at at full size
		   if (zoo.changeHunterSpeed(hunter)) {
			   buff();
		   }else {
			   debuff();
		   }
		   
  	        g.drawImage(bg, 0, 0, null);
  	        setOpaque(false);
  	        super.paintComponent(g);
      }
	
}