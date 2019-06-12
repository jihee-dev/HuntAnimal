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

	private Hunter hunter;
	private Store store;
	private Item item;
	private Animal ani;
	private JLabel moneyLabel = new JLabel();

	// ������ ��ư�� �������̹���
	ImageIcon foodIcon = new ImageIcon("../resourceFolder/image/store/foodButton.png");
	ImageIcon gunIcon = new ImageIcon("../resourceFolder/image/store/gunButton.png");
	ImageIcon netIcon = new ImageIcon("../resourceFolder/image/store/netButton.png");
	ImageIcon trapIcon = new ImageIcon("../resourceFolder/image/store/trapButton.png");

	// ���� ��ư�� �������̹���
	ImageIcon deerIcon = new ImageIcon("../resourceFolder/image/store/deerButton.png");
	ImageIcon lionIcon = new ImageIcon("../resourceFolder/image/store/lionButton.png");
	ImageIcon rabbitIcon = new ImageIcon("../resourceFolder/image/store/rabbitButton.png");
	ImageIcon tigerIcon = new ImageIcon("../resourceFolder/image/store/tigerButton.png");

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
				setMoneyLabel();
				//Ȯ��â�� �߰� �����ҰͰ���!
				//�� �������� �����Ͻðڽ��ϱ�? ��/�ƴϿ� ������ ����,����,������ �ܾ׳�Ÿ����
			}
		});
	}

	// �ִϸ� ��ư�� ����� ������
	public StoreButton(Animal ani) {
		this.ani = ani;
		this.setSize(120, 120);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.checkAnimal(); 
		this.addActionListener(this);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				hunter.sellAni(ani);
				setMoneyLabel();
				//Ȯ��â �� ������ �Ľðڽ��ϱ�? ��/�ƴϿ� ���� ������, �����Ǹ� �� �ܾ� ��Ÿ����
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
		if (this.ani == null) { 
			this.setEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Ŭ�� ȿ����
	}
}