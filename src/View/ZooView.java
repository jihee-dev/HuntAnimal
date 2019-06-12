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
	private Image background1 = new ImageIcon("./resourceFolder/image/store/zoobuff.jpg").getImage(); // 버프 받았을 때 배경
	private Image background2 = new ImageIcon("./resourceFolder/image/store/zoodebuff.jpg").getImage(); // 디버프 받았을 때 배경

	public ZooView(ViewController f) {
		this.F = f;
		this.setSize(1280, 720);
		this.setLayout(null);
		this.setVisible(true);
		if (zoo.changeHunterSpeed(hunter) == true) { // false일때 -> 속도 느려짐 ,true일때 -> 속도 빨라짐
			buff(); //버프이펙트
		} else
			debuff(); //디버프이펙트
		try {
			Thread.sleep(4000); // 4초
			this.setVisible(false); //요기도 수정해야할 부분일듯
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//패널이 어떤식으로 갈아끼워지는지, 백그라운드이미지설정을 어떻게 하는지 잘 모르겠어서 일단 이대로 두었습니당..ㅠ^ㅠ
	public void buff() {
		// background1 이미지로 셋
		// bgm 밝은거
	}

	public void debuff() {
		// background2
		// bgm 어두운거
	}
}