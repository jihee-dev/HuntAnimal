package View;
import java.awt.*;
import javax.swing.*;

import Model.Hunter;

import java.awt.event.*;
public class ViewController extends JFrame{
	JPanel maps=new MapSelect();
	JPanel forest1;
	JPanel forest2;
	JPanel shop;
	JPanel zoo;
	
	public ViewController() {
		this.add(maps);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1280,720);
		this.setVisible(true);
	}
	
	public static void main(String[]args) {
		new ViewController();
	}
}
