package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import model.active.Animal;
import model.active.Hunter;
import model.item.Item;
import model.map.Store;

public class StoreButton extends JButton implements ActionListener {

	private Hunter hunter = Hunter.getInstance();
	private Store store = Store.getInstance();
	private Item item;
	private Animal ani;
	private String name;
	private JLabel moneyLabel = new JLabel();
	private Ost ost = new Ost();
	private int index;
	private int numAni;

	// 아이템 버튼을 만드는 생성자
	public StoreButton(Item item) {
		this.item = item;
		this.setSize(120, 120);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.addActionListener(this);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				store.checkMoney(hunter, item);
				//setMoneyLabel();
				// 확인창도 뜨게 가능할것같음!
				// 이 아이템을 구매하시겠습니까? 예/아니오 아이템 가격,개수,구매후 잔액나타내기
			}
		});
	}

	// 애니멀 버튼을 만드는 생성자
	public StoreButton(String name, int index) {
		this.name = name;
		this.index = index;
		// numAni = hunter.getNumAni()[index];
		this.setSize(120, 120);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.checkAnimal();
		this.addActionListener(this);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ani = hunter.sellAni(name);
				setMoneyLabel();
				// 확인창 이 동물을 파시겠습니까? 예/아니오 동물 마리수, 동물판매 후 잔액 나타내기
				ani.getPrice();
				int money = hunter.getMoney() - ani.getPrice();
				checkAnimal();
			}
		});
	}

	// 사냥꾼이 소지한 돈을 나타낸 레이블을 반환해주는 메소드
	public JLabel getMoneyLabel() {
		setMoneyLabel();
		moneyLabel.setSize(250, 35);
		moneyLabel.setFont(new Font("", Font.BOLD, 30));
		return moneyLabel;
	}

	// 사냥꾼이 소지한 돈을 레이블에 세팅해주는 메소드
	public void setMoneyLabel() {
		moneyLabel.setText("" + hunter.getMoney());
	}

	// 리스트에 동물이 없으면 버튼 못누르도록 바꿔주는 메소드
	public void checkAnimal() {
		if (this.numAni == 0) {
			this.setEnabled(false);
		} else
			this.setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ost.playmusic("./resourceFolder/sound/clickbgm.wav");
	}
}