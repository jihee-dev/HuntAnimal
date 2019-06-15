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
	private Image background1 = new ImageIcon("./resourceFolder/image/store/zoobuff.jpg").getImage(); // 버프 받았을 때 배경
	private Image background2 = new ImageIcon("./resourceFolder/image/store/zoodebuff.jpg").getImage(); // 디버프 받았을 때 배경
	private Image bg;
	public ZooView(ViewController f) {
		
		this.setSize(1280, 720);
		this.setLayout(null);
		F = f;
		this.setVisible(true);
		
	}
	
	//패널이 어떤식으로 갈아끼워지는지, 백그라운드이미지설정을 어떻게 하는지 잘 모르겠어서 일단 이대로 두었습니당..ㅠ^ㅠ
	public void buff() {
		this.bg=this.background1;
		
		// bgm 밝은거
	}

	public void debuff() {
		this.bg=this.background2;
		
		// bgm 어두운거
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