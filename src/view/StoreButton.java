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

	// ������ ��ư�� ����� ������
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
				// Ȯ��â�� �߰� �����ҰͰ���!
				// �� �������� �����Ͻðڽ��ϱ�? ��/�ƴϿ� ������ ����,����,������ �ܾ׳�Ÿ����
			}
		});
	}

	// �ִϸ� ��ư�� ����� ������
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
				// Ȯ��â �� ������ �Ľðڽ��ϱ�? ��/�ƴϿ� ���� ������, �����Ǹ� �� �ܾ� ��Ÿ����
				ani.getPrice();
				int money = hunter.getMoney() - ani.getPrice();
				checkAnimal();
			}
		});
	}

	// ��ɲ��� ������ ���� ��Ÿ�� ���̺��� ��ȯ���ִ� �޼ҵ�
	public JLabel getMoneyLabel() {
		setMoneyLabel();
		moneyLabel.setSize(250, 35);
		moneyLabel.setFont(new Font("", Font.BOLD, 30));
		return moneyLabel;
	}

	// ��ɲ��� ������ ���� ���̺� �������ִ� �޼ҵ�
	public void setMoneyLabel() {
		moneyLabel.setText("" + hunter.getMoney());
	}

	// ����Ʈ�� ������ ������ ��ư ���������� �ٲ��ִ� �޼ҵ�
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