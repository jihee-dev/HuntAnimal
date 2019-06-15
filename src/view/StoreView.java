package view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.active.Hunter;

public class StoreView extends JPanel {

	private Hunter h;
	private Image background = new ImageIcon("./resourceFolder/image/store/store.jpg").getImage();
	private ViewController F;
	private StoreButton sb;

	private StoreButton deerB;
	private StoreButton lionB;
	private StoreButton rabbitB;
	private StoreButton tigerB;

	private StoreButton foodB;
	private StoreButton gunB;
	private StoreButton netB;
	private StoreButton trapB;

	// 사냥꾼이 소유한 돈을 나타내는 레이블
	private JLabel moneyLabel;

	public StoreView(ViewController f) {
		this.F = f;
		this.setSize(1280, 720);
		this.setLayout(null);
		this.setVisible(true);
		//동물 버튼들 생성하기
		deerB = new StoreButton(h.getPrison().get(0));
		lionB = new StoreButton(h.getPrison().get(1));
		rabbitB = new StoreButton(h.getPrison().get(2));
		tigerB = new StoreButton(h.getPrison().get(3));
		// -> 감옥에 어떤식으로 동물을 저장하는지 몰라서 일단 이런식으로 넣어놨어요 수정해야할부분일것같음!

		//아이템 버튼들 생성하기
		foodB = new StoreButton(h.getItems()[3]);
		gunB = new StoreButton(h.getItems()[2]);
		netB = new StoreButton(h.getItems()[1]);
		trapB = new StoreButton(h.getItems()[0]);

		// 버튼 아이콘 이미지 세팅
		deerB.setIcon(sb.deerIcon);
		lionB.setIcon(sb.lionIcon);
		rabbitB.setIcon(sb.rabbitIcon);
		tigerB.setIcon(sb.tigerIcon);
		foodB.setIcon(sb.foodIcon);
		gunB.setIcon(sb.gunIcon);
		netB.setIcon(sb.netIcon);
		trapB.setIcon(sb.trapIcon);

		// 버튼 아이콘 위치 지정
		deerB.setLocation(284, 429);
		lionB.setLocation(416, 429);
		rabbitB.setLocation(548, 429);
		tigerB.setLocation(680, 429);
		foodB.setLocation(284, 558);
		gunB.setLocation(416, 558);
		netB.setLocation(548, 558);
		trapB.setLocation(680, 558);

		// 패널에 버튼 추가
		this.add(deerB);
		this.add(lionB);
		this.add(rabbitB);
		this.add(tigerB);
		this.add(foodB);
		this.add(gunB);
		this.add(netB);
		this.add(trapB);

		// 돈 레이블 패널에 세팅하기
		moneyLabel = sb.getMoneyLabel();
		moneyLabel.setLocation(301, 700);
		this.add(moneyLabel);

	}

}
