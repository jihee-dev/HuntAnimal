
package controller;

import java.awt.*;
import javax.swing.*;

import view.Forest1;
import view.MapSelect;
import view.Ranking;
import view.Start;
import view.StoreView;
import view.ZooView;


//import model.Hunter;


public class ViewController extends JFrame{
	
	private CardLayout cards=new CardLayout();

	public static ViewController instance = null;

	public static ViewController getInstance() {
		if (instance == null) {
			instance = new ViewController();
		}

		return instance;
	}
	
	private ViewController() {
		getContentPane().setLayout(cards);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1280,720);
		this.setResizable(false);
		getContentPane().add("Start", new Start(this));
		getContentPane().add("Map", new MapSelect(this));
		// getContentPane().add("f1", new Forest1(this));
		
		
		//나중에, Shop(this),Zoo(this)로 수정해야함.
		// getContentPane().add("f2", new Forest1(this));
		getContentPane().add("shop", new StoreView(this));
		getContentPane().add("zoo", new ZooView(this));
		
		
		
		this.setVisible(true);
	}
	public CardLayout getCardLayout() {

   	 return cards;

   	}
	
}