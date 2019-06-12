package View;

import javax.swing.JButton;

import model.item.Item;
import model.item.Trap;

public class ItemButton extends JButton {
	private Item i;
	private Trap t;
	public ItemButton(Item i) {
		this.i = i;
	}
	public ItemButton(Trap t) {
		this.t = t;
	}
}
