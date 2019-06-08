package View;
import java.awt.*;
import javax.swing.*;

//import model.Hunter;
import View.MapSelect;
import java.awt.event.*;

public class ViewController extends JFrame{
	
	private CardLayout cards=new CardLayout();
	
	
	public ViewController() {
		getContentPane().setLayout(cards);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1280,720);
		this.setResizable(false);
		
		getContentPane().add("Map", new MapSelect(this));
		getContentPane().add("f1", new Forest1(this));
		
		
		//추후에 Forest2(this), Shop(this),Zoo(this)로 수정해야함
		getContentPane().add("f2", new Forest1(this));
		getContentPane().add("shop", new Forest1(this));
		getContentPane().add("zoo", new Forest1(this));
		
		
		
		this.setVisible(true);
	}
	public CardLayout getCardLayout() {

   	 return cards;

   	}
	public static void main(String[]args) {
		new ViewController();
	}
}
