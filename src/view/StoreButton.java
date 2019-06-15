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

	// ������ ��ư�� ����� ������
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
				int num = JOptionPane.showConfirmDialog(null, "�� �������� �����Ͻðڽ��ϱ�? �� �������� " + item.getPrice() + "�� �Դϴ�.",
						"����Ȯ��â", JOptionPane.YES_NO_OPTION);
				if (num == 0) {
					if (store.checkMoney(hunter, item) == true) {
						JOptionPane.showMessageDialog(null, "���Ű� �Ϸ�Ǿ����ϴ�.");
					} else {
						JOptionPane.showMessageDialog(null, "���� �����Ͽ� ������ �� �����ϴ�!");
					}

				}
			}
		});
	}

	// �ִϸ� ��ư�� ����� ������
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
				// Ȯ��â �� ������ �Ľðڽ��ϱ�? ��/�ƴϿ� ���� ������, �����Ǹ� �� �ܾ� ��Ÿ����
				ani.getPrice();
				int money = hunter.getMoney() - ani.getPrice();;
			}
		});
	}

}