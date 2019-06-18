package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ViewController;
import model.active.Hunter;
import model.item.Feed;
import model.item.Gun;
import model.item.Net;
import model.item.Trap;

public class StoreView extends JPanel {

	private Hunter hunter = Hunter.getInstance();
	private Image background = new ImageIcon("./resourceFolder/image/store/store.png").getImage();
	private ViewController F;
	private StoreButton sb;
	private Ost ost = new Ost();

	private StoreButton deerB;
	private StoreButton lionB;
	private StoreButton rabbitB;
	private StoreButton tigerB;

	private StoreButton foodB;
	private StoreButton gunB;
	private StoreButton netB;
	private StoreButton trapB;

	// 사냥꾼이 소유한 돈을 나타내는 레이블
	public JButton moneyB;

	private JButton exit;

	public StoreView(ViewController f) {
		this.F = f;
		this.setSize(1280, 720);
		this.setLayout(null);
		this.setVisible(true);
		
		// 동물 버튼들 생성하기
		deerB = new StoreButton("deer", 0);
		lionB = new StoreButton("lion", 1);
		rabbitB = new StoreButton("rabbit", 2);
		tigerB = new StoreButton("tiger", 3);

		// 아이템 버튼들 생성하기
		foodB = new StoreButton(Feed.getIstance());
		gunB = new StoreButton(Gun.getInstance());
		netB = new StoreButton(Net.getInstance());
		trapB = new StoreButton(Trap.getInstance());

		// 돈 버튼 생성 & 세팅
		moneyB = new JButton();
		moneyB.setSize(160,40);
		moneyB.setBorderPainted(false);
		moneyB.setContentAreaFilled(false);
		moneyB.setFocusPainted(false);
		moneyB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ost.playmusic("./resourceFolder/sound/clickbgm.wav");
				JOptionPane.showMessageDialog(null, "현재 잔액은 " + hunter.getMoney() + "원 입니다.");
			}
			
		});

		// 버튼 아이콘 이미지 세팅
		deerB.setIcon(new ImageIcon("./resourceFolder/image/store/deerButton.png"));
		lionB.setIcon(new ImageIcon("./resourceFolder/image/store/lionButton.png"));
		rabbitB.setIcon(new ImageIcon("./resourceFolder/image/store/rabbitButton.png"));
		tigerB.setIcon(new ImageIcon("./resourceFolder/image/store/tigerButton.png"));
		foodB.setIcon(new ImageIcon("./resourceFolder/image/store/foodButton.png"));
		gunB.setIcon(new ImageIcon("./resourceFolder/image/store/gunButton.png"));
		netB.setIcon(new ImageIcon("./resourceFolder/image/store/netButton.png"));
		trapB.setIcon(new ImageIcon("./resourceFolder/image/store/trapButton.png"));

		// 버튼 아이콘 위치 지정
		deerB.setLocation(224, 330);
		lionB.setLocation(356, 330);
		rabbitB.setLocation(488, 330);
		tigerB.setLocation(620, 330);
		foodB.setLocation(224, 458);
		gunB.setLocation(356, 458);
		netB.setLocation(488, 458);
		trapB.setLocation(620, 458);
		moneyB.setLocation(10, 640);

		// 패널에 버튼 추가
		this.add(deerB);
		this.add(lionB);
		this.add(rabbitB);
		this.add(tigerB);
		this.add(foodB);
		this.add(gunB);
		this.add(netB);
		this.add(trapB);
		this.add(moneyB);

		exit = new JButton("뒤로가기");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("exit click!");
				ost.playmusic("./resourceFolder/sound/clickbgm.wav");
				F.getCardLayout().show(F.getContentPane(), "Map");
			}
		});
		exit.setBounds(1150, 620, 100, 50);
		this.add(exit);

	}

	public void paintComponent(Graphics g) {
		// Display image at at full size
		g.drawImage(background, 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}

}
