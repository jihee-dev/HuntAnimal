package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.active.Animal;
import model.active.Hunter;
import model.item.Item;
import model.map.Store;

public class StoreButton extends JButton {

	private Hunter hunter = Hunter.getInstance();
	private Store store = Store.getInstance();
	private Item item;
	private Animal ani;
	private String name;
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
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ost.playmusic("./resourceFolder/sound/clickbgm.wav");
				int num = JOptionPane.showConfirmDialog(null, "이 아이템을 구매하시겠습니까? 이 아이템은 " + item.getPrice() + "원 입니다.",
						"구매확인창", JOptionPane.YES_NO_OPTION);
				if (num == 0) {
					if (store.checkMoney(hunter, item) == true) {
						JOptionPane.showMessageDialog(null, "구매가 완료되었습니다.");
					} else {
						JOptionPane.showMessageDialog(null, "돈이 부족하여 구매할 수 없습니다!");
					}

				}
			}
		});
	}

	// 애니멀 버튼을 만드는 생성자
	public StoreButton(String name, int index) {
		this.name = name;
		this.index = index;
		numAni = hunter.getNumAni()[index];
		this.setSize(120, 120);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ost.playmusic("./resourceFolder/sound/clickbgm.wav");
				ani = hunter.sellAni(name);
				// 확인창 이 동물을 파시겠습니까? 예/아니오 동물 마리수, 동물판매 후 잔액 나타내기
				ani.getPrice();
				int money = hunter.getMoney() - ani.getPrice();;
			}
		});
	}

}